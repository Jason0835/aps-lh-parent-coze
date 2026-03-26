package com.zlt.aps.lh.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 工作日历实体类
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@ApiModel(value = "工作日历对象", description = "工作日历表实体对象")
@Data
@TableName(value = "T_MDM_WORK_CALENDAR")
public class WorkCalendar implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分厂编号")
    @TableField(value = "FACTORY_CODE")
    private String factoryCode;

    @ApiModelProperty(value = "日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "CALENDAR_DATE")
    private Date calendarDate;

    @ApiModelProperty(value = "是否工作日(0-否;1-是)")
    @TableField(value = "IS_WORK_DAY")
    private String isWorkDay;

    @ApiModelProperty(value = "班次1是否开产(0-否;1-是)")
    @TableField(value = "SHIFT1_IS_OPEN")
    private String shift1IsOpen;

    @ApiModelProperty(value = "班次1开始时间")
    @TableField(value = "SHIFT1_START_TIME")
    private String shift1StartTime;

    @ApiModelProperty(value = "班次1结束时间")
    @TableField(value = "SHIFT1_END_TIME")
    private String shift1EndTime;

    @ApiModelProperty(value = "班次2是否开产(0-否;1-是)")
    @TableField(value = "SHIFT2_IS_OPEN")
    private String shift2IsOpen;

    @ApiModelProperty(value = "班次2开始时间")
    @TableField(value = "SHIFT2_START_TIME")
    private String shift2StartTime;

    @ApiModelProperty(value = "班次2结束时间")
    @TableField(value = "SHIFT2_END_TIME")
    private String shift2EndTime;

    @ApiModelProperty(value = "班次3是否开产(0-否;1-是)")
    @TableField(value = "SHIFT3_IS_OPEN")
    private String shift3IsOpen;

    @ApiModelProperty(value = "班次3开始时间")
    @TableField(value = "SHIFT3_START_TIME")
    private String shift3StartTime;

    @ApiModelProperty(value = "班次3结束时间")
    @TableField(value = "SHIFT3_END_TIME")
    private String shift3EndTime;

    @ApiModelProperty(value = "备注")
    @TableField(value = "REMARK")
    private String remark;

    @ApiModelProperty(value = "创建者")
    @TableField(value = "CREATE_BY")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "CREATE_TIME")
    private Date createTime;

    @ApiModelProperty(value = "更新者")
    @TableField(value = "UPDATE_BY")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "UPDATE_TIME")
    private Date updateTime;

    @ApiModelProperty(value = "删除标识(0-正常;1-删除)")
    @TableField(value = "IS_DELETE")
    private Integer isDelete;
}
