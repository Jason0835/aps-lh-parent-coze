package com.zlt.aps.lh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlt.aps.lh.api.domain.SkuLhCapacity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SKU硫化产能Mapper接口
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Mapper
public interface SkuLhCapacityMapper extends BaseMapper<SkuLhCapacity> {

    /**
     * 根据物料编号查询产能配置列表
     *
     * @param materialCode 物料编号
     * @return 产能配置列表
     */
    List<SkuLhCapacity> selectByMaterialCode(@Param("materialCode") String materialCode);

    /**
     * 根据机台编号查询产能配置列表
     *
     * @param machineCode 机台编号
     * @return 产能配置列表
     */
    List<SkuLhCapacity> selectByMachineCode(@Param("machineCode") String machineCode);

    /**
     * 根据物料编号和机台编号查询产能配置
     *
     * @param materialCode 物料编号
     * @param machineCode 机台编号
     * @return 产能配置
     */
    SkuLhCapacity selectByMaterialAndMachine(@Param("materialCode") String materialCode,
                                              @Param("machineCode") String machineCode);

    /**
     * 查询启用的产能配置列表
     *
     * @return 产能配置列表
     */
    List<SkuLhCapacity> selectEnabledCapacity();

    /**
     * 根据分厂编号查询产能配置列表
     *
     * @param factoryCode 分厂编号
     * @return 产能配置列表
     */
    List<SkuLhCapacity> selectByFactoryCode(@Param("factoryCode") String factoryCode);

    /**
     * 批量更新产能配置
     *
     * @param list 产能配置列表
     * @return 更新数量
     */
    int batchUpdateCapacity(@Param("list") List<SkuLhCapacity> list);
}
