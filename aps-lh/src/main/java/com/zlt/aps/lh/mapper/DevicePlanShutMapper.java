package com.zlt.aps.lh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlt.aps.lh.api.domain.DevicePlanShut;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 设备计划停机Mapper接口
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Mapper
public interface DevicePlanShutMapper extends BaseMapper<DevicePlanShut> {

    /**
     * 根据机台编号查询停机计划列表
     *
     * @param machineCode 机台编号
     * @return 停机计划列表
     */
    List<DevicePlanShut> selectByMachineCode(@Param("machineCode") String machineCode);

    /**
     * 根据日期范围查询停机计划列表
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 停机计划列表
     */
    List<DevicePlanShut> selectByDateRange(@Param("startDate") Date startDate,
                                            @Param("endDate") Date endDate);

    /**
     * 根据机台编号和日期范围查询停机计划列表
     *
     * @param machineCode 机台编号
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 停机计划列表
     */
    List<DevicePlanShut> selectByMachineAndDateRange(@Param("machineCode") String machineCode,
                                                      @Param("startDate") Date startDate,
                                                      @Param("endDate") Date endDate);

    /**
     * 查询在指定时间段内有停机计划的机台列表
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 停机计划列表
     */
    List<DevicePlanShut> selectMachinesWithShutdown(@Param("startTime") Date startTime,
                                                     @Param("endTime") Date endTime);

    /**
     * 更新停机状态
     *
     * @param id 停机计划ID
     * @param status 状态
     * @return 更新数量
     */
    int updateStatus(@Param("id") Long id, @Param("status") String status);
}
