package com.zlt.aps.lh.loader.impl;

import com.zlt.aps.lh.api.domain.*;
import com.zlt.aps.lh.api.dto.ScheduleDataContext;
import com.zlt.aps.lh.api.dto.ScheduleRequest;
import com.zlt.aps.lh.loader.DataLoader;
import com.zlt.aps.lh.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据加载器实现类
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Slf4j
@Component
public class DataLoaderImpl implements DataLoader {

    @Autowired
    private LhMachineInfoMapper machineInfoMapper;

    @Autowired
    private MonthPlanProdMapper monthPlanProdMapper;

    @Autowired
    private LhParamsMapper paramsMapper;

    @Autowired
    private DevicePlanShutMapper devicePlanShutMapper;

    @Autowired
    private MouldChangePlanMapper mouldChangePlanMapper;

    @Autowired
    private CleaningPlanMapper cleaningPlanMapper;

    @Autowired
    private WorkCalendarMapper workCalendarMapper;

    @Autowired
    private SkuLhCapacityMapper skuLhCapacityMapper;

    @Autowired
    private SkuMouldRelMapper skuMouldRelMapper;

    @Autowired
    private MachineOnlineInfoMapper machineOnlineInfoMapper;

    @Autowired
    private SpecifyMachineMapper specifyMachineMapper;

    @Autowired
    private DevMaintenancePlanMapper devMaintenancePlanMapper;

    @Override
    public List<LhMachineInfo> loadMachineInfo(String factoryCode) {
        log.info("加载机台信息，分厂编号: {}", factoryCode);
        List<LhMachineInfo> machineInfoList = machineInfoMapper.selectByFactoryCode(factoryCode);
        log.info("加载机台信息完成，数量: {}", machineInfoList != null ? machineInfoList.size() : 0);
        return machineInfoList;
    }

    @Override
    public List<MonthPlanProd> loadMonthPlanProd(ScheduleRequest request) {
        log.info("加载月度计划产品，计划编号: {}, 计划月份: {}", request.getPlanCode(), request.getPlanMonth());
        List<MonthPlanProd> prodList;
        if (request.getPlanCode() != null) {
            prodList = monthPlanProdMapper.selectByPlanCode(request.getPlanCode());
        } else {
            prodList = monthPlanProdMapper.selectByFactoryAndMonth(request.getFactoryCode(), request.getPlanMonth());
        }
        log.info("加载月度计划产品完成，数量: {}", prodList != null ? prodList.size() : 0);
        return prodList;
    }

