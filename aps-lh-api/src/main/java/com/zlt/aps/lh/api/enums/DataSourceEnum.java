package com.zlt.aps.lh.api.enums;

/**
 * 数据来源枚举
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
public enum DataSourceEnum {

    AUTO_SCHEDULE("0", "自动排程"),
    INSERT_ORDER("1", "插单"),
    IMPORT("2", "导入");

    private final String code;
    private final String desc;

    DataSourceEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static DataSourceEnum getByCode(String code) {
        for (DataSourceEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
