package com.zlt.aps.lh.engine;

import com.zlt.aps.lh.context.ScheduleContext;
import com.zlt.aps.lh.api.dto.ScheduleDataContext;
import com.zlt.aps.lh.api.dto.ScheduleRequest;
import com.zlt.aps.lh.loader.DataLoader;
import com.zlt.aps.lh.strategy.ScheduleStrategy;
import com.zlt.aps.lh.strategy.ScheduleStrategyFactory;
import com.zlt.aps.lh.validator.ValidatorChain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 排程引擎
 * 负责协调排程流程的执行
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Slf4j
@Component
public class ScheduleEngine {

    @Autowired
    private DataLoader dataLoader;

    @Autowired
    private ScheduleStrategyFactory strategyFactory;

    @Autowired
    private ValidatorChain validatorChain;

    /**
     * 执行排程
     *
     * @param request 排程请求
     * @return 排程上下文
     */
    public ScheduleContext execute(ScheduleRequest request) {
        log.info("开始执行排程引擎，计划编号: {}", request.getPlanCode());

        // 创建排程上下文
        ScheduleContext context = new ScheduleContext();
        context.setRequest(request);

        try {
            // 1. 加载数据
            log.info("步骤1: 加载排程数据");
            ScheduleDataContext dataContext = dataLoader.loadAllData(request);
            context.setDataContext(dataContext);

            // 2. 初始化上下文
            log.info("步骤2: 初始化排程上下文");
            context.init();

            // 3. 初始化校验器链
            log.info("步骤3: 初始化校验器链");
            initValidatorChain();

            // 4. 获取排程策略
            log.info("步骤4: 获取排程策略: {}", request.getStrategyType());
            ScheduleStrategy strategy = strategyFactory.getStrategy(request.getStrategyType());

            // 5. 执行排程策略
            log.info("步骤5: 执行排程策略");
            strategy.schedule(context);

            // 6. 完成排程
            log.info("步骤6: 完成排程");
            context.complete();

            // 7. 打印排程统计
            printStatistics(context);

        } catch (Exception e) {
            log.error("排程执行失败: {}", e.getMessage(), e);
            context.fail(e.getMessage());
            throw new RuntimeException("排程执行失败: " + e.getMessage(), e);
        }

        return context;
    }

    /**
     * 异步执行排程
     *
     * @param request 排程请求
     * @return 排程上下文
     */
    public ScheduleContext executeAsync(ScheduleRequest request) {
        // 异步执行逻辑，可以使用线程池
        // 这里简化为同步执行
        return execute(request);
    }

    /**
     * 初始化校验器链
     */
    private void initValidatorChain() {
        // 校验器链已经在Spring中配置，这里可以添加额外的校验器
        // 如果需要动态添加校验器，可以在这里实现
    }

    /**
     * 打印排程统计信息
     *
     * @param context 排程上下文
     */
    private void printStatistics(ScheduleContext context) {
        ScheduleContext.ScheduleStatistics statistics = context.getStatistics();
        log.info("========== 排程统计 ==========");
        log.info("总计划数: {}", statistics.getTotalPlanCount());
        log.info("已排程计划数: {}", statistics.getScheduledPlanCount());
        log.info("未排程计划数: {}", statistics.getUnscheduledPlanCount());
        log.info("总排程结果数: {}", statistics.getTotalScheduleResultCount());
        log.info("总排程数量: {}", statistics.getTotalScheduledQuantity());
        log.info("总未排程数量: {}", statistics.getTotalUnscheduledQuantity());
        log.info("排程耗时: {} ms", statistics.getScheduleDuration());
        log.info("==============================");
    }

    /**
     * 验证排程请求
     *
     * @param request 排程请求
     * @return 验证结果
     */
    public ValidationResult validateRequest(ScheduleRequest request) {
        if (request == null) {
            return ValidationResult.fail("排程请求不能为空");
        }

        if (request.getFactoryCode() == null || request.getFactoryCode().isEmpty()) {
            return ValidationResult.fail("分厂编号不能为空");
        }

        if (request.getScheduleStartDate() == null) {
            return ValidationResult.fail("排程开始日期不能为空");
        }

        if (request.getScheduleEndDate() == null) {
            return ValidationResult.fail("排程结束日期不能为空");
        }

        if (request.getScheduleStartDate().after(request.getScheduleEndDate())) {
            return ValidationResult.fail("排程开始日期不能晚于结束日期");
        }

        if (request.getStrategyType() == null || request.getStrategyType().isEmpty()) {
            return ValidationResult.fail("排程策略类型不能为空");
        }

        return ValidationResult.success();
    }

    /**
     * 验证结果
     */
    public static class ValidationResult {
        private boolean valid;
        private String message;

        public ValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }

        public static ValidationResult success() {
            return new ValidationResult(true, "验证通过");
        }

        public static ValidationResult fail(String message) {
            return new ValidationResult(false, message);
        }

        public boolean isValid() {
            return valid;
        }

        public String getMessage() {
            return message;
        }
    }
}
