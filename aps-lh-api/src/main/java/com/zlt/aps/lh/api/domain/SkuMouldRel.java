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
 * SKU与模具关系实体类
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@ApiModel(value = "SKU与模具关系对象", description = "SKU与模具关系表实体对象")
@Data
@TableName(value = "T_MDM_SKU_MOULD_REL")
public class SkuMouldRel implements Serializable {

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

    @ApiModelProperty(value = "模具编号")
    @TableField(value = "MOULD_CODE")
    private String mouldCode;

    @ApiModelProperty(value = "模具名称")
    @TableField(value = "MOULD_NAME")
    private String mouldName;

    @ApiModelProperty(value = "模具状态(0-不可用;1-可用)")
    @TableField(value = "MOULD_STATUS")
    private String mouldStatus;

    @ApiModelProperty(value = "模具可用日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "MOULD_AVAILABLE_DATE")
    private Date mouldAvailableDate;

    @ApiModelProperty(value = "是否共用模具(0-否;1-是)")
    @TableField(value = "IS_SHARED")
    private String isShared;

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
