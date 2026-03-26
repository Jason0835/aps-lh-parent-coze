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
import java.math.BigDecimal;
import java.util.Date;

/**
 * 硫化机台信息实体类
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@ApiModel(value = "硫化机台信息对象", description = "硫化机台信息表实体对象")
@Data
@TableName(value = "T_LH_MACHINE_INFO")
public class LhMachineInfo implements Serializable {

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

    @ApiModelProperty(value = "机台类型")
    @TableField(value = "MACHINE_TYPE")
    private String machineType;

    @ApiModelProperty(value = "机台状态(0-停用;1-正常)")
    @TableField(value = "STATUS")
    private String status;

    @ApiModelProperty(value = "模数")
    @TableField(value = "MOULD_COUNT")
    private Integer mouldCount;

    @ApiModelProperty(value = "当前规格代码")
    @TableField(value = "CURRENT_SPEC_CODE")
    private String currentSpecCode;

    @ApiModelProperty(value = "当前英寸")
    @TableField(value = "CURRENT_INCH")
    private String currentInch;

    @ApiModelProperty(value = "胶囊已使用次数")
    @TableField(value = "CAPSULE_USE_COUNT")
    private Integer capsuleUseCount;

    @ApiModelProperty(value = "当前物料编号")
    @TableField(value = "CURRENT_MATERIAL_CODE")
    private String currentMaterialCode;

    @ApiModelProperty(value = "当前物料描述")
    @TableField(value = "CURRENT_MATERIAL_DESC")
    private String currentMaterialDesc;

    @ApiModelProperty(value = "当前胎胚代码")
    @TableField(value = "CURRENT_EMBRYO_CODE")
    private String currentEmbryoCode;

    @ApiModelProperty(value = "当前结构名称")
    @TableField(value = "CURRENT_STRUCTURE_NAME")
    private String currentStructureName;

    @ApiModelProperty(value = "预计收尾时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "EXPECTED_FINISH_TIME")
    private Date expectedFinishTime;

    @ApiModelProperty(value = "是否可用(0-否;1-是)")
    @TableField(value = "IS_AVAILABLE")
    private String isAvailable;

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
