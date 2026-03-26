package com.zlt.aps.lh.service.impl;

import com.zlt.aps.lh.context.ScheduleContext;
import com.zlt.aps.lh.api.domain.LhScheduleResult;
import com.zlt.aps.lh.api.dto.ScheduleRequest;
import com.zlt.aps.lh.engine.ScheduleEngine;
import com.zlt.aps.lh.mapper.LhScheduleResultMapper;
import com.zlt.aps.lh.service.LhScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 硫化排程服务实现类
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Slf4j
@Service
public class LhScheduleServiceImpl implements LhScheduleService {

    @Autowired
    private ScheduleEngine scheduleEngine;

    @Autowired
    private LhScheduleResultMapper scheduleResultMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ScheduleResultDTO schedule(ScheduleRequest request) {
        log.info("开始执行硫化排程，请求参数: {}", request);

        try {
            // 验证请求
            ScheduleEngine.ValidationResult validation = scheduleEngine.validateRequest(request);
            if (!validation.isValid()) {
                return ScheduleResultDTO.fail(validation.getMessage());
            }

            // 是否覆盖已有排程
            if (Boolean.TRUE.equals(request.getOverrideExisting())) {
                log.info("删除已有排程结果，日期范围: {} - {}", 
                        request.getScheduleStartDate(), request.getScheduleEndDate());
                // 删除已有排程结果
                deleteExistingScheduleResults(request);
            }

            // 执行排程
            ScheduleContext context = scheduleEngine.execute(request);

            // 保存排程结果
            List<LhScheduleResult> scheduleResults = context.getScheduleResults();
            if (scheduleResults != null && !scheduleResults.isEmpty()) {
                saveScheduleResults(scheduleResults);
                log.info("保存排程结果成功，数量: {}", scheduleResults.size());
            }

            // 返回结果
            return ScheduleResultDTO.success(
                    scheduleResults,
                    context.getUnscheduledPlans(),
                    context.getStatistics()
            );

        } catch (Exception e) {
            log.error("排程执行失败: {}", e.getMessage(), e);
            return ScheduleResultDTO.fail("排程执行失败: " + e.getMessage());
        }
    }

    @Override
    public List<LhScheduleResult> queryScheduleResults(Date scheduleDate, String machineCode) {
        log.info("查询排程结果，日期: {}, 机台: {}", scheduleDate, machineCode);
        if (machineCode != null && !machineCode.isEmpty()) {
            return scheduleResultMapper.selectByMachineCode(machineCode, scheduleDate);
        }
        return scheduleResultMapper.selectByScheduleDate(scheduleDate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateScheduleStatus(Long id, String status) {
        log.info("更新排程状态，ID: {}, 状态: {}", id, status);
        try {
            LhScheduleResult result = new LhScheduleResult();
            result.setId(id);
            result.setStatus(status);
            result.setUpdateTime(new Date());
            return scheduleResultMapper.updateById(result) > 0;
        } catch (Exception e) {
            log.error("更新排程状态失败: {}", e.getMessage(), e);
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteScheduleResults(Date scheduleDate) {
        log.info("删除排程结果，日期: {}", scheduleDate);
        try {
            return scheduleResultMapper.deleteByScheduleDate(scheduleDate) >= 0;
        } catch (Exception e) {
            log.error("删除排程结果失败: {}", e.getMessage(), e);
            return false;
        }
    }

    /**
     * 删除已有排程结果
     *
     * @param request 排程请求
     */
    private void deleteExistingScheduleResults(ScheduleRequest request) {
        Date startDate = request.getScheduleStartDate();
        Date endDate = request.getScheduleEndDate();

        // 简化处理：删除整个日期范围内的排程结果
        // 实际应该按日期逐天删除
        Date currentDate = startDate;
        while (!currentDate.after(endDate)) {
            scheduleResultMapper.deleteByScheduleDate(currentDate);
            currentDate = addDays(currentDate, 1);
        }
    }

    /**
     * 保存排程结果
     *
     * @param scheduleResults 排程结果列表
     */
    private void saveScheduleResults(List<LhScheduleResult> scheduleResults) {
        // 批量插入
        int batchSize = 100;
        for (int i = 0; i < scheduleResults.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, scheduleResults.size());
            List<LhScheduleResult> batch = scheduleResults.subList(i, endIndex);
            scheduleResultMapper.batchInsert(batch);
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
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(java.util.Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
}
