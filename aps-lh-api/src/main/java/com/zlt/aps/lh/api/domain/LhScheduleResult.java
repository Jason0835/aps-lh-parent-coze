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
 * 硫化排程结果实体类
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@ApiModel(value = "硫化排程结果对象", description = "硫化排程结果表实体对象")
@Data
@TableName(value = "T_LH_SCHEDULE_RESULT")
public class LhScheduleResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "分厂编号")
    @TableField(value = "FACTORY_CODE")
    private String factoryCode;

    @ApiModelProperty(value = "批次号")
    @TableField(value = "BATCH_NO")
    private String batchNo;

    @ApiModelProperty(value = "唯一工单号")
    @TableField(value = "ORDER_NO")
    private String orderNo;

    @ApiModelProperty(value = "硫化机台编号")
    @TableField(value = "LH_MACHINE_CODE")
    private String lhMachineCode;

    @ApiModelProperty(value = "左右模(L:左模;R:右模;LR:双模)")
    @TableField(value = "LEFT_RIGHT_MOULD")
    private String leftRightMould;

    @ApiModelProperty(value = "硫化机台名称")
    @TableField(value = "LH_MACHINE_NAME")
    private String lhMachineName;

    @ApiModelProperty(value = "物料编号")
    @TableField(value = "MATERIAL_CODE")
    private String materialCode;

    @ApiModelProperty(value = "规格代码")
    @TableField(value = "SPEC_CODE")
    private String specCode;

    @ApiModelProperty(value = "胎胚代码")
    @TableField(value = "EMBRYO_CODE")
    private String embryoCode;

    @ApiModelProperty(value = "产品结构")
    @TableField(value = "STRUCTURE_NAME")
    private String structureName;

    @ApiModelProperty(value = "物料描述")
    @TableField(value = "MATERIAL_DESC")
    private String materialDesc;

    @ApiModelProperty(value = "主物料(胎胚描述)")
    @TableField(value = "MAIN_MATERIAL_DESC")
    private String mainMaterialDesc;

    @ApiModelProperty(value = "胎胚库存")
    @TableField(value = "EMBRYO_STOCK")
    private Integer embryoStock;

    @ApiModelProperty(value = "规格描述信息")
    @TableField(value = "SPEC_DESC")
    private String specDesc;

    @ApiModelProperty(value = "硫化时长(秒)")
    @TableField(value = "LH_TIME")
    private Integer lhTime;

    @ApiModelProperty(value = "日计划数量")
    @TableField(value = "DAILY_PLAN_QTY")
    private Integer dailyPlanQty;

    @ApiModelProperty(value = "排程日期")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "SCHEDULE_DATE")
    private Date scheduleDate;

    @ApiModelProperty(value = "规格结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "SPEC_END_TIME")
    private Date specEndTime;

    @ApiModelProperty(value = "生产状态(0-未生产;1-生产中;2-生产完成)")
    @TableField(value = "PRODUCTION_STATUS")
    private String productionStatus;

    @ApiModelProperty(value = "1班计划量")
    @TableField(value = "CLASS1_PLAN_QTY")
    private Integer class1PlanQty;

    @ApiModelProperty(value = "1班计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "CLASS1_START_TIME")
    private Date class1StartTime;

    @ApiModelProperty(value = "1班计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "CLASS1_END_TIME")
    private Date class1EndTime;

    @ApiModelProperty(value = "1班原因分析")
    @TableField(value = "CLASS1_ANALYSIS")
    private String class1Analysis;

    @ApiModelProperty(value = "1班完成量")
    @TableField(value = "CLASS1_FINISH_QTY")
    private Integer class1FinishQty;

    @ApiModelProperty(value = "2班计划量")
    @TableField(value = "CLASS2_PLAN_QTY")
    private Integer class2PlanQty;

    @ApiModelProperty(value = "2班计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "CLASS2_START_TIME")
    private Date class2StartTime;

    @ApiModelProperty(value = "2班计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "CLASS2_END_TIME")
    private Date class2EndTime;

    @ApiModelProperty(value = "2班原因分析")
    @TableField(value = "CLASS2_ANALYSIS")
    private String class2Analysis;

    @ApiModelProperty(value = "2班完成量")
    @TableField(value = "CLASS2_FINISH_QTY")
    private Integer class2FinishQty;

    @ApiModelProperty(value = "3班计划量")
    @TableField(value = "CLASS3_PLAN_QTY")
    private Integer class3PlanQty;

    @ApiModelProperty(value = "3班计划开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "CLASS3_START_TIME")
    private Date class3StartTime;

    @ApiModelProperty(value = "3班计划结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "CLASS3_END_TIME")
    private Date class3EndTime;

    @ApiModelProperty(value = "3班原因分析")
    @TableField(value = "CLASS3_ANALYSIS")
    private String class3Analysis;

    @ApiModelProperty(value = "3班完成量")
    @TableField(value = "CLASS3_FINISH_QTY")
    private Integer class3FinishQty;

    @ApiModelProperty(value = "是否交期(0-否;1-是)")
    @TableField(value = "IS_DELIVERY")
    private String isDelivery;

    @ApiModelProperty(value = "是否发布(0-未发布;1-已发布;2-发布失败;3-超时发布;4-待发布)")
    @TableField(value = "IS_RELEASE")
    private String isRelease;

    @ApiModelProperty(value = "数据来源(0-自动排程;1-插单;2-导入)")
    @TableField(value = "DATA_SOURCE")
    private String dataSource;

    @ApiModelProperty(value = "使用模数")
    @TableField(value = "MOULD_QTY")
    private Integer mouldQty;

    @ApiModelProperty(value = "单班硫化量")
    @TableField(value = "SINGLE_MOULD_SHIFT_QTY")
    private Integer singleMouldShiftQty;

    @ApiModelProperty(value = "模具信息 JSON字符串")
    @TableField(value = "MOULD_INFO")
    private String mouldInfo;

    @ApiModelProperty(value = "硫化方式")
    @TableField(value = "MOULD_METHOD")
    private String mouldMethod;

    @ApiModelProperty(value = "施工阶段(00-无工艺;01-试制;02-量试;03-正式)")
    @TableField(value = "CONSTRUCTION_STAGE")
    private String constructionStage;

    @ApiModelProperty(value = "制造示方书号")
    @TableField(value = "EMBRYO_NO")
    private String embryoNo;

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
