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
 * SKU日硫化产能实体类
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@ApiModel(value = "SKU日硫化产能对象", description = "SKU日硫化产能表实体对象")
@Data
@TableName(value = "T_MDM_SKU_LH_CAPACITY")
public class SkuLhCapacity implements Serializable {

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

    @ApiModelProperty(value = "规格代码")
    @TableField(value = "SPEC_CODE")
    private String specCode;

    @ApiModelProperty(value = "硫化时长(秒)")
    @TableField(value = "LH_TIME")
    private Integer lhTime;

    @ApiModelProperty(value = "单班产能")
    @TableField(value = "SHIFT_CAPACITY")
    private Integer shiftCapacity;

    @ApiModelProperty(value = "日产能")
    @TableField(value = "DAILY_CAPACITY")
    private Integer dailyCapacity;

    @ApiModelProperty(value = "模数")
    @TableField(value = "MOULD_COUNT")
    private Integer mouldCount;

    @ApiModelProperty(value = "硫化方式")
    @TableField(value = "LH_METHOD")
    private String lhMethod;

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
