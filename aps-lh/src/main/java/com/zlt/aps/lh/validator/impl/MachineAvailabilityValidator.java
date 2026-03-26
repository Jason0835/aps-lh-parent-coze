package com.zlt.aps.lh.validator.impl;

import com.zlt.aps.lh.context.ScheduleContext;
import com.zlt.aps.lh.api.domain.DevicePlanShut;
import com.zlt.aps.lh.api.domain.DevMaintenancePlan;
import com.zlt.aps.lh.api.domain.LhMachineInfo;
import com.zlt.aps.lh.api.domain.MonthPlanProd;
import com.zlt.aps.lh.validator.ConstraintValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 机台可用性校验器
 * 校验机台是否可用（停机、保养等）
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Slf4j
@Component
public class MachineAvailabilityValidator implements ConstraintValidator {

    @Override
    public String getName() {
        return "MachineAvailabilityValidator";
    }

    @Override
    public ValidationResult validate(ScheduleContext context, MonthPlanProd prod, LhMachineInfo machine,
                                       Date scheduleDate, String shift) {
        String machineCode = machine.getMachineCode();

        // 检查机台是否启用
        if (!isMachineEnabled(machine)) {
            return ValidationResult.fail("机台[" + machineCode + "]未启用", "MACHINE_NOT_ENABLED");
        }

        // 检查机台是否有停机计划
        if (hasShutdownPlan(context, machineCode, scheduleDate, shift)) {
            return ValidationResult.fail("机台[" + machineCode + "]在" + scheduleDate + "有停机计划", "MACHINE_HAS_SHUTDOWN");
        }

        // 检查机台是否有保养计划
        if (hasMaintenancePlan(context, machineCode, scheduleDate, shift)) {
            return ValidationResult.fail("机台[" + machineCode + "]在" + scheduleDate + "有保养计划", "MACHINE_HAS_MAINTENANCE");
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

        return allMachines.stream()
                .filter(machine -> validate(context, prod, machine, scheduleDate, shift).isValid())
                .collect(Collectors.toList());
    }

    /**
     * 检查机台是否启用
     *
     * @param machine 机台信息
     * @return 是否启用
     */
    private boolean isMachineEnabled(LhMachineInfo machine) {
        return machine != null && "1".equals(machine.getIsEnable());
    }

    /**
     * 检查机台是否有停机计划
     *
     * @param context 排程上下文
     * @param machineCode 机台编号
     * @param scheduleDate 排程日期
     * @param shift 班次
     * @return 是否有停机计划
     */
    private boolean hasShutdownPlan(ScheduleContext context, String machineCode, Date scheduleDate, String shift) {
        List<DevicePlanShut> shutdownPlans = context.getDataContext().getDevicePlanShutList();
        if (CollectionUtils.isEmpty(shutdownPlans)) {
            return false;
        }

        for (DevicePlanShut plan : shutdownPlans) {
            if (!machineCode.equals(plan.getMachineCode())) {
                continue;
            }

            // 检查日期是否在停机计划范围内
            if (isDateInRange(scheduleDate, plan.getPlanStartDate(), plan.getPlanEndDate())) {
                // 如果班次为空或停机计划覆盖了该班次
                if (shift == null || isShiftAffected(plan, shift)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 检查机台是否有保养计划
     *
     * @param context 排程上下文
     * @param machineCode 机台编号
     * @param scheduleDate 排程日期
     * @param shift 班次
     * @return 是否有保养计划
     */
    private boolean hasMaintenancePlan(ScheduleContext context, String machineCode, Date scheduleDate, String shift) {
        List<DevMaintenancePlan> maintenancePlans = context.getDataContext().getDevMaintenancePlanList();
        if (CollectionUtils.isEmpty(maintenancePlans)) {
            return false;
        }

        for (DevMaintenancePlan plan : maintenancePlans) {
            if (!machineCode.equals(plan.getLhMachineCode())) {
                continue;
            }

            // 检查日期是否在保养计划范围内
            if (isDateInRange(scheduleDate, plan.getPlanMaintenanceDate(), plan.getPlanMaintenanceDate())) {
                // 如果班次为空或保养计划覆盖了该班次
                if (shift == null || isTimeInShift(plan.getPlanStartTime(), plan.getPlanEndTime(), shift)) {
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
            // 如果没有结束日期，只比较开始日期
            return date.compareTo(startDate) >= 0;
        }
        return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
    }

    /**
     * 检查停机计划是否影响该班次
     *
     * @param plan 停机计划
     * @param shift 班次
     * @return 是否影响
     */
    private boolean isShiftAffected(DevicePlanShut plan, String shift) {
        // 简化处理：如果停机计划跨班次，则影响所有班次
        // 实际应根据停机时间和班次时间判断
        return true;
    }

    /**
     * 检查时间是否在班次内
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param shift 班次
     * @return 是否在班次内
     */
    private boolean isTimeInShift(Date startTime, Date endTime, String shift) {
        // 简化处理：如果有保养时间，则影响该班次
        // 实际应根据保养时间和班次时间判断
        return startTime != null || endTime != null;
    }
}
