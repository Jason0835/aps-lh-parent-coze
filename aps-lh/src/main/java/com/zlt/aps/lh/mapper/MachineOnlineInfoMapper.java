package com.zlt.aps.lh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlt.aps.lh.api.domain.MachineOnlineInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 机台在线信息Mapper接口
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Mapper
public interface MachineOnlineInfoMapper extends BaseMapper<MachineOnlineInfo> {

    /**
     * 根据机台编号查询在线信息
     *
     * @param machineCode 机台编号
     * @return 在线信息
     */
    MachineOnlineInfo selectByMachineCode(@Param("machineCode") String machineCode);

    /**
     * 根据分厂编号查询在线信息列表
     *
     * @param factoryCode 分厂编号
     * @return 在线信息列表
     */
    List<MachineOnlineInfo> selectByFactoryCode(@Param("factoryCode") String factoryCode);

    /**
     * 查询正在生产的机台列表
     *
     * @param factoryCode 分厂编号
     * @return 在线信息列表
     */
    List<MachineOnlineInfo> selectProducingMachines(@Param("factoryCode") String factoryCode);

    /**
     * 查询空闲机台列表
     *
     * @param factoryCode 分厂编号
     * @return 在线信息列表
     */
    List<MachineOnlineInfo> selectIdleMachines(@Param("factoryCode") String factoryCode);

    /**
     * 更新机台状态
     *
     * @param machineCode 机台编号
     * @param status 状态
     * @return 更新数量
     */
    int updateStatus(@Param("machineCode") String machineCode, @Param("status") String status);

    /**
     * 更新当前生产信息
     *
     * @param machineCode 机台编号
     * @param materialCode 物料编号
     * @param mouldCode 模具编号
     * @param startTime 开始时间
     * @return 更新数量
     */
    int updateCurrentProduction(@Param("machineCode") String machineCode,
                                 @Param("materialCode") String materialCode,
                                 @Param("mouldCode") String mouldCode,
                                 @Param("startTime") Date startTime);

    /**
     * 根据模具编号查询机台列表
     *
     * @param mouldCode 模具编号
     * @return 在线信息列表
     */
    List<MachineOnlineInfo> selectByMouldCode(@Param("mouldCode") String mouldCode);

    /**
     * 清空机台生产信息
     *
     * @param machineCode 机台编号
     * @return 更新数量
     */
    int clearProductionInfo(@Param("machineCode") String machineCode);
}
