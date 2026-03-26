package com.zlt.aps.lh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlt.aps.lh.api.domain.LhMachineInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 硫化机台信息Mapper接口
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Mapper
public interface LhMachineInfoMapper extends BaseMapper<LhMachineInfo> {

    /**
     * 根据分厂编号查询机台列表
     *
     * @param factoryCode 分厂编号
     * @return 机台列表
     */
    List<LhMachineInfo> selectByFactoryCode(@Param("factoryCode") String factoryCode);

    /**
     * 查询启用的机台列表
     *
     * @return 机台列表
     */
    List<LhMachineInfo> selectEnabledMachines();

    /**
     * 根据机台编号查询机台信息
     *
     * @param machineCode 机台编号
     * @return 机台信息
     */
    LhMachineInfo selectByMachineCode(@Param("machineCode") String machineCode);

    /**
     * 根据机台类型查询机台列表
     *
     * @param machineType 机台类型
     * @return 机台列表
     */
    List<LhMachineInfo> selectByMachineType(@Param("machineType") String machineType);

    /**
     * 查询可用的机台列表(未停机、未保养)
     *
     * @param factoryCode 分厂编号
     * @return 机台列表
     */
    List<LhMachineInfo> selectAvailableMachines(@Param("factoryCode") String factoryCode);
}
