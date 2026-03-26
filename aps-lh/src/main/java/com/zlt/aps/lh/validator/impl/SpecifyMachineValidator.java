package com.zlt.aps.lh.validator.impl;

import com.zlt.aps.lh.context.ScheduleContext;
import com.zlt.aps.lh.api.domain.LhMachineInfo;
import com.zlt.aps.lh.api.domain.MonthPlanProd;
import com.zlt.aps.lh.api.domain.SpecifyMachine;
import com.zlt.aps.lh.validator.ConstraintValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 定点机台校验器
 * 校验物料是否只能在定点机台上生产
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Slf4j
@Component
public class SpecifyMachineValidator implements ConstraintValidator {

    @Override
    public String getName() {
        return "SpecifyMachineValidator";
    }

    @Override
    public ValidationResult validate(ScheduleContext context, MonthPlanProd prod, LhMachineInfo machine,
                                       Date scheduleDate, String shift) {
        String materialCode = prod.getMaterialCode();
        String machineCode = machine.getMachineCode();

        // 获取物料的定点机台列表
        List<String> specifyMachines = context.getDataContext().getSpecifyMachines(materialCode);
        
        // 如果没有定点机台限制，则所有机台都可以
        if (CollectionUtils.isEmpty(specifyMachines)) {
            return ValidationResult.success();
        }

        // 检查当前机台是否在定点机台列表中
        if (specifyMachines.contains(machineCode)) {
            // 进一步检查定点机台的限制类型和生效日期
            SpecifyMachine specify = getSpecifyMachine(context, materialCode, machineCode, scheduleDate);
            if (specify != null) {
                // 限制类型为"不可作业"
                if ("2".equals(specify.getRestrictType())) {
                    return ValidationResult.fail("机台[" + machineCode + "]被设置为物料[" + materialCode + "]的不可作业机台", 
                            "MACHINE_RESTRICTED");
                }
                // 限制类型为"限制作业"，允许生产
                return ValidationResult.success();
            }
        } else {
            // 机台不在定点列表中，检查是否有不可作业的定点
            if (hasUnavailableSpecify(context, materialCode, scheduleDate)) {
                return ValidationResult.fail("物料[" + materialCode + "]有定点机台限制，机台[" + machineCode + "]不在定点列表中", 
                        "MACHINE_NOT_IN_SPECIFY");
            }
        }

        return ValidationResult.success();
    }

    @Override
    public List<LhMachineInfo> getAvailableMachines(ScheduleContext context, MonthPlanProd prod,
                                                      Date scheduleDate, String shift) {
        String materialCode = prod.getMaterialCode();
        List<String> specifyMachines = context.getDataContext().getSpecifyMachines(materialCode);

        // 如果没有定点机台限制，返回所有机台
        if (CollectionUtils.isEmpty(specifyMachines)) {
            return context.getDataContext().getMachineInfoList();
        }

        // 获取可用的定点机台（限制作业类型）
        List<String> availableSpecifyMachines = getAvailableSpecifyMachines(context, materialCode, scheduleDate);

        // 如果有可用的定点机台，只返回这些机台
        if (!CollectionUtils.isEmpty(availableSpecifyMachines)) {
            return context.getDataContext().getMachineInfoList().stream()
                    .filter(m -> availableSpecifyMachines.contains(m.getMachineCode()))
                    .collect(Collectors.toList());
        }

        // 如果没有定点限制，返回所有不在不可作业列表中的机台
        List<String> unavailableMachines = getUnavailableSpecifyMachines(context, materialCode, scheduleDate);
        return context.getDataContext().getMachineInfoList().stream()
                .filter(m -> !unavailableMachines.contains(m.getMachineCode()))
                .collect(Collectors.toList());
    }

    /**
     * 获取定点机台信息
     *
     * @param context 排程上下文
     * @param materialCode 物料编号
     * @param machineCode 机台编号
     * @param scheduleDate 排程日期
     * @return 定点机台信息
     */
    private SpecifyMachine getSpecifyMachine(ScheduleContext context, String materialCode, 
                                              String machineCode, Date scheduleDate) {
        List<SpecifyMachine> specifyList = context.getDataContext().getSpecifyMachineList();
        if (CollectionUtils.isEmpty(specifyList)) {
            return null;
        }

        for (SpecifyMachine specify : specifyList) {
            if (materialCode.equals(specify.getMaterialCode()) 
                    && machineCode.equals(specify.getLhMachineCode())) {
                // 检查生效日期
                if (isEffective(specify, scheduleDate)) {
                    return specify;
                }
            }
        }

        return null;
    }

    /**
     * 检查定点机台是否生效
     *
     * @param specify 定点机台信息
     * @param scheduleDate 排程日期
     * @return 是否生效
     */
    private boolean isEffective(SpecifyMachine specify, Date scheduleDate) {
        if (scheduleDate == null) {
            return true;
        }

        Date startDate = specify.getEffectStartDate();
        Date endDate = specify.getEffectEndDate();

        if (startDate == null && endDate == null) {
            return true;
        }

        if (startDate != null && scheduleDate.before(startDate)) {
            return false;
        }

        if (endDate != null && scheduleDate.after(endDate)) {
            return false;
        }

        return true;
    }

    /**
     * 获取可用的定点机台列表（限制作业类型）
     *
     * @param context 排程上下文
     * @param materialCode 物料编号
     * @param scheduleDate 排程日期
     * @return 可用定点机台列表
     */
    private List<String> getAvailableSpecifyMachines(ScheduleContext context, String materialCode, Date scheduleDate) {
        List<String> availableMachines = new ArrayList<>();
        List<SpecifyMachine> specifyList = context.getDataContext().getSpecifyMachineList();

        if (CollectionUtils.isEmpty(specifyList)) {
            return availableMachines;
        }

        for (SpecifyMachine specify : specifyList) {
            if (materialCode.equals(specify.getMaterialCode()) 
                    && "1".equals(specify.getRestrictType())
                    && isEffective(specify, scheduleDate)) {
                availableMachines.add(specify.getLhMachineCode());
            }
        }

        return availableMachines;
    }

    /**
     * 获取不可作业的定点机台列表
     *
     * @param context 排程上下文
     * @param materialCode 物料编号
     * @param scheduleDate 排程日期
     * @return 不可作业机台列表
     */
    private List<String> getUnavailableSpecifyMachines(ScheduleContext context, String materialCode, Date scheduleDate) {
        List<String> unavailableMachines = new ArrayList<>();
        List<SpecifyMachine> specifyList = context.getDataContext().getSpecifyMachineList();

        if (CollectionUtils.isEmpty(specifyList)) {
            return unavailableMachines;
        }

        for (SpecifyMachine specify : specifyList) {
            if (materialCode.equals(specify.getMaterialCode()) 
                    && "2".equals(specify.getRestrictType())
                    && isEffective(specify, scheduleDate)) {
                unavailableMachines.add(specify.getLhMachineCode());
            }
        }

        return unavailableMachines;
    }

    /**
     * 检查是否有不可作业的定点机台
     *
     * @param context 排程上下文
     * @param materialCode 物料编号
     * @param scheduleDate 排程日期
     * @return 是否有不可作业的定点机台
     */
    private boolean hasUnavailableSpecify(ScheduleContext context, String materialCode, Date scheduleDate) {
        return !CollectionUtils.isEmpty(getUnavailableSpecifyMachines(context, materialCode, scheduleDate));
    }
}
