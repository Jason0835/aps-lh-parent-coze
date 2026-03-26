package com.zlt.aps.lh.context;

import com.zlt.aps.lh.api.domain.*;
import com.zlt.aps.lh.api.dto.ScheduleDataContext;
import com.zlt.aps.lh.api.dto.ScheduleRequest;
import com.zlt.aps.lh.api.enums.ScheduleStatusEnum;
import com.zlt.aps.lh.api.enums.UnscheduledReasonEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * 排程上下文
 * 保存排程过程中的临时状态和结果
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Data
public class ScheduleContext implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 排程请求参数
     */
    private ScheduleRequest request;

    /**
     * 数据上下文
     */
    private ScheduleDataContext dataContext;

    /**
     * 已排程结果列表
     */
    private List<LhScheduleResult> scheduleResults = new ArrayList<>();

    /**
     * 未排程计划列表（包含原因）
     */
    private List<UnscheduledPlan> unscheduledPlans = new ArrayList<>();

    /**
     * 机台日排程情况Map
     * key: 机台编号_日期
     * value: 机台日排程情况
     */
    private Map<String, MachineDaySchedule> machineDayScheduleMap = new HashMap<>();

    /**
     * 模具日排程情况Map
     * key: 模具编号_日期
     * value: 模具日排程情况
     */
    private Map<String, MouldDaySchedule> mouldDayScheduleMap = new HashMap<>();

    /**
     * 物料剩余数量Map
     * key: 月度计划产品ID
     * value: 剩余数量
     */
    private Map<Long, Integer> materialRemainingMap = new HashMap<>();

    /**
     * 排程日志列表
     */
    private List<ScheduleLog> scheduleLogs = new ArrayList<>();

    /**
     * 排程开始时间
     */
    private Date scheduleStartTime;

    /**
     * 排程结束时间
     */
    private Date scheduleEndTime;

    /**
     * 排程状态
     */
    private ScheduleStatusEnum status = ScheduleStatusEnum.INIT;

    /**
     * 排程统计信息
     */
    private ScheduleStatistics statistics = new ScheduleStatistics();

    /**
     * 初始化排程上下文
     */
    public void init() {
        this.scheduleStartTime = new Date();
        this.status = ScheduleStatusEnum.IN_PROGRESS;
        this.scheduleResults = new ArrayList<>();
        this.unscheduledPlans = new ArrayList<>();
        this.machineDayScheduleMap = new HashMap<>();
        this.mouldDayScheduleMap = new HashMap<>();
        this.scheduleLogs = new ArrayList<>();

        // 初始化物料剩余数量
        if (dataContext.getMonthPlanProdList() != null) {
            for (MonthPlanProd prod : dataContext.getMonthPlanProdList()) {
                int remaining = (prod.getPlanQuantity() != null ? prod.getPlanQuantity() : 0)
                        - (prod.getScheduledQuantity() != null ? prod.getScheduledQuantity() : 0);
                materialRemainingMap.put(prod.getId(), Math.max(remaining, 0));
            }
        }

        addLog("排程上下文初始化完成");
    }

    /**
     * 完成排程
     */
    public void complete() {
        this.scheduleEndTime = new Date();
        this.status = ScheduleStatusEnum.COMPLETED;
        calculateStatistics();
        addLog("排程完成");
    }

    /**
     * 排程失败
     */
    public void fail(String reason) {
        this.scheduleEndTime = new Date();
        this.status = ScheduleStatusEnum.FAILED;
        addLog("排程失败: " + reason);
    }

    /**
     * 添加排程结果
     *
     * @param result 排程结果
     */
    public void addScheduleResult(LhScheduleResult result) {
        this.scheduleResults.add(result);

        // 更新物料剩余数量
        Long planProdId = result.getMonthPlanProdId();
        if (planProdId != null) {
            Integer remaining = materialRemainingMap.getOrDefault(planProdId, 0);
            int planQty = result.getPlanQuantity() != null ? result.getPlanQuantity() : 0;
            materialRemainingMap.put(planProdId, Math.max(remaining - planQty, 0));
        }

        // 更新机台日排程情况
        updateMachineDaySchedule(result);

        // 更新模具日排程情况
        updateMouldDaySchedule(result);
    }

    /**
     * 添加未排程计划
     *
     * @param prod 计划产品
     * @param reason 未排程原因
     */
    public void addUnscheduledPlan(MonthPlanProd prod, UnscheduledReasonEnum reason) {
        UnscheduledPlan unscheduled = new UnscheduledPlan();
        unscheduled.setMonthPlanProd(prod);
        unscheduled.setReason(reason);
        unscheduled.setRemainingQuantity(materialRemainingMap.getOrDefault(prod.getId(), 0));
        this.unscheduledPlans.add(unscheduled);
    }

    /**
     * 添加排程日志
     *
     * @param message 日志消息
     */
    public void addLog(String message) {
        ScheduleLog log = new ScheduleLog();
        log.setTimestamp(new Date());
        log.setMessage(message);
        this.scheduleLogs.add(log);
    }

    /**
     * 获取机台日排程情况
     *
     * @param machineCode 机台编号
     * @param scheduleDate 排程日期
     * @return 机台日排程情况
     */
    public MachineDaySchedule getMachineDaySchedule(String machineCode, Date scheduleDate) {
        String key = buildMachineDayKey(machineCode, scheduleDate);
        return machineDayScheduleMap.computeIfAbsent(key, k -> {
            MachineDaySchedule schedule = new MachineDaySchedule();
            schedule.setMachineCode(machineCode);
            schedule.setScheduleDate(scheduleDate);
            return schedule;
        });
    }

    /**
     * 获取模具日排程情况
     *
     * @param mouldCode 模具编号
     * @param scheduleDate 排程日期
     * @return 模具日排程情况
     */
    public MouldDaySchedule getMouldDaySchedule(String mouldCode, Date scheduleDate) {
        String key = buildMouldDayKey(mouldCode, scheduleDate);
        return mouldDayScheduleMap.computeIfAbsent(key, k -> {
            MouldDaySchedule schedule = new MouldDaySchedule();
            schedule.setMouldCode(mouldCode);
            schedule.setScheduleDate(scheduleDate);
            return schedule;
        });
    }

    /**
     * 获取物料剩余数量
     *
     * @param planProdId 月度计划产品ID
     * @return 剩余数量
     */
    public int getRemainingQuantity(Long planProdId) {
        return materialRemainingMap.getOrDefault(planProdId, 0);
    }

    /**
     * 检查物料是否已排完
     *
     * @param planProdId 月度计划产品ID
     * @return 是否已排完
     */
    public boolean isMaterialCompleted(Long planProdId) {
        return getRemainingQuantity(planProdId) <= 0;
    }

    /**
     * 更新机台日排程情况
     *
     * @param result 排程结果
     */
    private void updateMachineDaySchedule(LhScheduleResult result) {
        MachineDaySchedule schedule = getMachineDaySchedule(result.getMachineCode(), result.getScheduleDate());
        schedule.addResult(result);
    }

    /**
     * 更新模具日排程情况
     *
     * @param result 排程结果
     */
    private void updateMouldDaySchedule(LhScheduleResult result) {
        if (result.getMouldCode() != null) {
            MouldDaySchedule schedule = getMouldDaySchedule(result.getMouldCode(), result.getScheduleDate());
            schedule.addResult(result);
        }
    }

    /**
     * 构建机台日Key
     */
    private String buildMachineDayKey(String machineCode, Date scheduleDate) {
        return machineCode + "_" + scheduleDate.getTime();
    }

    /**
     * 构建模具日Key
     */
    private String buildMouldDayKey(String mouldCode, Date scheduleDate) {
        return mouldCode + "_" + scheduleDate.getTime();
    }

    /**
     * 计算排程统计信息
     */
    private void calculateStatistics() {
        statistics.setTotalPlanCount(dataContext.getMonthPlanProdList() != null ? dataContext.getMonthPlanProdList().size() : 0);
        statistics.setScheduledPlanCount(scheduleResults.stream()
                .map(LhScheduleResult::getMonthPlanProdId)
                .distinct()
                .count());
        statistics.setUnscheduledPlanCount(unscheduledPlans.size());
        statistics.setTotalScheduleResultCount(scheduleResults.size());
        statistics.setTotalScheduledQuantity(scheduleResults.stream()
                .mapToInt(r -> r.getPlanQuantity() != null ? r.getPlanQuantity() : 0)
                .sum());
        statistics.setTotalUnscheduledQuantity(unscheduledPlans.stream()
                .mapToInt(u -> u.getRemainingQuantity() != null ? u.getRemainingQuantity() : 0)
                .sum());
        statistics.setScheduleDuration(scheduleEndTime.getTime() - scheduleStartTime.getTime());
    }

    /**
     * 未排程计划
     */
    @Data
    public static class UnscheduledPlan implements Serializable {
        private static final long serialVersionUID = 1L;
        private MonthPlanProd monthPlanProd;
        private UnscheduledReasonEnum reason;
        private Integer remainingQuantity;
    }

    /**
     * 排程日志
     */
    @Data
    public static class ScheduleLog implements Serializable {
        private static final long serialVersionUID = 1L;
        private Date timestamp;
        private String message;
    }

    /**
     * 排程统计信息
     */
    @Data
    public static class ScheduleStatistics implements Serializable {
        private static final long serialVersionUID = 1L;
        private int totalPlanCount;
        private long scheduledPlanCount;
        private int unscheduledPlanCount;
        private int totalScheduleResultCount;
        private int totalScheduledQuantity;
        private int totalUnscheduledQuantity;
        private long scheduleDuration;
    }
}
