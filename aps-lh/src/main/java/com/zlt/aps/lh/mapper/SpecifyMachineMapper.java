package com.zlt.aps.lh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlt.aps.lh.api.domain.SpecifyMachine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 硫化定点机台信息Mapper接口
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Mapper
public interface SpecifyMachineMapper extends BaseMapper<SpecifyMachine> {

    /**
     * 根据物料编号查询定点机台列表
     *
     * @param materialCode 物料编号
     * @return 定点机台列表
     */
    List<SpecifyMachine> selectByMaterialCode(@Param("materialCode") String materialCode);

    /**
     * 根据机台编号查询定点物料列表
     *
     * @param machineCode 机台编号
     * @return 定点机台列表
     */
    List<SpecifyMachine> selectByMachineCode(@Param("machineCode") String machineCode);

    /**
     * 根据物料编号和机台编号查询定点关系
     *
     * @param materialCode 物料编号
     * @param machineCode 机台编号
     * @return 定点机台
     */
    SpecifyMachine selectByMaterialAndMachine(@Param("materialCode") String materialCode,
                                               @Param("machineCode") String machineCode);

    /**
     * 查询启用的定点机台列表
     *
     * @return 定点机台列表
     */
    List<SpecifyMachine> selectEnabledSpecify();

    /**
     * 根据日期范围查询有效的定点机台列表
     *
     * @param materialCode 物料编号
     * @param effectDate 生效日期
     * @return 定点机台列表
     */
    List<SpecifyMachine> selectEffectiveSpecify(@Param("materialCode") String materialCode,
                                                 @Param("effectDate") Date effectDate);

    /**
     * 查询物料可用的机台编号列表
     *
     * @param materialCode 物料编号
     * @param effectDate 生效日期
     * @return 机台编号列表
     */
    List<String> selectAvailableMachineCodes(@Param("materialCode") String materialCode,
                                              @Param("effectDate") Date effectDate);

    /**
     * 查询物料不可用的机台编号列表
     *
     * @param materialCode 物料编号
     * @param effectDate 生效日期
     * @return 机台编号列表
     */
    List<String> selectUnavailableMachineCodes(@Param("materialCode") String materialCode,
                                                @Param("effectDate") Date effectDate);
}
