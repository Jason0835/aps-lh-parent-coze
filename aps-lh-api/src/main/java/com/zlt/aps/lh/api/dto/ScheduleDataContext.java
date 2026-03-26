package com.zlt.aps.lh.api.dto;

import com.zlt.aps.lh.api.domain.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 排程数据上下文
 * 包含排程所需的所有基础数据
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Data
public class ScheduleDataContext implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 排程请求参数
     */
    private ScheduleRequest request;

    /**
     * 机台信息列表
     */
    private List<LhMachineInfo> machineInfoList;

    /**
     * 月度计划产品列表
     */
    private List<MonthPlanProd> monthPlanProdList;

    /**
     * 参数配置Map，key为参数编码，value为参数值
     */
    private Map<String, String> paramsMap;

    /**
     * 设备停机计划列表
     */
    private List<DevicePlanShut> devicePlanShutList;

    /**
     * 换模计划列表
     */
    private List<MouldChangePlan> mouldChangePlanList;

    /**
     * 清洗计划列表
     */
    private List<CleaningPlan> cleaningPlanList;

    /**
     * 工作日历列表
     */
    private List<WorkCalendar> workCalendarList;

    /**
     * SKU产能配置列表
     */
    private List<SkuLhCapacity> skuLhCapacityList;

    /**
     * SKU模具关系列表
     */
    private List<SkuMouldRel> skuMouldRelList;

    /**
     * 机台在线信息列表
     */
    private List<MachineOnlineInfo> machineOnlineInfoList;

    /**
     * 定点机台信息列表
     */
    private List<SpecifyMachine> specifyMachineList;

    /**
     * 设备保养计划列表
     */
    private List<DevMaintenancePlan> devMaintenancePlanList;

    /**
     * 物料-模具关系Map，key为物料编号，value为模具编号列表
     */
    private Map<String, List<String>> materialMouldMap;

    /**
     * 模具-物料关系Map，key为模具编号，value为物料编号列表
     */
    private Map<String, List<String>> mouldMaterialMap;

    /**
     * 物料-产能Map，key为物料编号+机台编号，value为产能配置
     */
    private Map<String, SkuLhCapacity> materialCapacityMap;

    /**
     * 物料-定点机台Map，key为物料编号，value为定点机台编号列表
     */
    private Map<String, List<String>> materialSpecifyMachineMap;

    /**
     * 机台-在线信息Map，key为机台编号，value为在线信息
     */
    private Map<String, MachineOnlineInfo> machineOnlineMap;

    /**
     * 获取参数值
     *
     * @param paramCode 参数编码
     * @return 参数值
     */
    public String getParamValue(String paramCode) {
        return paramsMap != null ? paramsMap.get(paramCode) : null;
    }

    /**
     * 获取参数值，带默认值
     *
     * @param paramCode 参数编码
     * @param defaultValue 默认值
     * @return 参数值
     */
    public String getParamValue(String paramCode, String defaultValue) {
        String value = getParamValue(paramCode);
        return value != null ? value : defaultValue;
    }

    /**
     * 获取物料对应的模具列表
     *
     * @param materialCode 物料编号
     * @return 模具编号列表
     */
    public List<String> getMouldCodesByMaterial(String materialCode) {
        return materialMouldMap != null ? materialMouldMap.get(materialCode) : null;
    }

    /**
     * 获取模具对应的物料列表
     *
     * @param mouldCode 模具编号
     * @return 物料编号列表
     */
    public List<String> getMaterialCodesByMould(String mouldCode) {
        return mouldMaterialMap != null ? mouldMaterialMap.get(mouldCode) : null;
    }

    /**
     * 获取物料在指定机台的产能配置
     *
     * @param materialCode 物料编号
     * @param machineCode 机台编号
     * @return 产能配置
     */
    public SkuLhCapacity getCapacity(String materialCode, String machineCode) {
        String key = materialCode + "_" + machineCode;
        return materialCapacityMap != null ? materialCapacityMap.get(key) : null;
    }

    /**
     * 获取机台在线信息
     *
     * @param machineCode 机台编号
     * @return 在线信息
     */
    public MachineOnlineInfo getMachineOnlineInfo(String machineCode) {
        return machineOnlineMap != null ? machineOnlineMap.get(machineCode) : null;
    }

    /**
     * 获取物料的定点机台列表
     *
     * @param materialCode 物料编号
     * @return 定点机台编号列表
     */
    public List<String> getSpecifyMachines(String materialCode) {
        return materialSpecifyMachineMap != null ? materialSpecifyMachineMap.get(materialCode) : null;
    }
}
