package com.zlt.aps.lh.api.enums;

/**
 * 班次枚举
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
public enum ShiftEnum {

    NIGHT("NIGHT", "夜班", "00:00-08:00"),
    MORNING("MORNING", "早班", "08:00-16:00"),
    AFTERNOON("AFTERNOON", "中班", "16:00-24:00");

    private final String code;
    private final String desc;
    private final String timeRange;

    ShiftEnum(String code, String desc, String timeRange) {
        this.code = code;
        this.desc = desc;
        this.timeRange = timeRange;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public static ShiftEnum getByCode(String code) {
        for (ShiftEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
