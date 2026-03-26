package com.zlt.aps.lh.api.enums;

/**
 * 施工阶段枚举
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
public enum ConstructionStageEnum {

    NO_PROCESS("00", "无工艺"),
    TRIAL("01", "试制"),
    QUANTITY_TRIAL("02", "量试"),
    FORMAL("03", "正式");

    private final String code;
    private final String desc;

    ConstructionStageEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static ConstructionStageEnum getByCode(String code) {
        for (ConstructionStageEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
