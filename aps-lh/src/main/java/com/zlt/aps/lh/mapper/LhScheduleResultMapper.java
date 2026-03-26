package com.zlt.aps.lh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zlt.aps.lh.api.domain.LhScheduleResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 硫化排程结果Mapper接口
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Mapper
public interface LhScheduleResultMapper extends BaseMapper<LhScheduleResult> {

    /**
     * 根据排程日期查询排程结果
     *
     * @param scheduleDate 排程日期
     * @return 排程结果列表
     */
    List<LhScheduleResult> selectByScheduleDate(@Param("scheduleDate") Date scheduleDate);

    /**
     * 根据机台编号查询排程结果
     *
     * @param machineCode 机台编号
     * @param scheduleDate 排程日期
     * @return 排程结果列表
     */
    List<LhScheduleResult> selectByMachineCode(@Param("machineCode") String machineCode, 
                                                @Param("scheduleDate") Date scheduleDate);

    /**
     * 批量插入排程结果
     *
     * @param list 排程结果列表
     * @return 插入数量
     */
    int batchInsert(@Param("list") List<LhScheduleResult> list);

    /**
     * 根据排程日期删除排程结果
     *
     * @param scheduleDate 排程日期
     * @return 删除数量
     */
    int deleteByScheduleDate(@Param("scheduleDate") Date scheduleDate);

    /**
     * 根据机台编号和班次更新排程状态
     *
     * @param machineCode 机台编号
     * @param shift 班次
     * @param scheduleDate 排程日期
     * @param status 状态
     * @return 更新数量
     */
    int updateStatusByMachineAndShift(@Param("machineCode") String machineCode,
                                       @Param("shift") String shift,
                                       @Param("scheduleDate") Date scheduleDate,
                                       @Param("status") String status);

    /**
     * 查询机台的最大序号
     *
     * @param machineCode 机台编号
     * @param scheduleDate 排程日期
     * @return 最大序号
     */
    Integer selectMaxSeqByMachineAndDate(@Param("machineCode") String machineCode,
                                          @Param("scheduleDate") Date scheduleDate);
}
