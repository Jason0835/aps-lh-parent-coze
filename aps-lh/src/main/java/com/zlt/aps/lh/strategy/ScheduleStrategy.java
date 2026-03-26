package com.zlt.aps.lh.strategy;

import com.zlt.aps.lh.context.ScheduleContext;
import com.zlt.aps.lh.api.domain.LhMachineInfo;
import com.zlt.aps.lh.api.domain.LhScheduleResult;
import com.zlt.aps.lh.api.domain.MonthPlanProd;

import java.util.Date;
import java.util.List;

/**
 * 排程策略接口
 * 定义排程算法的通用接口
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
public interface ScheduleStrategy {

    /**
     * 获取策略名称
     *
     * @return 策略名称
     */
    String getName();

    /**
     * 执行排程
     *
     * @param context 排程上下文
     */
    void schedule(ScheduleContext context);

    /**
     * 排程单个计划产品
     *
     * @param context 排程上下文
     * @param prod 计划产品
     * @param scheduleDate 排程日期
     * @param shift 班次
     * @return 排程结果列表
     */
    List<LhScheduleResult> scheduleProduct(ScheduleContext context, MonthPlanProd prod,
                                            Date scheduleDate, String shift);

    /**
     * 选择最优机台
     *
     * @param context 排程上下文
     * @param prod 计划产品
     * @param availableMachines 可用机台列表
     * @param scheduleDate 排程日期
     * @param shift 班次
     * @return 最优机台
     */
    LhMachineInfo selectBestMachine(ScheduleContext context, MonthPlanProd prod,
                                     List<LhMachineInfo> availableMachines,
                                     Date scheduleDate, String shift);

    /**
     * 计算排程数量
     *
     * @param context 排程上下文
     * @param prod 计划产品
     * @param machine 机台信息
     * @param scheduleDate 排程日期
     * @param shift 班次
     * @return 排程数量
     */
    int calculateScheduleQuantity(ScheduleContext context, MonthPlanProd prod,
                                   LhMachineInfo machine, Date scheduleDate, String shift);

    /**
     * 生成排程结果
     *
     * @param context 排程上下文
     * @param prod 计划产品
     * @param machine 机台信息
     * @param scheduleDate 排程日期
     * @param shift 班次
     * @param quantity 排程数量
     * @return 排程结果
     */
    LhScheduleResult generateScheduleResult(ScheduleContext context, MonthPlanProd prod,
                                             LhMachineInfo machine, Date scheduleDate,
                                             String shift, int quantity);
}
