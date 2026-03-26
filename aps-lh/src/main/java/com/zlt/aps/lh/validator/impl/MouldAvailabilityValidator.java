package com.zlt.aps.lh.validator.impl;

import com.zlt.aps.lh.context.MouldDaySchedule;
import com.zlt.aps.lh.context.ScheduleContext;
import com.zlt.aps.lh.api.domain.*;
import com.zlt.aps.lh.api.enums.ChangeMouldTypeEnum;
import com.zlt.aps.lh.validator.ConstraintValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 模具可用性校验器
 * 校验模具是否可用
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Slf4j
@Component
public class MouldAvailabilityValidator implements ConstraintValidator {

    @Override
    public String getName() {
        return "MouldAvailabilityValidator";
    }

    @Override
    public ValidationResult validate(ScheduleContext context, MonthPlanProd prod, LhMachineInfo machine,
                                       Date scheduleDate, String shift) {
        String materialCode = prod.getMaterialCode();
        String machineCode = machine.getMachineCode();

        // 获取物料对应的模具列表
        List<String> mouldCodes = context.getDataContext().getMouldCodesByMaterial(materialCode);
        if (CollectionUtils.isEmpty(mouldCodes)) {
            return ValidationResult.fail("物料[" + materialCode + "]没有关联的模具", "NO_MOULD_RELATION");
        }

        // 检查是否有可用的模具
        for (String mouldCode : mouldCodes) {
            if (isMouldAvailable(context, mouldCode, machineCode, scheduleDate, shift)) {
                return ValidationResult.success();
            }
        }

        // 所有模具都不可用
        return ValidationResult.fail("物料[" + materialCode + "]的所有模具在机台[" + machineCode + "]上都不可用", 
                "NO_AVAILABLE_MOULD");
    }

    @Override
    public List<LhMachineInfo> getAvailableMachines(ScheduleContext context, MonthPlanProd prod,
                                                      Date scheduleDate, String shift) {
        List<LhMachineInfo> allMachines = context.getDataContext().getMachineInfoList();
        if (CollectionUtils.isEmpty(allMachines)) {
            return new ArrayList<>();
        }

        String materialCode = prod.getMaterialCode();
        List<String> mouldCodes = context.getDataContext().getMouldCodesByMaterial(materialCode);

        if (CollectionUtils.isEmpty(mouldCodes)) {
            return new ArrayList<>();
        }

        // 过滤出有可用模具的机台
        return allMachines.stream()
                .filter(machine -> hasAvailableMould(context, mouldCodes, machine.getMachineCode(), scheduleDate, shift))
                .collect(Collectors.toList());
    }

