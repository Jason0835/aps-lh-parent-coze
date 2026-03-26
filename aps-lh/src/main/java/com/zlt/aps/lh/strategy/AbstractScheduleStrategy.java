package com.zlt.aps.lh.strategy;

import com.zlt.aps.lh.context.MachineDaySchedule;
import com.zlt.aps.lh.context.ScheduleContext;
import com.zlt.aps.lh.api.domain.*;
import com.zlt.aps.lh.api.enums.ProductionStatusEnum;
import com.zlt.aps.lh.validator.ConstraintValidator;
import com.zlt.aps.lh.validator.impl.CapacityValidator;
import com.zlt.aps.lh.validator.impl.MouldAvailabilityValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 抽象排程策略基类
 * 提供排程策略的通用方法
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Slf4j
public abstract class AbstractScheduleStrategy implements ScheduleStrategy {

    @Autowired
    protected ConstraintValidator validatorChain;

    @Autowired
    protected CapacityValidator capacityValidator;

    @Autowired
    protected MouldAvailabilityValidator mouldValidator;

    @Override
    public List<LhScheduleResult> scheduleProduct(ScheduleContext context, MonthPlanProd prod,
                                                   Date scheduleDate, String shift) {
        List<LhScheduleResult> results = new ArrayList<>();

        // 获取剩余需求数量
        int remainingQuantity = context.getRemainingQuantity(prod.getId());
        if (remainingQuantity <= 0) {
            return results;
        }

        // 获取可用机台列表
        List<LhMachineInfo> availableMachines = validatorChain.getAvailableMachines(
                context, prod, scheduleDate, shift);

        if (availableMachines.isEmpty()) {
            log.warn("物料[{}]在日期[{}]班次[{}]无可用机台", prod.getMaterialCode(), scheduleDate, shift);
            return results;
        }

        // 选择最优机台
        LhMachineInfo bestMachine = selectBestMachine(context, prod, availableMachines, scheduleDate, shift);
        if (bestMachine == null) {
            return results;
        }

        // 计算排程数量
        int scheduleQuantity = calculateScheduleQuantity(context, prod, bestMachine, scheduleDate, shift);
        if (scheduleQuantity <= 0) {
            return results;
        }

        // 生成排程结果
        LhScheduleResult result = generateScheduleResult(context, prod, bestMachine, scheduleDate, shift, scheduleQuantity);
        if (result != null) {
            results.add(result);
        }

        return results;
    }

    @Override
    public int calculateScheduleQuantity(ScheduleContext context, MonthPlanProd prod,
                                          LhMachineInfo machine, Date scheduleDate, String shift) {
        // 获取剩余需求数量
        int remainingQuantity = context.getRemainingQuantity(prod.getId());
        
        // 获取机台可用产能
        int availableCapacity = capacityValidator.calculateAvailableQuantity(
                context, prod, machine, scheduleDate, shift);

        // 取较小值
        return Math.min(remainingQuantity, availableCapacity);
    }

    @Override
    public LhScheduleResult generateScheduleResult(ScheduleContext context, MonthPlanProd prod,
                                                    LhMachineInfo machine, Date scheduleDate,
                                                    String shift, int quantity) {
        if (quantity <= 0) {
            return null;
        }

        // 获取最优模具
        String mouldCode = mouldValidator.getBestMould(context, prod, machine, scheduleDate, shift);
        if (mouldCode == null) {
            log.warn("物料[{}]在机台[{}]无可用模具", prod.getMaterialCode(), machine.getMachineCode());
            return null;
        }

        // 获取产能配置
        SkuLhCapacity capacity = context.getDataContext().getCapacity(prod.getMaterialCode(), machine.getMachineCode());

        // 获取下一个序号
        MachineDaySchedule daySchedule = context.getMachineDaySchedule(machine.getMachineCode(), scheduleDate);
        MachineDaySchedule.ShiftSchedule shiftSchedule = daySchedule.getOrCreateShiftSchedule(shift);
        int nextSeq = shiftSchedule.getNextSeq();

        // 构建排程结果
        LhScheduleResult result = new LhScheduleResult();
        result.setId(generateId());
        result.setFactoryCode(context.getRequest().getFactoryCode());
        result.setMachineCode(machine.getMachineCode());
        result.setMachineName(machine.getMachineName());
        result.setMaterialCode(prod.getMaterialCode());
        result.setMaterialDesc(prod.getMaterialDesc());
        result.setMouldCode(mouldCode);
        result.setScheduleDate(scheduleDate);
        result.setShift(shift);
        result.setSeq(nextSeq);
        result.setPlanQuantity(quantity);
        result.setScheduledQuantity(0);
        result.setStatus(ProductionStatusEnum.PENDING.getCode());
        result.setMonthPlanProdId(prod.getId());
        result.setMonthPlanCode(prod.getMonthPlanCode());

        // 设置生产时长
        if (capacity != null && capacity.getStandardCycle() != null) {
            int productionTime = capacityValidator.calculateProductionTime(capacity, quantity);
            result.setProductionTime(productionTime);
        }

        // 设置换模时间
        MachineOnlineInfo onlineInfo = context.getDataContext().getMachineOnlineInfo(machine.getMachineCode());
        if (onlineInfo != null && onlineInfo.getCurrentMouldCode() != null) {
            int changeMouldTime = mouldValidator.calculateChangeMouldTime(
                    context, machine, onlineInfo.getCurrentMouldCode(), mouldCode);
            result.setChangeMouldTime(changeMouldTime);
        }

        result.setCreateBy(context.getRequest().getCreateBy());
        result.setCreateTime(new Date());

        return result;
    }

    /**
     * 生成唯一ID
     *
     * @return 唯一ID
     */
    protected Long generateId() {
        return Math.abs(UUID.randomUUID().getMostSignificantBits());
    }

    /**
     * 判断是否为工作日
     *
     * @param context 排程上下文
     * @param date 日期
     * @return 是否为工作日
     */
    protected boolean isWorkDay(ScheduleContext context, Date date) {
        List<WorkCalendar> calendars = context.getDataContext().getWorkCalendarList();
        if (calendars == null || calendars.isEmpty()) {
            return true; // 默认为工作日
        }

        for (WorkCalendar calendar : calendars) {
            if (isSameDay(date, calendar.getCalendarDate())) {
                return "1".equals(calendar.getIsWorkDay());
            }
        }

        return true;
    }

    /**
     * 判断两个日期是否为同一天
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 是否同一天
     */
    protected boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取班次列表
     *
     * @return 班次列表
     */
    protected List<String> getShiftList() {
        List<String> shifts = new ArrayList<>();
        shifts.add("1"); // 白班
        shifts.add("2"); // 夜班
        shifts.add("3"); // 中班
        return shifts;
    }
}
