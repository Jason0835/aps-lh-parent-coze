package com.zlt.aps.lh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlt.aps.lh.api.domain.LhParams;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 硫化参数配置Mapper接口
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Mapper
public interface LhParamsMapper extends BaseMapper<LhParams> {

    /**
     * 根据参数编码查询参数配置
     *
     * @param paramCode 参数编码
     * @return 参数配置
     */
    LhParams selectByParamCode(@Param("paramCode") String paramCode);

    /**
     * 根据参数类型查询参数列表
     *
     * @param paramType 参数类型
     * @return 参数列表
     */
    List<LhParams> selectByParamType(@Param("paramType") String paramType);

    /**
     * 根据分厂编号查询参数列表
     *
     * @param factoryCode 分厂编号
     * @return 参数列表
     */
    List<LhParams> selectByFactoryCode(@Param("factoryCode") String factoryCode);

    /**
     * 查询启用的参数列表
     *
     * @return 参数列表
     */
    List<LhParams> selectEnabledParams();

    /**
     * 根据参数编码更新参数值
     *
     * @param paramCode 参数编码
     * @param paramValue 参数值
     * @return 更新数量
     */
    int updateParamValue(@Param("paramCode") String paramCode, @Param("paramValue") String paramValue);
}
