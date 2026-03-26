package com.zlt.aps.lh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlt.aps.lh.api.domain.MouldChangePlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 换模计划Mapper接口
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Mapper
public interface MouldChangePlanMapper extends BaseMapper<MouldChangePlan> {

    /**
     * 根据机台编号查询换模计划列表
     *
     * @param machineCode 机台编号
     * @return 换模计划列表
     */
    List<MouldChangePlan> selectByMachineCode(@Param("machineCode") String machineCode);

    /**
     * 根据日期范围查询换模计划列表
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 换模计划列表
     */
    List<MouldChangePlan> selectByDateRange(@Param("startDate") Date startDate,
                                             @Param("endDate") Date endDate);

    /**
     * 根据机台编号和日期范围查询换模计划列表
     *
     * @param machineCode 机台编号
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 换模计划列表
     */
    List<MouldChangePlan> selectByMachineAndDateRange(@Param("machineCode") String machineCode,
                                                       @Param("startDate") Date startDate,
                                                       @Param("endDate") Date endDate);

    /**
     * 查询待执行的换模计划
     *
     * @param machineCode 机台编号
     * @param planDate 计划日期
     * @return 换模计划列表
     */
    List<MouldChangePlan> selectPendingPlans(@Param("machineCode") String machineCode,
                                              @Param("planDate") Date planDate);

    /**
     * 更新换模状态
     *
     * @param id 换模计划ID
     * @param status 状态
     * @return 更新数量
     */
    int updateStatus(@Param("id") Long id, @Param("status") String status);

    /**
     * 根据模具编号查询换模计划
     *
     * @param mouldCode 模具编号
     * @param planDate 计划日期
     * @return 换模计划列表
     */
    List<MouldChangePlan> selectByMouldCode(@Param("mouldCode") String mouldCode,
                                             @Param("planDate") Date planDate);
}
