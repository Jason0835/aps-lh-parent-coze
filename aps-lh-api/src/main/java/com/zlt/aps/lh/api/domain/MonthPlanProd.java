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
 * 月生产计划实体类
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@ApiModel(value = "月生产计划对象", description = "月生产计划表实体对象")
@Data
@TableName(value = "T_MP_MONTH_PLAN_PROD_FINAL")
public class MonthPlanProd implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分厂编号")
    @TableField(value = "FACTORY_CODE")
    private String factoryCode;

    @ApiModelProperty(value = "计划年月")
    @TableField(value = "PLAN_MONTH")
    private String planMonth;

    @ApiModelProperty(value = "物料编号")
    @TableField(value = "MATERIAL_CODE")
    private String materialCode;

    @ApiModelProperty(value = "物料描述")
    @TableField(value = "MATERIAL_DESC")
    private String materialDesc;

    @ApiModelProperty(value = "规格代码")
    @TableField(value = "SPEC_CODE")
    private String specCode;

    @ApiModelProperty(value = "胎胚代码")
    @TableField(value = "EMBRYO_CODE")
    private String embryoCode;

    @ApiModelProperty(value = "产品结构")
    @TableField(value = "STRUCTURE_NAME")
    private String structureName;

    @ApiModelProperty(value = "月计划数量")
    @TableField(value = "MONTH_PLAN_QTY")
    private Integer monthPlanQty;

    @ApiModelProperty(value = "已完成数量")
    @TableField(value = "FINISHED_QTY")
    private Integer finishedQty;

    @ApiModelProperty(value = "硫化余量")
    @TableField(value = "SULFUR_SURPLUS")
    private Integer sulfurSurplus;

    @ApiModelProperty(value = "供应链优先级")
    @TableField(value = "SUPPLY_CHAIN_PRIORITY")
    private Integer supplyChainPriority;

    @ApiModelProperty(value = "是否锁定上机日期(0-否;1-是)")
    @TableField(value = "IS_LOCK_MACHINE_DATE")
    private String isLockMachineDate;

    @ApiModelProperty(value = "锁定上机日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "LOCK_MACHINE_DATE")
    private Date lockMachineDate;

    @ApiModelProperty(value = "延误天数")
    @TableField(value = "DELAY_DAYS")
    private Integer delayDays;

    @ApiModelProperty(value = "施工阶段")
    @TableField(value = "CONSTRUCTION_STAGE")
    private String constructionStage;

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
