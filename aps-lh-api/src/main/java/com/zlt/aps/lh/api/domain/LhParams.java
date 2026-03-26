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
 * 硫化参数实体类
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@ApiModel(value = "硫化参数对象", description = "硫化参数表实体对象")
@Data
@TableName(value = "T_LH_PARAMS")
public class LhParams implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分厂编号")
    @TableField(value = "FACTORY_CODE")
    private String factoryCode;

    @ApiModelProperty(value = "参数编码")
    @TableField(value = "PARAM_CODE")
    private String paramCode;

    @ApiModelProperty(value = "参数名称")
    @TableField(value = "PARAM_NAME")
    private String paramName;

    @ApiModelProperty(value = "参数值")
    @TableField(value = "PARAM_VALUE")
    private String paramValue;

    @ApiModelProperty(value = "参数类型")
    @TableField(value = "PARAM_TYPE")
    private String paramType;

    @ApiModelProperty(value = "参数说明")
    @TableField(value = "PARAM_DESC")
    private String paramDesc;

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
