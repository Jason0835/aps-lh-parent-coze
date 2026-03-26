package com.zlt.aps.lh.validator;

import com.zlt.aps.lh.context.ScheduleContext;
import com.zlt.aps.lh.domain.LhMachineInfo;
import com.zlt.aps.lh.domain.MonthPlanProd;
import com.zlt.aps.lh.api.dto.ScheduleDataContext;
import com.zlt.aps.lh.api.dto.ScheduleRequest;
import com.zlt.aps.lh.validator.impl.CapacityValidator;
import com.zlt.aps.lh.validator.impl.MachineAvailabilityValidator;
import com.zlt.aps.lh.validator.impl.SpecifyMachineValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 约束校验器测试类
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@ExtendWith(MockitoExtension.class)
class ConstraintValidatorTest {

    @InjectMocks
    private MachineAvailabilityValidator machineAvailabilityValidator;

    @InjectMocks
    private SpecifyMachineValidator specifyMachineValidator;

    @InjectMocks
    private CapacityValidator capacityValidator;

    private ScheduleContext context;
    private MonthPlanProd testProd;
    private LhMachineInfo testMachine;
    private Date testDate;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() throws ParseException {
        // 初始化上下文
        context = new ScheduleContext();
        context.setRequest(new ScheduleRequest());
        context.setDataContext(new ScheduleDataContext());

        // 初始化测试产品
        testProd = new MonthPlanProd();
        testProd.setId(1L);
        testProd.setMaterialCode("MAT001");
        testProd.setMaterialDesc("测试物料");
        testProd.setPlanQuantity(1000);
        testProd.setScheduledQuantity(0);

        // 初始化测试机台
        testMachine = new LhMachineInfo();
        testMachine.setMachineCode("M001");
        testMachine.setMachineName("测试机台");
        testMachine.setIsEnable("1");

        // 初始化测试日期
        testDate = dateFormat.parse("2026-03-01");
    }

    @Test
    @DisplayName("测试校验结果成功")
    void testValidationResultSuccess() {
        ConstraintValidator.ValidationResult result = ConstraintValidator.ValidationResult.success();
        
        assertTrue(result.isValid());
        assertEquals("校验通过", result.getMessage());
    }

    @Test
    @DisplayName("测试校验结果失败")
    void testValidationResultFail() {
        ConstraintValidator.ValidationResult result = 
                ConstraintValidator.ValidationResult.fail("测试失败");
        
        assertFalse(result.isValid());
        assertEquals("测试失败", result.getMessage());
    }

    @Test
    @DisplayName("测试校验结果失败带错误码")
    void testValidationResultFailWithCode() {
        ConstraintValidator.ValidationResult result = 
                ConstraintValidator.ValidationResult.fail("测试失败", "ERR001");
        
        assertFalse(result.isValid());
        assertEquals("测试失败", result.getMessage());
        assertEquals("ERR001", result.getErrorCode());
    }

    @Test
    @DisplayName("测试机台可用性校验器名称")
    void testMachineAvailabilityValidatorName() {
        assertEquals("MachineAvailabilityValidator", machineAvailabilityValidator.getName());
    }

    @Test
    @DisplayName("测试定点机台校验器名称")
    void testSpecifyMachineValidatorName() {
        assertEquals("SpecifyMachineValidator", specifyMachineValidator.getName());
    }

    @Test
    @DisplayName("测试产能校验器名称")
    void testCapacityValidatorName() {
        assertEquals("CapacityValidator", capacityValidator.getName());
    }

    @Test
    @DisplayName("测试获取可用机台空列表")
    void testGetAvailableMachinesEmptyList() {
        List<LhMachineInfo> availableMachines = 
                machineAvailabilityValidator.getAvailableMachines(context, testProd, testDate, "1");
        
        assertNotNull(availableMachines);
        assertTrue(availableMachines.isEmpty());
    }

    @Test
    @DisplayName("测试机台信息启用状态")
    void testMachineEnabledStatus() {
        LhMachineInfo enabledMachine = new LhMachineInfo();
        enabledMachine.setIsEnable("1");
        assertEquals("1", enabledMachine.getIsEnable());

        LhMachineInfo disabledMachine = new LhMachineInfo();
        disabledMachine.setIsEnable("0");
        assertEquals("0", disabledMachine.getIsEnable());
    }
}