    @Override
    public Map<String, String> loadParams(String factoryCode) {
        log.info("加载参数配置，分厂编号: {}", factoryCode);
        List<LhParams> paramsList = paramsMapper.selectByFactoryCode(factoryCode);
        Map<String, String> paramsMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(paramsList)) {
            paramsMap = paramsList.stream()
                    .collect(Collectors.toMap(LhParams::getParamCode, LhParams::getParamValue, (v1, v2) -> v2));
        }
        log.info("加载参数配置完成，数量: {}", paramsMap.size());
        return paramsMap;
    }

    @Override
    public List<DevicePlanShut> loadDevicePlanShut(String factoryCode, Date startDate, Date endDate) {
        log.info("加载设备停机计划，分厂编号: {}, 开始日期: {}, 结束日期: {}", factoryCode, startDate, endDate);
        List<DevicePlanShut> shutList = devicePlanShutMapper.selectByDateRange(startDate, endDate);
        // 过滤分厂
        if (!CollectionUtils.isEmpty(shutList) && factoryCode != null) {
            shutList = shutList.stream()
                    .filter(shut -> factoryCode.equals(shut.getFactoryCode()))
                    .collect(Collectors.toList());
        }
        log.info("加载设备停机计划完成，数量: {}", shutList != null ? shutList.size() : 0);
        return shutList;
    }

    @Override
    public List<MouldChangePlan> loadMouldChangePlan(String factoryCode, Date startDate, Date endDate) {
        log.info("加载换模计划，分厂编号: {}, 开始日期: {}, 结束日期: {}", factoryCode, startDate, endDate);
        List<MouldChangePlan> mouldChangeList = mouldChangePlanMapper.selectByDateRange(startDate, endDate);
        // 过滤分厂
        if (!CollectionUtils.isEmpty(mouldChangeList) && factoryCode != null) {
            mouldChangeList = mouldChangeList.stream()
                    .filter(plan -> factoryCode.equals(plan.getFactoryCode()))
                    .collect(Collectors.toList());
        }
        log.info("加载换模计划完成，数量: {}", mouldChangeList != null ? mouldChangeList.size() : 0);
        return mouldChangeList;
    }

    @Override
    public List<CleaningPlan> loadCleaningPlan(String factoryCode, Date startDate, Date endDate) {
        log.info("加载清洗计划，分厂编号: {}, 开始日期: {}, 结束日期: {}", factoryCode, startDate, endDate);
        List<CleaningPlan> cleaningList = cleaningPlanMapper.selectByDateRange(startDate, endDate);
        // 过滤分厂
        if (!CollectionUtils.isEmpty(cleaningList) && factoryCode != null) {
            cleaningList = cleaningList.stream()
                    .filter(plan -> factoryCode.equals(plan.getFactoryCode()))
                    .collect(Collectors.toList());
        }
        log.info("加载清洗计划完成，数量: {}", cleaningList != null ? cleaningList.size() : 0);
        return cleaningList;
    }

    @Override
    public List<WorkCalendar> loadWorkCalendar(String factoryCode, Date startDate, Date endDate) {
        log.info("加载工作日历，分厂编号: {}, 开始日期: {}, 结束日期: {}", factoryCode, startDate, endDate);
        List<WorkCalendar> calendarList = workCalendarMapper.selectByDateRange(factoryCode, startDate, endDate);
        log.info("加载工作日历完成，数量: {}", calendarList != null ? calendarList.size() : 0);
        return calendarList;
    }

    @Override
    public List<SkuLhCapacity> loadSkuLhCapacity(String factoryCode) {
        log.info("加载SKU产能配置，分厂编号: {}", factoryCode);
        List<SkuLhCapacity> capacityList = skuLhCapacityMapper.selectByFactoryCode(factoryCode);
        log.info("加载SKU产能配置完成，数量: {}", capacityList != null ? capacityList.size() : 0);
        return capacityList;
    }

    @Override
    public List<SkuMouldRel> loadSkuMouldRel(String factoryCode) {
        log.info("加载SKU模具关系，分厂编号: {}", factoryCode);
        List<SkuMouldRel> mouldRelList = skuMouldRelMapper.selectByFactoryCode(factoryCode);
        log.info("加载SKU模具关系完成，数量: {}", mouldRelList != null ? mouldRelList.size() : 0);
        return mouldRelList;
    }

    @Override
    public List<MachineOnlineInfo> loadMachineOnlineInfo(String factoryCode) {
        log.info("加载机台在线信息，分厂编号: {}", factoryCode);
        List<MachineOnlineInfo> onlineList = machineOnlineInfoMapper.selectByFactoryCode(factoryCode);
        log.info("加载机台在线信息完成，数量: {}", onlineList != null ? onlineList.size() : 0);
        return onlineList;
    }

    @Override
    public List<SpecifyMachine> loadSpecifyMachine(String factoryCode) {
        log.info("加载定点机台信息，分厂编号: {}", factoryCode);
        List<SpecifyMachine> specifyList = specifyMachineMapper.selectEnabledSpecify();
        // 过滤分厂
        if (!CollectionUtils.isEmpty(specifyList) && factoryCode != null) {
            specifyList = specifyList.stream()
                    .filter(specify -> factoryCode.equals(specify.getFactoryCode()))
                    .collect(Collectors.toList());
        }
        log.info("加载定点机台信息完成，数量: {}", specifyList != null ? specifyList.size() : 0);
        return specifyList;
    }

    @Override
    public List<DevMaintenancePlan> loadDevMaintenancePlan(String factoryCode, Date startDate, Date endDate) {
        log.info("加载设备保养计划，分厂编号: {}, 开始日期: {}, 结束日期: {}", factoryCode, startDate, endDate);
        List<DevMaintenancePlan> maintenanceList = devMaintenancePlanMapper.selectByDateRange(startDate, endDate);
        // 过滤分厂
        if (!CollectionUtils.isEmpty(maintenanceList) && factoryCode != null) {
            maintenanceList = maintenanceList.stream()
                    .filter(plan -> factoryCode.equals(plan.getFactoryCode()))
                    .collect(Collectors.toList());
        }
        log.info("加载设备保养计划完成，数量: {}", maintenanceList != null ? maintenanceList.size() : 0);
        return maintenanceList;
    }

    @Override
    public ScheduleDataContext loadAllData(ScheduleRequest request) {
        log.info("开始加载所有排程数据...");
        long startTime = System.currentTimeMillis();

        ScheduleDataContext context = new ScheduleDataContext();
        context.setRequest(request);

        String factoryCode = request.getFactoryCode();
        Date startDate = request.getScheduleStartDate();
        Date endDate = request.getScheduleEndDate();

        // 加载基础数据
        context.setMachineInfoList(loadMachineInfo(factoryCode));
        context.setMonthPlanProdList(loadMonthPlanProd(request));
        context.setParamsMap(loadParams(factoryCode));
        context.setDevicePlanShutList(loadDevicePlanShut(factoryCode, startDate, endDate));
        context.setMouldChangePlanList(loadMouldChangePlan(factoryCode, startDate, endDate));
        context.setCleaningPlanList(loadCleaningPlan(factoryCode, startDate, endDate));
        context.setWorkCalendarList(loadWorkCalendar(factoryCode, startDate, endDate));
        context.setSkuLhCapacityList(loadSkuLhCapacity(factoryCode));
        context.setSkuMouldRelList(loadSkuMouldRel(factoryCode));
        context.setMachineOnlineInfoList(loadMachineOnlineInfo(factoryCode));
        context.setSpecifyMachineList(loadSpecifyMachine(factoryCode));
        context.setDevMaintenancePlanList(loadDevMaintenancePlan(factoryCode, startDate, endDate));

        // 构建索引Map
        buildIndexMaps(context);

        long endTime = System.currentTimeMillis();
        log.info("加载所有排程数据完成，耗时: {} ms", endTime - startTime);

        return context;
    }

    /**
     * 构建索引Map，提高查询效率
     *
     * @param context 排程数据上下文
     */
    private void buildIndexMaps(ScheduleDataContext context) {
        log.info("开始构建索引Map...");

        // 构建物料-模具关系Map
        if (!CollectionUtils.isEmpty(context.getSkuMouldRelList())) {
            Map<String, List<String>> materialMouldMap = context.getSkuMouldRelList().stream()
                    .filter(rel -> rel.getMaterialCode() != null && rel.getMouldCode() != null)
                    .collect(Collectors.groupingBy(
                            SkuMouldRel::getMaterialCode,
                            Collectors.mapping(SkuMouldRel::getMouldCode, Collectors.toList())
                    ));
            context.setMaterialMouldMap(materialMouldMap);

            Map<String, List<String>> mouldMaterialMap = context.getSkuMouldRelList().stream()
                    .filter(rel -> rel.getMaterialCode() != null && rel.getMouldCode() != null)
                    .collect(Collectors.groupingBy(
                            SkuMouldRel::getMouldCode,
                            Collectors.mapping(SkuMouldRel::getMaterialCode, Collectors.toList())
                    ));
            context.setMouldMaterialMap(mouldMaterialMap);
        }

        // 构建物料-产能Map
        if (!CollectionUtils.isEmpty(context.getSkuLhCapacityList())) {
            Map<String, SkuLhCapacity> capacityMap = context.getSkuLhCapacityList().stream()
                    .filter(cap -> cap.getMaterialCode() != null && cap.getMachineCode() != null)
                    .collect(Collectors.toMap(
                            cap -> cap.getMaterialCode() + "_" + cap.getMachineCode(),
                            cap -> cap,
                            (v1, v2) -> v2
                    ));
            context.setMaterialCapacityMap(capacityMap);
        }

        // 构建物料-定点机台Map
        if (!CollectionUtils.isEmpty(context.getSpecifyMachineList())) {
            Map<String, List<String>> specifyMachineMap = context.getSpecifyMachineList().stream()
                    .filter(spec -> spec.getMaterialCode() != null && spec.getLhMachineCode() != null)
                    .collect(Collectors.groupingBy(
                            SpecifyMachine::getMaterialCode,
                            Collectors.mapping(SpecifyMachine::getLhMachineCode, Collectors.toList())
                    ));
            context.setMaterialSpecifyMachineMap(specifyMachineMap);
        }

        // 构建机台-在线信息Map
        if (!CollectionUtils.isEmpty(context.getMachineOnlineInfoList())) {
            Map<String, MachineOnlineInfo> machineOnlineMap = context.getMachineOnlineInfoList().stream()
                    .filter(info -> info.getMachineCode() != null)
                    .collect(Collectors.toMap(
                            MachineOnlineInfo::getMachineCode,
                            info -> info,
                            (v1, v2) -> v2
                    ));
            context.setMachineOnlineMap(machineOnlineMap);
        }

        log.info("构建索引Map完成");
    }
}
