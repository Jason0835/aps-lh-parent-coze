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
 * 设备保养计划实体类
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@ApiModel(value = "设备保养计划对象", description = "设备保养计划表实体对象")
@Data
@TableName(value = "T_MDM_DEV_MAINTENANCE_PLAN")
public class DevMaintenancePlan implements Serializable {

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

    @ApiModelProperty(value = "保养类型")
    @TableField(value = "MAINTENANCE_TYPE")
    private String maintenanceType;

    @ApiModelProperty(value = "保养类型描述")
    @TableField(value = "MAINTENANCE_TYPE_DESC")
    private String maintenanceTypeDesc;

    @ApiModelProperty(value = "计划保养日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "PLAN_MAINTENANCE_DATE")
    private Date planMaintenanceDate;

    @ApiModelProperty(value = "计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "PLAN_START_TIME")
    private Date planStartTime;

    @ApiModelProperty(value = "计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "PLAN_END_TIME")
    private Date planEndTime;

    @ApiModelProperty(value = "保养时长(小时)")
    @TableField(value = "MAINTENANCE_DURATION")
    private Integer maintenanceDuration;

    @ApiModelProperty(value = "上次保养日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "LAST_MAINTENANCE_DATE")
    private Date lastMaintenanceDate;

    @ApiModelProperty(value = "状态(0-计划;1-执行中;2-已完成)")
    @TableField(value = "STATUS")
    private String status;

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
