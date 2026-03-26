package com.zlt.aps.lh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlt.aps.lh.api.domain.SkuMouldRel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SKU模具关系Mapper接口
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Mapper
public interface SkuMouldRelMapper extends BaseMapper<SkuMouldRel> {

    /**
     * 根据物料编号查询模具关系列表
     *
     * @param materialCode 物料编号
     * @return 模具关系列表
     */
    List<SkuMouldRel> selectByMaterialCode(@Param("materialCode") String materialCode);

    /**
     * 根据模具编号查询物料关系列表
     *
     * @param mouldCode 模具编号
     * @return 模具关系列表
     */
    List<SkuMouldRel> selectByMouldCode(@Param("mouldCode") String mouldCode);

    /**
     * 根据物料编号和模具编号查询关系
     *
     * @param materialCode 物料编号
     * @param mouldCode 模具编号
     * @return 模具关系
     */
    SkuMouldRel selectByMaterialAndMould(@Param("materialCode") String materialCode,
                                          @Param("mouldCode") String mouldCode);

    /**
     * 查询启用的模具关系列表
     *
     * @return 模具关系列表
     */
    List<SkuMouldRel> selectEnabledRel();

    /**
     * 根据分厂编号查询模具关系列表
     *
     * @param factoryCode 分厂编号
     * @return 模具关系列表
     */
    List<SkuMouldRel> selectByFactoryCode(@Param("factoryCode") String factoryCode);

    /**
     * 查询物料对应的模具列表
     *
     * @param materialCode 物料编号
     * @return 模具编号列表
     */
    List<String> selectMouldCodesByMaterial(@Param("materialCode") String materialCode);

    /**
     * 查询模具对应的物料列表
     *
     * @param mouldCode 模具编号
     * @return 物料编号列表
     */
    List<String> selectMaterialCodesByMould(@Param("mouldCode") String mouldCode);
}
