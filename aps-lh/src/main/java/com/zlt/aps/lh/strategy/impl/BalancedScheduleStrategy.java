package com.zlt.aps.lh.strategy.impl;

import com.zlt.aps.lh.context.MachineDaySchedule;
import com.zlt.aps.lh.context.ScheduleContext;
import com.zlt.aps.lh.api.domain.LhMachineInfo;
import com.zlt.aps.lh.api.domain.LhScheduleResult;
import com.zlt.aps.lh.api.domain.MonthPlanProd;
import com.zlt.aps.lh.api.enums.StrategyTypeEnum;
import com.zlt.aps.lh.strategy.AbstractScheduleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 均衡排程策略
 * 在各机台之间均衡分配生产任务
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Slf4j
@Component
public class BalancedScheduleStrategy extends AbstractScheduleStrategy {

    @Override
    public String getName() {
        return StrategyTypeEnum.BALANCED.getDescription();
    }

    @Override
    public void schedule(ScheduleContext context) {
        log.info("开始执行均衡排程策略...");
        context.addLog("开始执行均衡排程策略");

        // 获取计划产品列表，按优先级排序
        List<MonthPlanProd> prodList = sortProducts(context);

        // 获取排程日期范围
        Date startDate = context.getRequest().getScheduleStartDate();
        Date endDate = context.getRequest().getScheduleEndDate();

        // 遍历排程日期
        Date currentDate = startDate;
        while (!currentDate.after(endDate)) {
            // 检查是否为工作日
            if (!isWorkDay(context, currentDate)) {
                log.debug("日期[{}]非工作日，跳过", currentDate);
                currentDate = addDays(currentDate, 1);
                continue;
            }

            // 遍历班次
            for (String shift : getShiftList()) {
                // 遍历计划产品
                for (MonthPlanProd prod : prodList) {
                    // 检查是否已排完
                    if (context.isMaterialCompleted(prod.getId())) {
                        continue;
                    }

                    // 执行排程
                    List<LhScheduleResult> results = scheduleProduct(context, prod, currentDate, shift);

                    // 添加排程结果
                    for (LhScheduleResult result : results) {
                        context.addScheduleResult(result);
                        log.debug("排程成功: 机台[{}], 物料[{}], 数量[{}]", 
                                result.getMachineCode(), result.getMaterialCode(), result.getPlanQuantity());
                    }
                }
            }

            currentDate = addDays(currentDate, 1);
        }

        // 处理未排程的计划
        processUnscheduled(context, prodList);

        log.info("均衡排程策略执行完成");
        context.addLog("均衡排程策略执行完成");
    }

    @Override
    public LhMachineInfo selectBestMachine(ScheduleContext context, MonthPlanProd prod,
                                             List<LhMachineInfo> availableMachines,
                                             Date scheduleDate, String shift) {
        if (availableMachines == null || availableMachines.isEmpty()) {
            return null;
        }

        // 选择已排数量最少的机台（均衡负载）
        LhMachineInfo bestMachine = null;
        int minScheduledQty = Integer.MAX_VALUE;

        for (LhMachineInfo machine : availableMachines) {
            MachineDaySchedule daySchedule = context.getMachineDaySchedule(
                    machine.getMachineCode(), scheduleDate);
            int scheduledQty = daySchedule.getShiftScheduledQuantity(shift);

            if (scheduledQty < minScheduledQty) {
                minScheduledQty = scheduledQty;
                bestMachine = machine;
            }
        }

        return bestMachine;
    }

    /**
     * 对计划产品进行排序
     * 按优先级、交付日期等排序
     *
     * @param context 排程上下文
     * @return 排序后的计划产品列表
     */
    private List<MonthPlanProd> sortProducts(ScheduleContext context) {
        List<MonthPlanProd> prodList = context.getDataContext().getMonthPlanProdList();
        if (prodList == null || prodList.isEmpty()) {
            return new ArrayList<>();
        }

        // 按优先级排序
        return prodList.stream()
                .sorted((p1, p2) -> {
                    // 首先按供应链优先级排序
                    int priority1 = p1.getSupplyChainPriority() != null ? p1.getSupplyChainPriority() : 99;
                    int priority2 = p2.getSupplyChainPriority() != null ? p2.getSupplyChainPriority() : 99;
                    if (priority1 != priority2) {
                        return Integer.compare(priority1, priority2);
                    }

                    // 然后按施工阶段排序
                    int stage1 = p1.getConstructionStage() != null ? p1.getConstructionStage() : 99;
                    int stage2 = p2.getConstructionStage() != null ? p2.getConstructionStage() : 99;
                    if (stage1 != stage2) {
                        return Integer.compare(stage1, stage2);
                    }

                    // 最后按需求数量降序（大数量优先）
                    int qty1 = p1.getPlanQuantity() != null ? p1.getPlanQuantity() : 0;
                    int qty2 = p2.getPlanQuantity() != null ? p2.getPlanQuantity() : 0;
                    return Integer.compare(qty2, qty1);
                })
                .collect(Collectors.toList());
    }

    /**
     * 处理未排程的计划
     *
     * @param context 排程上下文
     * @param prodList 计划产品列表
     */
    private void processUnscheduled(ScheduleContext context, List<MonthPlanProd> prodList) {
        for (MonthPlanProd prod : prodList) {
            if (!context.isMaterialCompleted(prod.getId())) {
                // 记录未排程原因
                context.addUnscheduledPlan(prod, 
                        com.zlt.aps.lh.api.enums.UnscheduledReasonEnum.CAPACITY_INSUFFICIENT);
                log.warn("物料[{}]未能完全排程，剩余数量: {}", 
                        prod.getMaterialCode(), context.getRemainingQuantity(prod.getId()));
            }
        }
    }

    /**
     * 日期加天数
     *
     * @param date 日期
     * @param days 天数
     * @return 新日期
     */
    private Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
}
