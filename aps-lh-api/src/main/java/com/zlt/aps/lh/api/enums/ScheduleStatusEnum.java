package com.zlt.aps.lh.api.enums;

/**
 * 硫化排程状态枚举
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
public enum ScheduleStatusEnum {

    NOT_RELEASED("0", "未发布"),
    RELEASED("1", "已发布"),
    RELEASE_FAILED("2", "发布失败"),
    RELEASE_TIMEOUT("3", "超时发布"),
    RELEASE_WAITING("4", "待发布");

    private final String code;
    private final String desc;

    ScheduleStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ScheduleStatusEnum getByCode(String code) {
        for (ScheduleStatusEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
