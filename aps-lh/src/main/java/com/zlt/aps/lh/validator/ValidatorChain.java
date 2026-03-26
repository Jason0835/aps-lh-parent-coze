package com.zlt.aps.lh.validator;

import com.zlt.aps.lh.context.ScheduleContext;
import com.zlt.aps.lh.api.domain.LhMachineInfo;
import com.zlt.aps.lh.api.domain.MonthPlanProd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 校验器链
 * 使用责任链模式组合多个校验器
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Slf4j
@Component
public class ValidatorChain implements ConstraintValidator {

    private List<ConstraintValidator> validators = new ArrayList<>();

    /**
     * 添加校验器
     *
     * @param validator 校验器
     */
    public void addValidator(ConstraintValidator validator) {
        this.validators.add(validator);
    }

    /**
     * 设置校验器列表
     *
     * @param validators 校验器列表
     */
    public void setValidators(List<ConstraintValidator> validators) {
        this.validators = validators;
    }

    @Override
    public String getName() {
        return "ValidatorChain";
    }

    @Override
    public ValidationResult validate(ScheduleContext context, MonthPlanProd prod, LhMachineInfo machine,
                                       Date scheduleDate, String shift) {
        for (ConstraintValidator validator : validators) {
            ValidationResult result = validator.validate(context, prod, machine, scheduleDate, shift);
            if (!result.isValid()) {
                log.debug("校验器[{}]校验失败: {}", validator.getName(), result.getMessage());
                return result;
            }
        }
        return ValidationResult.success();
    }

    @Override
    public List<LhMachineInfo> getAvailableMachines(ScheduleContext context, MonthPlanProd prod,
                                                      Date scheduleDate, String shift) {
        List<LhMachineInfo> availableMachines = context.getDataContext().getMachineInfoList();

        for (ConstraintValidator validator : validators) {
            final List<LhMachineInfo> currentMachines = availableMachines;
            availableMachines = validator.getAvailableMachines(context, prod, scheduleDate, shift)
                    .stream()
                    .filter(m -> currentMachines.stream()
                            .anyMatch(cm -> cm.getMachineCode().equals(m.getMachineCode())))
                    .collect(Collectors.toList());

            if (availableMachines.isEmpty()) {
                log.debug("校验器[{}]过滤后无可用机台", validator.getName());
                break;
            }
        }

        return availableMachines;
    }
}
