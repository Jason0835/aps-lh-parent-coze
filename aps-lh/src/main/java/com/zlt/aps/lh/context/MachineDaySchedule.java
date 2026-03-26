package com.zlt.aps.lh.context;

import com.zlt.aps.lh.api.domain.LhScheduleResult;
import com.zlt.aps.lh.api.enums.ShiftEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 机台日排程情况
 * 记录某机台在某日的排程情况
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Data
public class MachineDaySchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 机台编号
     */
    private String machineCode;

    /**
     * 排程日期
     */
    private Date scheduleDate;

    /**
     * 班次排程情况列表
     */
    private List<ShiftSchedule> shiftSchedules = new ArrayList<>();

    /**
     * 已排产数量（汇总）
     */
    private int scheduledQuantity;

    /**
     * 当前模具编号
     */
    private String currentMouldCode;

    /**
     * 当前物料编号
     */
    private String currentMaterialCode;

    /**
     * 是否有停机计划
     */
    private boolean hasShutdown;

    /**
     * 是否有保养计划
     */
    private boolean hasMaintenance;

    /**
     * 是否可用
     */
    private boolean available = true;

    /**
     * 添加排程结果
     *
     * @param result 排程结果
     */
    public void addResult(LhScheduleResult result) {
        // 查找或创建班次排程情况
        ShiftSchedule shiftSchedule = getOrCreateShiftSchedule(result.getShift());
        shiftSchedule.addResult(result);

        // 更新汇总数量
        this.scheduledQuantity += (result.getPlanQuantity() != null ? result.getPlanQuantity() : 0);

        // 更新当前模具和物料
        this.currentMouldCode = result.getMouldCode();
        this.currentMaterialCode = result.getMaterialCode();
    }

    /**
     * 获取或创建班次排程情况
     *
     * @param shift 班次
     * @return 班次排程情况
     */
    public ShiftSchedule getOrCreateShiftSchedule(String shift) {
        for (ShiftSchedule schedule : shiftSchedules) {
            if (schedule.getShift().equals(shift)) {
                return schedule;
            }
        }
        ShiftSchedule schedule = new ShiftSchedule();
        schedule.setShift(shift);
        shiftSchedules.add(schedule);
        return schedule;
    }

    /**
     * 获取指定班次的排程情况
     *
     * @param shift 班次
     * @return 班次排程情况
     */
    public ShiftSchedule getShiftSchedule(String shift) {
        for (ShiftSchedule schedule : shiftSchedules) {
            if (schedule.getShift().equals(shift)) {
                return schedule;
            }
        }
        return null;
    }

    /**
     * 获取指定班次的已排数量
     *
     * @param shift 班次
     * @return 已排数量
     */
    public int getShiftScheduledQuantity(String shift) {
        ShiftSchedule schedule = getShiftSchedule(shift);
        return schedule != null ? schedule.getScheduledQuantity() : 0;
    }

    /**
     * 获取指定班次的排程结果列表
     *
     * @param shift 班次
     * @return 排程结果列表
     */
    public List<LhScheduleResult> getShiftResults(String shift) {
        ShiftSchedule schedule = getShiftSchedule(shift);
        return schedule != null ? schedule.getResults() : new ArrayList<>();
    }

    /**
     * 获取班次数量
     *
     * @return 班次数量
     */
    public int getShiftCount() {
        return shiftSchedules.size();
    }

    /**
     * 班次排程情况
     */
    @Data
    public static class ShiftSchedule implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * 班次
         */
        private String shift;

        /**
         * 排程结果列表
         */
        private List<LhScheduleResult> results = new ArrayList<>();

        /**
         * 已排产数量
         */
        private int scheduledQuantity;

        /**
         * 最大序号
         */
        private int maxSeq;

        /**
         * 添加排程结果
         *
         * @param result 排程结果
         */
        public void addResult(LhScheduleResult result) {
            this.results.add(result);
            this.scheduledQuantity += (result.getPlanQuantity() != null ? result.getPlanQuantity() : 0);
            int seq = result.getSeq() != null ? result.getSeq() : 0;
            if (seq > this.maxSeq) {
                this.maxSeq = seq;
            }
        }

        /**
         * 获取下一个序号
         *
         * @return 下一个序号
         */
        public int getNextSeq() {
            return this.maxSeq + 1;
        }

        /**
         * 获取最后一个排程结果
         *
         * @return 最后一个排程结果
         */
        public LhScheduleResult getLastResult() {
            return results.isEmpty() ? null : results.get(results.size() - 1);
        }
    }
}
