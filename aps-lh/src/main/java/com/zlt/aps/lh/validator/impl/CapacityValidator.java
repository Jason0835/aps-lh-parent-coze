package com.zlt.aps.lh.validator.impl;

import com.zlt.aps.lh.context.MachineDaySchedule;
import com.zlt.aps.lh.context.ScheduleContext;
import com.zlt.aps.lh.api.domain.LhMachineInfo;
import com.zlt.aps.lh.api.domain.MonthPlanProd;
import com.zlt.aps.lh.api.domain.SkuLhCapacity;
import com.zlt.aps.lh.validator.ConstraintValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 产能校验器
 * 校验机台是否有足够的产能
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Slf4j
@Component
public class CapacityValidator implements ConstraintValidator {

    @Override
    public String getName() {
        return "CapacityValidator";
    }

    @Override
    public ValidationResult validate(ScheduleContext context, MonthPlanProd prod, LhMachineInfo machine,
                                       Date scheduleDate, String shift) {
        String materialCode = prod.getMaterialCode();
        String machineCode = machine.getMachineCode();

        // 获取物料在该机台的产能配置
        SkuLhCapacity capacity = context.getDataContext().getCapacity(materialCode, machineCode);
        
        // 如果没有产能配置，校验失败
        if (capacity == null) {
            return ValidationResult.fail("物料[" + materialCode + "]在机台[" + machineCode + "]无产能配置", 
                    "NO_CAPACITY_CONFIG");
        }

        // 检查产能配置是否启用
        if (!"1".equals(capacity.getIsEnable())) {
            return ValidationResult.fail("物料[" + materialCode + "]在机台[" + machineCode + "]的产能配置未启用", 
                    "CAPACITY_NOT_ENABLED");
        }

        // 获取机台当日已排产数量
        MachineDaySchedule daySchedule = context.getMachineDaySchedule(machineCode, scheduleDate);
        int scheduledQuantity = daySchedule.getShiftScheduledQuantity(shift);

        // 获取班次产能上限
        Integer shiftCapacity = getShiftCapacity(capacity, shift);
        if (shiftCapacity != null && shiftCapacity > 0) {
            // 检查是否还有剩余产能
            if (scheduledQuantity >= shiftCapacity) {
                return ValidationResult.fail("机台[" + machineCode + "]在班次[" + shift + "]的产能已满", 
                        "CAPACITY_EXHAUSTED");
            }
        }

        // 检查标准节拍
        if (capacity.getStandardCycle() != null && capacity.getStandardCycle() <= 0) {
            return ValidationResult.fail("物料[" + materialCode + "]在机台[" + machineCode + "]的标准节拍配置错误", 
                    "INVALID_CYCLE_TIME");
        }

        return ValidationResult.success();
    }

    @Override
    public List<LhMachineInfo> getAvailableMachines(ScheduleContext context, MonthPlanProd prod,
                                                      Date scheduleDate, String shift) {
        List<LhMachineInfo> allMachines = context.getDataContext().getMachineInfoList();
        if (CollectionUtils.isEmpty(allMachines)) {
            return new ArrayList<>();
        }

        String materialCode = prod.getMaterialCode();
        List<LhMachineInfo> availableMachines = new ArrayList<>();

        for (LhMachineInfo machine : allMachines) {
            String machineCode = machine.getMachineCode();
            
            // 检查是否有产能配置
            SkuLhCapacity capacity = context.getDataContext().getCapacity(materialCode, machineCode);
            if (capacity == null || !"1".equals(capacity.getIsEnable())) {
                continue;
            }

            // 检查产能是否已满
            MachineDaySchedule daySchedule = context.getMachineDaySchedule(machineCode, scheduleDate);
            int scheduledQuantity = daySchedule.getShiftScheduledQuantity(shift);
            Integer shiftCapacity = getShiftCapacity(capacity, shift);
            
            if (shiftCapacity == null || shiftCapacity <= 0 || scheduledQuantity < shiftCapacity) {
                availableMachines.add(machine);
            }
        }

        return availableMachines;
    }

    /**
     * 获取班次产能
     *
     * @param capacity 产能配置
     * @param shift 班次
     * @return 班次产能
     */
    private Integer getShiftCapacity(SkuLhCapacity capacity, String shift) {
        if (capacity == null || shift == null) {
            return null;
        }

        // 根据班次获取对应的产能配置
        // 假设产能配置中有日产能，按班次平均分配
        Integer dailyCapacity = capacity.getDailyCapacity();
        if (dailyCapacity != null && dailyCapacity > 0) {
            // 假设三班制，每个班次产能为日产能的1/3
            return dailyCapacity / 3;
        }

        return null;
    }

    /**
     * 计算预计生产时长
     *
     * @param capacity 产能配置
     * @param quantity 生产数量
     * @return 预计生产时长（分钟）
     */
    public int calculateProductionTime(SkuLhCapacity capacity, int quantity) {
        if (capacity == null || capacity.getStandardCycle() == null || quantity <= 0) {
            return 0;
        }

        // 标准节拍单位为秒/条，生产时长 = 节拍 * 数量 / 60
        return (int) Math.ceil(capacity.getStandardCycle() * quantity / 60.0);
    }

    /**
     * 计算可生产数量
     *
     * @param context 排程上下文
     * @param prod 计划产品
     * @param machine 机台信息
     * @param scheduleDate 排程日期
     * @param shift 班次
     * @return 可生产数量
     */
    public int calculateAvailableQuantity(ScheduleContext context, MonthPlanProd prod, 
                                           LhMachineInfo machine, Date scheduleDate, String shift) {
        String materialCode = prod.getMaterialCode();
        String machineCode = machine.getMachineCode();

        SkuLhCapacity capacity = context.getDataContext().getCapacity(materialCode, machineCode);
        if (capacity == null) {
            return 0;
        }

        MachineDaySchedule daySchedule = context.getMachineDaySchedule(machineCode, scheduleDate);
        int scheduledQuantity = daySchedule.getShiftScheduledQuantity(shift);
        Integer shiftCapacity = getShiftCapacity(capacity, shift);

        if (shiftCapacity == null || shiftCapacity <= 0) {
            // 如果没有产能限制，返回剩余需求数量
            return context.getRemainingQuantity(prod.getId());
        }

        return Math.max(shiftCapacity - scheduledQuantity, 0);
    }
}
