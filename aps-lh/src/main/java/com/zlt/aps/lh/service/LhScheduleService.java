package com.zlt.aps.lh.service;

import com.zlt.aps.lh.context.ScheduleContext;
import com.zlt.aps.lh.api.domain.LhScheduleResult;
import com.zlt.aps.lh.api.dto.ScheduleRequest;

import java.util.List;

/**
 * 硫化排程服务接口
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
public interface LhScheduleService {

    /**
     * 执行排程
     *
     * @param request 排程请求
     * @return 排程结果
     */
    ScheduleResultDTO schedule(ScheduleRequest request);

    /**
     * 查询排程结果
     *
     * @param scheduleDate 排程日期
     * @param machineCode 机台编号（可选）
     * @return 排程结果列表
     */
    List<LhScheduleResult> queryScheduleResults(java.util.Date scheduleDate, String machineCode);

    /**
     * 更新排程状态
     *
     * @param id 排程结果ID
     * @param status 状态
     * @return 是否成功
     */
    boolean updateScheduleStatus(Long id, String status);

    /**
     * 删除排程结果
     *
     * @param scheduleDate 排程日期
     * @return 是否成功
     */
    boolean deleteScheduleResults(java.util.Date scheduleDate);

    /**
     * 排程结果DTO
     */
    class ScheduleResultDTO {
        private boolean success;
        private String message;
        private List<LhScheduleResult> scheduleResults;
        private List<ScheduleContext.UnscheduledPlan> unscheduledPlans;
        private ScheduleContext.ScheduleStatistics statistics;

        public static ScheduleResultDTO success(List<LhScheduleResult> scheduleResults, 
                                                 List<ScheduleContext.UnscheduledPlan> unscheduledPlans,
                                                 ScheduleContext.ScheduleStatistics statistics) {
            ScheduleResultDTO dto = new ScheduleResultDTO();
            dto.setSuccess(true);
            dto.setMessage("排程成功");
            dto.setScheduleResults(scheduleResults);
            dto.setUnscheduledPlans(unscheduledPlans);
            dto.setStatistics(statistics);
            return dto;
        }

        public static ScheduleResultDTO fail(String message) {
            ScheduleResultDTO dto = new ScheduleResultDTO();
            dto.setSuccess(false);
            dto.setMessage(message);
            return dto;
        }

        // Getters and Setters
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<LhScheduleResult> getScheduleResults() {
            return scheduleResults;
        }

        public void setScheduleResults(List<LhScheduleResult> scheduleResults) {
            this.scheduleResults = scheduleResults;
        }

        public List<ScheduleContext.UnscheduledPlan> getUnscheduledPlans() {
            return unscheduledPlans;
        }

        public void setUnscheduledPlans(List<ScheduleContext.UnscheduledPlan> unscheduledPlans) {
            this.unscheduledPlans = unscheduledPlans;
        }

        public ScheduleContext.ScheduleStatistics getStatistics() {
            return statistics;
        }

        public void setStatistics(ScheduleContext.ScheduleStatistics statistics) {
            this.statistics = statistics;
        }
    }
}
