package com.zlt.aps.lh.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 枚举类测试
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
class EnumTests {

    @Test
    @DisplayName("测试排程状态枚举")
    void testScheduleStatusEnum() {
        assertEquals("0", ScheduleStatusEnum.INIT.getCode());
        assertEquals("1", ScheduleStatusEnum.IN_PROGRESS.getCode());
        assertEquals("2", ScheduleStatusEnum.COMPLETED.getCode());
        assertEquals("3", ScheduleStatusEnum.FAILED.getCode());

        assertEquals("初始化", ScheduleStatusEnum.INIT.getDescription());
        assertEquals("进行中", ScheduleStatusEnum.IN_PROGRESS.getDescription());
        assertEquals("已完成", ScheduleStatusEnum.COMPLETED.getDescription());
        assertEquals("失败", ScheduleStatusEnum.FAILED.getDescription());

        assertEquals(ScheduleStatusEnum.INIT, ScheduleStatusEnum.getByCode("0"));
        assertEquals(ScheduleStatusEnum.COMPLETED, ScheduleStatusEnum.getByCode("2"));
        assertNull(ScheduleStatusEnum.getByCode("99"));
    }

    @Test
    @DisplayName("测试生产状态枚举")
    void testProductionStatusEnum() {
        assertEquals("0", ProductionStatusEnum.PENDING.getCode());
        assertEquals("1", ProductionStatusEnum.IN_PRODUCTION.getCode());
        assertEquals("2", ProductionStatusEnum.COMPLETED.getCode());
        assertEquals("3", ProductionStatusEnum.CANCELLED.getCode());

        assertEquals("待生产", ProductionStatusEnum.PENDING.getDescription());
        assertEquals("生产中", ProductionStatusEnum.IN_PRODUCTION.getDescription());
        assertEquals("已完成", ProductionStatusEnum.COMPLETED.getDescription());
        assertEquals("已取消", ProductionStatusEnum.CANCELLED.getDescription());
    }

    @Test
    @DisplayName("测试班次枚举")
    void testShiftEnum() {
        assertEquals("1", ShiftEnum.DAY_SHIFT.getCode());
        assertEquals("2", ShiftEnum.NIGHT_SHIFT.getCode());
        assertEquals("3", ShiftEnum.MIDDLE_SHIFT.getCode());

        assertEquals("白班", ShiftEnum.DAY_SHIFT.getDescription());
        assertEquals("夜班", ShiftEnum.NIGHT_SHIFT.getDescription());
        assertEquals("中班", ShiftEnum.MIDDLE_SHIFT.getDescription());
    }

    @Test
    @DisplayName("测试策略类型枚举")
    void testStrategyTypeEnum() {
        assertEquals("1", StrategyTypeEnum.BALANCED.getCode());
        assertEquals("2", StrategyTypeEnum.PRIORITY.getCode());
        assertEquals("3", StrategyTypeEnum.BATCH.getCode());

        assertEquals("均衡策略", StrategyTypeEnum.BALANCED.getDescription());
        assertEquals("优先策略", StrategyTypeEnum.PRIORITY.getDescription());
        assertEquals("批量策略", StrategyTypeEnum.BATCH.getDescription());
    }

    @Test
    @DisplayName("测试数据来源枚举")
    void testDataSourceEnum() {
        assertEquals("1", DataSourceEnum.MANUAL.getCode());
        assertEquals("2", DataSourceEnum.SAP.getCode());
        assertEquals("3", DataSourceEnum.MES.getCode());

        assertEquals("手工录入", DataSourceEnum.MANUAL.getDescription());
        assertEquals("SAP同步", DataSourceEnum.SAP.getDescription());
        assertEquals("MES同步", DataSourceEnum.MES.getDescription());
    }

    @Test
    @DisplayName("测试供应链优先级枚举")
    void testSupplyChainPriorityEnum() {
        assertEquals(1, SupplyChainPriorityEnum.HIGH.getCode());
        assertEquals(2, SupplyChainPriorityEnum.MEDIUM.getCode());
        assertEquals(3, SupplyChainPriorityEnum.LOW.getCode());

        assertEquals("高优先级", SupplyChainPriorityEnum.HIGH.getDescription());
        assertEquals("中优先级", SupplyChainPriorityEnum.MEDIUM.getDescription());
        assertEquals("低优先级", SupplyChainPriorityEnum.LOW.getDescription());
    }

    @Test
    @DisplayName("测试施工阶段枚举")
    void testConstructionStageEnum() {
        assertEquals(1, ConstructionStageEnum.TIRE_BUILDING.getCode());
        assertEquals(2, ConstructionStageEnum.TIRE_CURING.getCode());
        assertEquals(3, ConstructionStageEnum.TIRE_INSPECTION.getCode());

        assertEquals("成型", ConstructionStageEnum.TIRE_BUILDING.getDescription());
        assertEquals("硫化", ConstructionStageEnum.TIRE_CURING.getDescription());
        assertEquals("检测", ConstructionStageEnum.TIRE_INSPECTION.getDescription());
    }

    @Test
    @DisplayName("测试换模类型枚举")
    void testChangeMouldTypeEnum() {
        assertEquals("1", ChangeMouldTypeEnum.SAME_SPEC.getCode());
        assertEquals("2", ChangeMouldTypeEnum.DIFF_SPEC.getCode());
        assertEquals("3", ChangeMouldTypeEnum.FIRST.getCode());

        assertEquals("同规格换模", ChangeMouldTypeEnum.SAME_SPEC.getDescription());
        assertEquals("不同规格换模", ChangeMouldTypeEnum.DIFF_SPEC.getDescription());
        assertEquals("首次上模", ChangeMouldTypeEnum.FIRST.getDescription());
    }

    @Test
    @DisplayName("测试未排程原因枚举")
    void testUnscheduledReasonEnum() {
        assertEquals("CAPACITY_INSUFFICIENT", UnscheduledReasonEnum.CAPACITY_INSUFFICIENT.getCode());
        assertEquals("NO_AVAILABLE_MACHINE", UnscheduledReasonEnum.NO_AVAILABLE_MACHINE.getCode());
        assertEquals("NO_AVAILABLE_MOULD", UnscheduledReasonEnum.NO_AVAILABLE_MOULD.getCode());
        assertEquals("MACHINE_RESTRICTED", UnscheduledReasonEnum.MACHINE_RESTRICTED.getCode());

        assertEquals("产能不足", UnscheduledReasonEnum.CAPACITY_INSUFFICIENT.getDescription());
        assertEquals("无可用机台", UnscheduledReasonEnum.NO_AVAILABLE_MACHINE.getDescription());
        assertEquals("无可用模具", UnscheduledReasonEnum.NO_AVAILABLE_MOULD.getDescription());
        assertEquals("机台限制", UnscheduledReasonEnum.MACHINE_RESTRICTED.getDescription());
    }
}
