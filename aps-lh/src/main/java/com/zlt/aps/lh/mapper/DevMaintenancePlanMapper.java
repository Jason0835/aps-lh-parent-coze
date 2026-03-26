package com.zlt.aps.lh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlt.aps.lh.api.domain.DevMaintenancePlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 设备保养计划Mapper接口
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Mapper
public interface DevMaintenancePlanMapper extends BaseMapper<DevMaintenancePlan> {

    /**
     * 根据机台编号查询保养计划列表
     *
     * @param machineCode 机台编号
     * @return 保养计划列表
     */
    List<DevMaintenancePlan> selectByMachineCode(@Param("machineCode") String machineCode);

    /**
     * 根据日期范围查询保养计划列表
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 保养计划列表
     */
    List<DevMaintenancePlan> selectByDateRange(@Param("startDate") Date startDate,
                                                @Param("endDate") Date endDate);

    /**
     * 根据机台编号和日期范围查询保养计划列表
     *
     * @param machineCode 机台编号
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 保养计划列表
     */
    List<DevMaintenancePlan> selectByMachineAndDateRange(@Param("machineCode") String machineCode,
                                                          @Param("startDate") Date startDate,
                                                          @Param("endDate") Date endDate);

    /**
     * 查询待执行的保养计划
     *
     * @param machineCode 机台编号
     * @param planDate 计划日期
     * @return 保养计划列表
     */
    List<DevMaintenancePlan> selectPendingPlans(@Param("machineCode") String machineCode,
                                                 @Param("planDate") Date planDate);

    /**
     * 更新保养状态
     *
     * @param id 保养计划ID
     * @param status 状态
     * @return 更新数量
     */
    int updateStatus(@Param("id") Long id, @Param("status") String status);

    /**
     * 查询在指定时间段内有保养计划的机台列表
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 保养计划列表
     */
    List<DevMaintenancePlan> selectMachinesWithMaintenance(@Param("startTime") Date startTime,
                                                           @Param("endTime") Date endTime);

    /**
     * 根据分厂编号查询保养计划列表
     *
     * @param factoryCode 分厂编号
     * @param planDate 计划日期
     * @return 保养计划列表
     */
    List<DevMaintenancePlan> selectByFactoryAndDate(@Param("factoryCode") String factoryCode,
                                                     @Param("planDate") Date planDate);
}
