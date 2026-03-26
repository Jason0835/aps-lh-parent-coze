package com.zlt.aps.lh.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 排程请求DTO
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@ApiModel(value = "排程请求对象", description = "硫化排程请求参数")
@Data
public class ScheduleRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分厂编号", required = true)
    private String factoryCode;

    @ApiModelProperty(value = "计划编号", required = true)
    private String planCode;

    @ApiModelProperty(value = "计划月份", required = true)
    @JsonFormat(pattern = "yyyy-MM")
    private Date planMonth;

    @ApiModelProperty(value = "排程开始日期", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date scheduleStartDate;

    @ApiModelProperty(value = "排程结束日期", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date scheduleEndDate;

    @ApiModelProperty(value = "排程策略类型(1-均衡策略;2-优先策略;3-批量策略)", required = true)
    private String strategyType;

    @ApiModelProperty(value = "是否考虑换模时间")
    private Boolean considerMouldChangeTime = true;

    @ApiModelProperty(value = "是否考虑清洗时间")
    private Boolean considerCleaningTime = true;

    @ApiModelProperty(value = "是否考虑设备保养")
    private Boolean considerMaintenance = true;

    @ApiModelProperty(value = "最小生产批量")
    private Integer minBatchSize;

    @ApiModelProperty(value = "最大生产批量")
    private Integer maxBatchSize;

    @ApiModelProperty(value = "排程天数")
    private Integer scheduleDays;

    @ApiModelProperty(value = "是否覆盖已有排程")
    private Boolean overrideExisting = false;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @ApiModelProperty(value = "备注")
    private String remark;
}
