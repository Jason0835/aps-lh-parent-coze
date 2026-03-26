package com.zlt.aps.lh.api.enums;

/**
 * 排程策略类型枚举
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
public enum StrategyTypeEnum {

    CONTINUE("CONTINUE", "续作排程"),
    NEW_SKU("NEW_SKU", "新增排程"),
    ADJUST("ADJUST", "调整排程");

    private final String code;
    private final String desc;

    StrategyTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static StrategyTypeEnum getByCode(String code) {
        for (StrategyTypeEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
