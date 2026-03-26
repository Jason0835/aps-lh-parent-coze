package com.zlt.aps.lh.api.enums;

/**
 * 供应链优先级枚举
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
public enum SupplyChainPriorityEnum {

    HIGH(1, "高优先级"),
    CYCLE(2, "周期排产"),
    MIDDLE(3, "中优先级"),
    MATCH(4, "搭配排产");

    private final int priority;
    private final String desc;

    SupplyChainPriorityEnum(int priority, String desc) {
        this.priority = priority;
        this.desc = desc;
    }

    public int getPriority() {
        return priority;
    }

    public String getDesc() {
        return desc;
    }

    public static SupplyChainPriorityEnum getByPriority(int priority) {
        for (SupplyChainPriorityEnum e : values()) {
            if (e.getPriority() == priority) {
                return e;
            }
        }
        return null;
    }
}
