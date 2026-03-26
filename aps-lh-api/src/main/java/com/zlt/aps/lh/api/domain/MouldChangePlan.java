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
 * 模具交替计划实体类
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@ApiModel(value = "模具交替计划对象", description = "模具交替计划表实体对象")
@Data
@TableName(value = "T_LH_MOULD_CHANGE_PLAN")
public class MouldChangePlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分厂编号")
    @TableField(value = "FACTORY_CODE")
    private String factoryCode;

    @ApiModelProperty(value = "计划日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "PLAN_DATE")
    private Date planDate;

    @ApiModelProperty(value = "硫化排程批次号")
    @TableField(value = "BATCH_NO")
    private String batchNo;

    @ApiModelProperty(value = "工单号")
    @TableField(value = "ORDER_NO")
    private String orderNo;

    @ApiModelProperty(value = "硫化机台")
    @TableField(value = "LH_MACHINE_CODE")
    private String lhMachineCode;

    @ApiModelProperty(value = "硫化机台名称")
    @TableField(value = "LH_MACHINE_NAME")
    private String lhMachineName;

    @ApiModelProperty(value = "计划顺位")
    @TableField(value = "PLAN_SEQUENCE")
    private Integer planSequence;

    @ApiModelProperty(value = "左右模(L-左模;R-右模;LR-左右模)")
    @TableField(value = "LEFT_RIGHT_MOULD")
    private String leftRightMould;

    @ApiModelProperty(value = "前规格物料编码")
    @TableField(value = "BEFORE_MATERIAL_CODE")
    private String beforeMaterialCode;

    @ApiModelProperty(value = "前规格物料描述")
    @TableField(value = "BEFORE_MATERIAL_DESC")
    private String beforeMaterialDesc;

    @ApiModelProperty(value = "后规格物料编码")
    @TableField(value = "AFTER_MATERIAL_CODE")
    private String afterMaterialCode;

    @ApiModelProperty(value = "后规格物料描述")
    @TableField(value = "AFTER_MATERIAL_DESC")
    private String afterMaterialDesc;

    @ApiModelProperty(value = "交替类型")
    @TableField(value = "CHANGE_TYPE")
    private String changeType;

    @ApiModelProperty(value = "计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "PLAN_START_TIME")
    private Date planStartTime;

    @ApiModelProperty(value = "计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "PLAN_END_TIME")
    private Date planEndTime;

    @ApiModelProperty(value = "班次")
    @TableField(value = "SHIFT_CODE")
    private String shiftCode;

    @ApiModelProperty(value = "是否需要首检(0-否;1-是)")
    @TableField(value = "IS_NEED_FIRST_CHECK")
    private String isNeedFirstCheck;

    @ApiModelProperty(value = "施工状态(0-未执行;1-执行中;2-已完成)")
    @TableField(value = "EXECUTE_STATUS")
    private String executeStatus;

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
