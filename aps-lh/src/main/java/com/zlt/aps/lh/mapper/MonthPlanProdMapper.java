package com.zlt.aps.lh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlt.aps.lh.api.domain.MonthPlanProd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 月度计划产品Mapper接口
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Mapper
public interface MonthPlanProdMapper extends BaseMapper<MonthPlanProd> {

    /**
     * 根据计划编号查询计划产品列表
     *
     * @param planCode 计划编号
     * @return 计划产品列表
     */
    List<MonthPlanProd> selectByPlanCode(@Param("planCode") String planCode);

    /**
     * 根据分厂编号和计划月份查询计划产品列表
     *
     * @param factoryCode 分厂编号
     * @param planMonth 计划月份
     * @return 计划产品列表
     */
    List<MonthPlanProd> selectByFactoryAndMonth(@Param("factoryCode") String factoryCode,
                                                  @Param("planMonth") Date planMonth);

    /**
     * 根据物料编号查询计划产品列表
     *
     * @param materialCode 物料编号
     * @param planMonth 计划月份
     * @return 计划产品列表
     */
    List<MonthPlanProd> selectByMaterialCode(@Param("materialCode") String materialCode,
                                               @Param("planMonth") Date planMonth);

    /**
     * 查询未完成的计划产品列表(未排产数量>0)
     *
     * @param planCode 计划编号
     * @return 计划产品列表
     */
    List<MonthPlanProd> selectUnfinishedProducts(@Param("planCode") String planCode);

    /**
     * 更新已排产数量
     *
     * @param id 计划产品ID
     * @param quantity 排产数量
     * @return 更新数量
     */
    int updateScheduledQuantity(@Param("id") Long id, @Param("quantity") Integer quantity);

    /**
     * 批量更新已排产数量
     *
     * @param list 更新列表
     * @return 更新数量
     */
    int batchUpdateScheduledQuantity(@Param("list") List<MonthPlanProd> list);
}
