package com.zlt.aps.lh.loader;

import com.zlt.aps.lh.api.domain.*;
import com.zlt.aps.lh.api.dto.ScheduleRequest;

import java.util.List;
import java.util.Map;

/**
 * 数据加载器接口
 * 负责加载排程所需的所有基础数据
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
public interface DataLoader {

    /**
     * 加载机台信息列表
     *
     * @param factoryCode 分厂编号
     * @return 机台信息列表
     */
    List<LhMachineInfo> loadMachineInfo(String factoryCode);

    /**
     * 加载月度计划产品列表
     *
     * @param request 排程请求
     * @return 计划产品列表
     */
    List<MonthPlanProd> loadMonthPlanProd(ScheduleRequest request);

    /**
     * 加载参数配置
     *
     * @param factoryCode 分厂编号
     * @return 参数配置Map，key为参数编码，value为参数值
     */
    Map<String, String> loadParams(String factoryCode);

    /**
     * 加载设备停机计划
     *
     * @param factoryCode 分厂编号
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 停机计划列表
     */
    List<DevicePlanShut> loadDevicePlanShut(String factoryCode, java.util.Date startDate, java.util.Date endDate);

    /**
     * 加载换模计划
     *
     * @param factoryCode 分厂编号
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 换模计划列表
     */
    List<MouldChangePlan> loadMouldChangePlan(String factoryCode, java.util.Date startDate, java.util.Date endDate);

    /**
     * 加载清洗计划
     *
     * @param factoryCode 分厂编号
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 清洗计划列表
     */
    List<CleaningPlan> loadCleaningPlan(String factoryCode, java.util.Date startDate, java.util.Date endDate);

    /**
     * 加载工作日历
     *
     * @param factoryCode 分厂编号
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 工作日历列表
     */
    List<WorkCalendar> loadWorkCalendar(String factoryCode, java.util.Date startDate, java.util.Date endDate);

    /**
     * 加载SKU产能配置
     *
     * @param factoryCode 分厂编号
     * @return SKU产能配置列表
     */
    List<SkuLhCapacity> loadSkuLhCapacity(String factoryCode);

    /**
     * 加载SKU模具关系
     *
     * @param factoryCode 分厂编号
     * @return SKU模具关系列表
     */
    List<SkuMouldRel> loadSkuMouldRel(String factoryCode);

    /**
     * 加载机台在线信息
     *
     * @param factoryCode 分厂编号
     * @return 机台在线信息列表
     */
    List<MachineOnlineInfo> loadMachineOnlineInfo(String factoryCode);

    /**
     * 加载定点机台信息
     *
     * @param factoryCode 分厂编号
     * @return 定点机台信息列表
     */
    List<SpecifyMachine> loadSpecifyMachine(String factoryCode);

    /**
     * 加载设备保养计划
     *
     * @param factoryCode 分厂编号
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 保养计划列表
     */
    List<DevMaintenancePlan> loadDevMaintenancePlan(String factoryCode, java.util.Date startDate, java.util.Date endDate);

    /**
     * 加载所有基础数据
     *
     * @param request 排程请求
     * @return 排程数据上下文
     */
    ScheduleDataContext loadAllData(ScheduleRequest request);
}
