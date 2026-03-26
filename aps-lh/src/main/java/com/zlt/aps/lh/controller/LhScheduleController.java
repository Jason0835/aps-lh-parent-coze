package com.zlt.aps.lh.controller;

import com.zlt.aps.lh.api.domain.LhScheduleResult;
import com.zlt.aps.lh.api.dto.ScheduleRequest;
import com.zlt.aps.lh.service.LhScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 硫化排程控制器
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Slf4j
@RestController
@RequestMapping("/api/lh/schedule")
@Api(tags = "硫化排程管理")
public class LhScheduleController {

    @Autowired
    private LhScheduleService scheduleService;

    /**
     * 执行排程
     *
     * @param request 排程请求
     * @return 排程结果
     */
    @PostMapping("/execute")
    @ApiOperation(value = "执行硫化排程", notes = "根据请求参数执行硫化排程，返回排程结果")
    public ResponseEntity<Map<String, Object>> executeSchedule(@RequestBody ScheduleRequest request) {
        log.info("接收排程请求: {}", request);
        Map<String, Object> result = new HashMap<>();

        try {
            LhScheduleService.ScheduleResultDTO scheduleResult = scheduleService.schedule(request);

            result.put("success", scheduleResult.isSuccess());
            result.put("message", scheduleResult.getMessage());
            result.put("data", Map.of(
                    "scheduleResults", scheduleResult.getScheduleResults() != null ? scheduleResult.getScheduleResults() : List.of(),
                    "unscheduledPlans", scheduleResult.getUnscheduledPlans() != null ? scheduleResult.getUnscheduledPlans() : List.of(),
                    "statistics", scheduleResult.getStatistics() != null ? scheduleResult.getStatistics() : Map.of()
            ));

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("执行排程失败: {}", e.getMessage(), e);
            result.put("success", false);
            result.put("message", "执行排程失败: " + e.getMessage());
            return ResponseEntity.internalServerError().body(result);
        }
    }

    /**
     * 查询排程结果
     *
     * @param scheduleDate 排程日期
     * @param machineCode 机台编号（可选）
     * @return 排程结果列表
     */
    @GetMapping("/query")
    @ApiOperation(value = "查询排程结果", notes = "根据日期和机台查询排程结果")
    public ResponseEntity<Map<String, Object>> queryScheduleResults(
            @ApiParam(value = "排程日期", required = true)
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date scheduleDate,
            @ApiParam(value = "机台编号")
            @RequestParam(required = false) String machineCode) {

        log.info("查询排程结果，日期: {}, 机台: {}", scheduleDate, machineCode);
        Map<String, Object> result = new HashMap<>();

        try {
            List<LhScheduleResult> scheduleResults = scheduleService.queryScheduleResults(scheduleDate, machineCode);

            result.put("success", true);
            result.put("message", "查询成功");
            result.put("data", scheduleResults);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("查询排程结果失败: {}", e.getMessage(), e);
            result.put("success", false);
            result.put("message", "查询失败: " + e.getMessage());
            return ResponseEntity.internalServerError().body(result);
        }
    }

    /**
     * 更新排程状态
     *
     * @param id 排程结果ID
     * @param status 状态
     * @return 更新结果
     */
    @PutMapping("/status")
    @ApiOperation(value = "更新排程状态", notes = "更新指定排程结果的状态")
    public ResponseEntity<Map<String, Object>> updateScheduleStatus(
            @ApiParam(value = "排程结果ID", required = true)
            @RequestParam Long id,
            @ApiParam(value = "状态", required = true)
            @RequestParam String status) {

        log.info("更新排程状态，ID: {}, 状态: {}", id, status);
        Map<String, Object> result = new HashMap<>();

        try {
            boolean success = scheduleService.updateScheduleStatus(id, status);

            result.put("success", success);
            result.put("message", success ? "更新成功" : "更新失败");

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("更新排程状态失败: {}", e.getMessage(), e);
            result.put("success", false);
            result.put("message", "更新失败: " + e.getMessage());
            return ResponseEntity.internalServerError().body(result);
        }
    }

    /**
     * 删除排程结果
     *
     * @param scheduleDate 排程日期
     * @return 删除结果
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除排程结果", notes = "删除指定日期的排程结果")
    public ResponseEntity<Map<String, Object>> deleteScheduleResults(
            @ApiParam(value = "排程日期", required = true)
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date scheduleDate) {

        log.info("删除排程结果，日期: {}", scheduleDate);
        Map<String, Object> result = new HashMap<>();

        try {
            boolean success = scheduleService.deleteScheduleResults(scheduleDate);

            result.put("success", success);
            result.put("message", success ? "删除成功" : "删除失败");

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("删除排程结果失败: {}", e.getMessage(), e);
            result.put("success", false);
            result.put("message", "删除失败: " + e.getMessage());
            return ResponseEntity.internalServerError().body(result);
        }
    }

    /**
     * 健康检查
     *
     * @return 健康状态
     */
    @GetMapping("/health")
    @ApiOperation(value = "健康检查", notes = "检查服务是否正常运行")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "服务正常运行");
        result.put("timestamp", new Date());
        return ResponseEntity.ok(result);
    }
}
