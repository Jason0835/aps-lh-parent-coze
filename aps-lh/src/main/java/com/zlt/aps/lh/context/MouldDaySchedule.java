package com.zlt.aps.lh.context;

import com.zlt.aps.lh.api.domain.LhScheduleResult;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 模具日排程情况
 * 记录某模具在某日的排程情况
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Data
public class MouldDaySchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模具编号
     */
    private String mouldCode;

    /**
     * 排程日期
     */
    private Date scheduleDate;

    /**
     * 使用该模具的排程结果列表
     */
    private List<LhScheduleResult> results = new ArrayList<>();

    /**
     * 已排产数量（汇总）
     */
    private int scheduledQuantity;

    /**
     * 使用该模具的机台列表
     */
    private List<String> machineCodes = new ArrayList<>();

    /**
     * 使用该模具的物料列表
     */
    private List<String> materialCodes = new ArrayList<>();

    /**
     * 是否需要清洗
     */
    private boolean needCleaning;

    /**
     * 添加排程结果
     *
     * @param result 排程结果
     */
    public void addResult(LhScheduleResult result) {
        this.results.add(result);

        // 更新汇总数量
        this.scheduledQuantity += (result.getPlanQuantity() != null ? result.getPlanQuantity() : 0);

        // 更新机台列表
        if (!machineCodes.contains(result.getMachineCode())) {
            machineCodes.add(result.getMachineCode());
        }

        // 更新物料列表
        if (!materialCodes.contains(result.getMaterialCode())) {
            materialCodes.add(result.getMaterialCode());
        }
    }

    /**
     * 获取使用该模具的机台数量
     *
     * @return 机台数量
     */
    public int getMachineCount() {
        return machineCodes.size();
    }

    /**
     * 获取使用该模具的物料数量
     *
     * @return 物料数量
     */
    public int getMaterialCount() {
        return materialCodes.size();
    }

    /**
     * 判断模具是否在某机台上使用
     *
     * @param machineCode 机台编号
     * @return 是否使用
     */
    public boolean isUsedByMachine(String machineCode) {
        return machineCodes.contains(machineCode);
    }

    /**
     * 获取指定机台上的排程结果
     *
     * @param machineCode 机台编号
     * @return 排程结果列表
     */
    public List<LhScheduleResult> getResultsByMachine(String machineCode) {
        List<LhScheduleResult> machineResults = new ArrayList<>();
        for (LhScheduleResult result : results) {
            if (machineCode.equals(result.getMachineCode())) {
                machineResults.add(result);
            }
        }
        return machineResults;
    }

    /**
     * 获取最后一个排程结果
     *
     * @return 最后一个排程结果
     */
    public LhScheduleResult getLastResult() {
        return results.isEmpty() ? null : results.get(results.size() - 1);
    }

    /**
     * 判断模具是否正在生产
     *
     * @return 是否正在生产
     */
    public boolean isInProduction() {
        return !results.isEmpty();
    }

    /**
     * 获取累计使用次数
     *
     * @return 使用次数
     */
    public int getUsageCount() {
        return results.size();
    }
}