    /**
     * 检查是否有可用的模具
     *
     * @param context 排程上下文
     * @param mouldCodes 模具列表
     * @param machineCode 机台编号
     * @param scheduleDate 排程日期
     * @param shift 班次
     * @return 是否有可用模具
     */
    private boolean hasAvailableMould(ScheduleContext context, List<String> mouldCodes, 
                                       String machineCode, Date scheduleDate, String shift) {
        for (String mouldCode : mouldCodes) {
            if (isMouldAvailable(context, mouldCode, machineCode, scheduleDate, shift)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查模具是否可用
     *
     * @param context 排程上下文
     * @param mouldCode 模具编号
     * @param machineCode 机台编号
     * @param scheduleDate 排程日期
     * @param shift 班次
     * @return 是否可用
     */
    private boolean isMouldAvailable(ScheduleContext context, String mouldCode, String machineCode,
                                      Date scheduleDate, String shift) {
        // 检查是否有清洗计划
        if (hasCleaningPlan(context, mouldCode, scheduleDate)) {
            return false;
        }

        // 检查模具是否在其他机台上使用
        if (isMouldInUse(context, mouldCode, machineCode, scheduleDate, shift)) {
            return false;
        }

        return true;
    }

    /**
     * 检查是否有清洗计划
     *
     * @param context 排程上下文
     * @param mouldCode 模具编号
     * @param scheduleDate 排程日期
     * @return 是否有清洗计划
     */
    private boolean hasCleaningPlan(ScheduleContext context, String mouldCode, Date scheduleDate) {
        List<CleaningPlan> cleaningPlans = context.getDataContext().getCleaningPlanList();
        if (CollectionUtils.isEmpty(cleaningPlans)) {
            return false;
        }

        for (CleaningPlan plan : cleaningPlans) {
            if (mouldCode.equals(plan.getMouldCode())) {
                if (isDateInRange(scheduleDate, plan.getPlanDate(), plan.getPlanDate())) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 检查模具是否在其他机台上使用
     *
     * @param context 排程上下文
     * @param mouldCode 模具编号
     * @param machineCode 机台编号
     * @param scheduleDate 排程日期
     * @param shift 班次
     * @return 是否在使用
     */
    private boolean isMouldInUse(ScheduleContext context, String mouldCode, String machineCode,
                                  Date scheduleDate, String shift) {
        MouldDaySchedule mouldSchedule = context.getMouldDaySchedule(mouldCode, scheduleDate);
        
        // 检查模具是否在其他机台上使用
        List<String> usedMachines = mouldSchedule.getMachineCodes();
        if (!CollectionUtils.isEmpty(usedMachines)) {
            for (String usedMachine : usedMachines) {
                if (!machineCode.equals(usedMachine)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 检查日期是否在范围内
     *
     * @param date 待检查日期
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 是否在范围内
     */
    private boolean isDateInRange(Date date, Date startDate, Date endDate) {
        if (date == null || startDate == null) {
            return false;
        }
        if (endDate == null) {
            return date.compareTo(startDate) >= 0;
        }
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
    }

    /**
     * 获取最佳模具
     *
     * @param context 排程上下文
     * @param prod 计划产品
     * @param machine 机台信息
     * @param scheduleDate 排程日期
     * @param shift 班次
     * @return 最佳模具编号
     */
    public String getBestMould(ScheduleContext context, MonthPlanProd prod, LhMachineInfo machine,
                                Date scheduleDate, String shift) {
        String materialCode = prod.getMaterialCode();
        String machineCode = machine.getMachineCode();
        List<String> mouldCodes = context.getDataContext().getMouldCodesByMaterial(materialCode);

        if (CollectionUtils.isEmpty(mouldCodes)) {
            return null;
        }

        // 优先选择当前机台上已使用的模具（减少换模）
        MachineOnlineInfo onlineInfo = context.getDataContext().getMachineOnlineInfo(machineCode);
        if (onlineInfo != null && onlineInfo.getCurrentMouldCode() != null) {
            if (mouldCodes.contains(onlineInfo.getCurrentMouldCode())) {
                // 检查模具是否可用
                if (isMouldAvailable(context, onlineInfo.getCurrentMouldCode(), machineCode, scheduleDate, shift)) {
                    return onlineInfo.getCurrentMouldCode();
                }
            }
        }

        // 选择第一个可用的模具
        for (String mouldCode : mouldCodes) {
            if (isMouldAvailable(context, mouldCode, machineCode, scheduleDate, shift)) {
                return mouldCode;
            }
        }

        return null;
    }

    /**
     * 计算换模时间
     *
     * @param context 排程上下文
     * @param machine 机台信息
     * @param fromMould 当前模具
     * @param toMould 目标模具
     * @return 换模时间（分钟）
     */
    public int calculateChangeMouldTime(ScheduleContext context, LhMachineInfo machine,
                                         String fromMould, String toMould) {
        if (fromMould == null || toMould == null || fromMould.equals(toMould)) {
            return 0;
        }

        // 查找换模计划
        List<MouldChangePlan> changePlans = context.getDataContext().getMouldChangePlanList();
        if (!CollectionUtils.isEmpty(changePlans)) {
            for (MouldChangePlan plan : changePlans) {
                if (machine.getMachineCode().equals(plan.getMachineCode()) 
                        && fromMould.equals(plan.getOldMouldCode())
                        && toMould.equals(plan.getNewMouldCode())) {
                    return plan.getChangeDuration() != null ? plan.getChangeDuration() : 0;
                }
            }
        }

        // 默认换模时间（分钟）
        return 30;
    }
}
