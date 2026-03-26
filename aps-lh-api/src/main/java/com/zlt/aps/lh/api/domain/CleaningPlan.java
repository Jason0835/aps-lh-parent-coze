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
 * 模具清洗计划实体类
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@ApiModel(value = "模具清洗计划对象", description = "模具清洗计划表实体对象")
@Data
@TableName(value = "T_LH_CLEANING_PLAN")
public class CleaningPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分厂编号")
    @TableField(value = "FACTORY_CODE")
    private String factoryCode;

    @ApiModelProperty(value = "硫化机台编号")
    @TableField(value = "LH_MACHINE_CODE")
    private String lhMachineCode;

    @ApiModelProperty(value = "硫化机台名称")
    @TableField(value = "LH_MACHINE_NAME")
    private String lhMachineName;

    @ApiModelProperty(value = "模具编号")
    @TableField(value = "MOULD_CODE")
    private String mouldCode;

    @ApiModelProperty(value = "模具名称")
    @TableField(value = "MOULD_NAME")
    private String mouldName;

    @ApiModelProperty(value = "清洗类型(1-干冰清洗;2-喷砂清洗)")
    @TableField(value = "CLEANING_TYPE")
    private String cleaningType;

    @ApiModelProperty(value = "计划清洗日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "PLAN_CLEANING_DATE")
    private Date planCleaningDate;

    @ApiModelProperty(value = "计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "PLAN_START_TIME")
    private Date planStartTime;

    @ApiModelProperty(value = "计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "PLAN_END_TIME")
    private Date planEndTime;

    @ApiModelProperty(value = "清洗时长(小时)")
    @TableField(value = "CLEANING_DURATION")
    private Integer cleaningDuration;

    @ApiModelProperty(value = "班次")
    @TableField(value = "SHIFT_CODE")
    private String shiftCode;

    @ApiModelProperty(value = "状态(0-计划;1-执行中;2-已完成)")
    @TableField(value = "STATUS")
    private String status;

    @ApiModelProperty(value = "上机时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "ON_MACHINE_TIME")
    private Date onMachineTime;

    @ApiModelProperty(value = "首次清洗时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "FIRST_CLEANING_TIME")
    private Date firstCleaningTime;

    @ApiModelProperty(value = "二次清洗时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "SECOND_CLEANING_TIME")
    private Date secondCleaningTime;

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

    @ApiModelProperty(value = "备注")
    @TableField(value = "REMARK")
    private String remark;

    @ApiModelProperty(value = "删除标识(0-正常;1-删除)")
    @TableField(value = "IS_DELETE")
    private Integer isDelete;
}
