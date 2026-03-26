package com.zlt.aps.lh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlt.aps.lh.api.domain.WorkCalendar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 工作日历Mapper接口
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Mapper
public interface WorkCalendarMapper extends BaseMapper<WorkCalendar> {

    /**
     * 根据分厂编号查询工作日历列表
     *
     * @param factoryCode 分厂编号
     * @return 工作日历列表
     */
    List<WorkCalendar> selectByFactoryCode(@Param("factoryCode") String factoryCode);

    /**
     * 根据日期范围查询工作日历列表
     *
     * @param factoryCode 分厂编号
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 工作日历列表
     */
    List<WorkCalendar> selectByDateRange(@Param("factoryCode") String factoryCode,
                                          @Param("startDate") Date startDate,
                                          @Param("endDate") Date endDate);

    /**
     * 查询工作日列表
     *
     * @param factoryCode 分厂编号
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 工作日历列表
     */
    List<WorkCalendar> selectWorkDays(@Param("factoryCode") String factoryCode,
                                       @Param("startDate") Date startDate,
                                       @Param("endDate") Date endDate);

    /**
     * 查询休息日列表
     *
     * @param factoryCode 分厂编号
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 工作日历列表
     */
    List<WorkCalendar> selectRestDays(@Param("factoryCode") String factoryCode,
                                       @Param("startDate") Date startDate,
                                       @Param("endDate") Date endDate);

    /**
     * 根据日期查询工作日历
     *
     * @param factoryCode 分厂编号
     * @param calendarDate 日历日期
     * @return 工作日历
     */
    WorkCalendar selectByDate(@Param("factoryCode") String factoryCode,
                               @Param("calendarDate") Date calendarDate);

    /**
     * 判断是否为工作日
     *
     * @param factoryCode 分厂编号
     * @param calendarDate 日历日期
     * @return 是否为工作日
     */
    Boolean isWorkDay(@Param("factoryCode") String factoryCode,
                      @Param("calendarDate") Date calendarDate);
}
