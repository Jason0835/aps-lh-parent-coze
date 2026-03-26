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
 * 硫化定点机台信息实体类
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@ApiModel(value = "硫化定点机台信息对象", description = "硫化定点机台信息表实体对象")
@Data
@TableName(value = "T_LH_SPECIFY_MACHINE")
public class SpecifyMachine implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分厂编号")
    @TableField(value = "FACTORY_CODE")
    private String factoryCode;

    @ApiModelProperty(value = "物料编号")
    @TableField(value = "MATERIAL_CODE")
    private String materialCode;

    @ApiModelProperty(value = "物料描述")
    @TableField(value = "MATERIAL_DESC")
    private String materialDesc;

    @ApiModelProperty(value = "硫化机台编号")
    @TableField(value = "LH_MACHINE_CODE")
    private String lhMachineCode;

    @ApiModelProperty(value = "硫化机台名称")
    @TableField(value = "LH_MACHINE_NAME")
    private String lhMachineName;

    @ApiModelProperty(value = "限制类型(1-限制作业;2-不可作业)")
    @TableField(value = "RESTRICT_TYPE")
    private String restrictType;

    @ApiModelProperty(value = "生效开始日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "EFFECT_START_DATE")
    private Date effectStartDate;

    @ApiModelProperty(value = "生效结束日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "EFFECT_END_DATE")
    private Date effectEndDate;

    @ApiModelProperty(value = "是否启用(0-否;1-是)")
    @TableField(value = "IS_ENABLE")
    private String isEnable;

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
