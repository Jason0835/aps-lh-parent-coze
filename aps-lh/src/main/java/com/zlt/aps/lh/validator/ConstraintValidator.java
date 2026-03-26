package com.zlt.aps.lh.validator;

import com.zlt.aps.lh.context.ScheduleContext;
import com.zlt.aps.lh.api.domain.LhMachineInfo;
import com.zlt.aps.lh.api.domain.MonthPlanProd;

import java.util.Date;
import java.util.List;

/**
 * 约束校验器接口
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
public interface ConstraintValidator {

    /**
     * 获取校验器名称
     *
     * @return 校验器名称
     */
    String getName();

    /**
     * 校验物料是否可以在指定机台上排产
     *
     * @param context 排程上下文
     * @param prod 计划产品
     * @param machine 机台信息
     * @param scheduleDate 排程日期
     * @param shift 班次
     * @return 校验结果
     */
    ValidationResult validate(ScheduleContext context, MonthPlanProd prod, LhMachineInfo machine,
                               Date scheduleDate, String shift);

    /**
     * 获取可用的机台列表
     *
     * @param context 排程上下文
     * @param prod 计划产品
     * @param scheduleDate 排程日期
     * @param shift 班次
     * @return 可用机台列表
     */
    List<LhMachineInfo> getAvailableMachines(ScheduleContext context, MonthPlanProd prod,
                                              Date scheduleDate, String shift);

    /**
     * 校验结果
     */
    class ValidationResult {
        private boolean valid;
        private String message;
        private String errorCode;

        public ValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }

        public ValidationResult(boolean valid, String message, String errorCode) {
            this.valid = valid;
            this.message = message;
            this.errorCode = errorCode;
        }

        public static ValidationResult success() {
            return new ValidationResult(true, "校验通过");
        }

        public static ValidationResult fail(String message) {
            return new ValidationResult(false, message);
        }

        public static ValidationResult fail(String message, String errorCode) {
            return new ValidationResult(false, message, errorCode);
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }
    }
}
