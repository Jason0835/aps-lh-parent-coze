package com.zlt.aps.lh.api.enums;

/**
 * 生产状态枚举
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
public enum ProductionStatusEnum {

    NOT_STARTED("0", "未生产"),
    IN_PRODUCTION("1", "生产中"),
    COMPLETED("2", "生产完成");

    private final String code;
    private final String desc;

    ProductionStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ProductionStatusEnum getByCode(String code) {
        for (ProductionStatusEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
