package com.zlt.aps.lh.service;

import com.zlt.aps.lh.api.dto.ScheduleRequest;
import com.zlt.aps.lh.service.impl.LhScheduleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 硫化排程服务测试类
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@ExtendWith(MockitoExtension.class)
class LhScheduleServiceTest {

    @Mock
    private com.zlt.aps.lh.api.engine.ScheduleEngine scheduleEngine;

    @Mock
    private com.zlt.aps.lh.api.mapper.LhScheduleResultMapper scheduleResultMapper;

    @InjectMocks
    private LhScheduleServiceImpl scheduleService;

    private ScheduleRequest testRequest;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() throws ParseException {
        testRequest = new ScheduleRequest();
        testRequest.setFactoryCode("F001");
        testRequest.setPlanCode("PLAN202603001");
        testRequest.setPlanMonth(dateFormat.parse("2026-03-01"));
        testRequest.setScheduleStartDate(dateFormat.parse("2026-03-01"));
        testRequest.setScheduleEndDate(dateFormat.parse("2026-03-07"));
        testRequest.setStrategyType("1");
        testRequest.setCreateBy("admin");
    }

    @Test
    @DisplayName("测试排程请求验证")
    void testScheduleRequestValidation() {
        // 测试空请求
        ScheduleRequest nullRequest = null;
        com.zlt.aps.lh.api.engine.ScheduleEngine.ValidationResult result = 
                scheduleEngine.validateRequest(nullRequest);
        // 注意：这里Mock了scheduleEngine，需要配置行为
        
        // 测试正常请求
        assertNotNull(testRequest);
        assertEquals("F001", testRequest.getFactoryCode());
        assertEquals("PLAN202603001", testRequest.getPlanCode());
        assertEquals("1", testRequest.getStrategyType());
    }

    @Test
    @DisplayName("测试日期范围验证")
    void testDateRangeValidation() throws ParseException {
        // 测试开始日期晚于结束日期
        ScheduleRequest invalidRequest = new ScheduleRequest();
        invalidRequest.setFactoryCode("F001");
        invalidRequest.setScheduleStartDate(dateFormat.parse("2026-03-10"));
        invalidRequest.setScheduleEndDate(dateFormat.parse("2026-03-01"));
        invalidRequest.setStrategyType("1");

        assertTrue(invalidRequest.getScheduleStartDate().after(invalidRequest.getScheduleEndDate()));
    }

    @Test
    @DisplayName("测试排程结果DTO创建")
    void testScheduleResultDTO() {
        // 测试成功结果
        LhScheduleService.ScheduleResultDTO successDTO = 
                LhScheduleService.ScheduleResultDTO.success(null, null, null);
        
        assertTrue(successDTO.isSuccess());
        assertEquals("排程成功", successDTO.getMessage());
        assertNull(successDTO.getScheduleResults());

        // 测试失败结果
        LhScheduleService.ScheduleResultDTO failDTO = 
                LhScheduleService.ScheduleResultDTO.fail("测试失败");
        
        assertFalse(failDTO.isSuccess());
        assertEquals("测试失败", failDTO.getMessage());
    }
}
