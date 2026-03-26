package com.zlt.aps.lh.api.enums;

/**
 * 未排产原因枚举
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
public enum UnscheduledReasonEnum {

    NO_MOULD("NO_MOULD", "无可用模具"),
    NO_MACHINE("NO_MACHINE", "无可用机台"),
    CHANGE_MOULD_LIMIT("CHANGE_MOULD_LIMIT", "换模次数超限"),
    STOCK_INSUFFICIENT("STOCK_INSUFFICIENT", "胎胚库存不足"),
    DEVICE_FAULT("DEVICE_FAULT", "设备故障"),
    OTHER("OTHER", "其他原因");

    private final String code;
    private final String desc;

    UnscheduledReasonEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static UnscheduledReasonEnum getByCode(String code) {
        for (UnscheduledReasonEnum e : values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }
}
