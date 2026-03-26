package com.zlt.aps.lh.api.enums;

/**
 * 换模类型枚举
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
public enum ChangeMouldTypeEnum {

    NORMAL("1", "正规"),
    CHANGE_LETTER("2", "更换活字块"),
    DRY_ICE_CLEAN("3", "模具干冰清洗"),
    SAND_BLAST_CLEAN("4", "模具喷砂清洗");

    private final String code;
    private final String desc;

    ChangeMouldTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ChangeMouldTypeEnum getByCode(String code) {
        for (ChangeMouldTypeEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
