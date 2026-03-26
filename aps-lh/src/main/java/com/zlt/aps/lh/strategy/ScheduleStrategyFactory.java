package com.zlt.aps.lh.strategy;

import com.zlt.aps.lh.api.enums.StrategyTypeEnum;
import com.zlt.aps.lh.strategy.impl.BalancedScheduleStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 排程策略工厂
 * 根据策略类型获取对应的策略实现
 * 
 * @author 自动生成
 * @date 2026-03-25
 */
@Slf4j
@Component
public class ScheduleStrategyFactory {

    @Autowired
    private BalancedScheduleStrategy balancedScheduleStrategy;

    private Map<String, ScheduleStrategy> strategyMap = new HashMap<>();

    @PostConstruct
    public void init() {
        // 注册策略
        strategyMap.put(StrategyTypeEnum.BALANCED.getCode(), balancedScheduleStrategy);
        // 可以继续添加其他策略
        // strategyMap.put(StrategyTypeEnum.PRIORITY.getCode(), priorityScheduleStrategy);
        // strategyMap.put(StrategyTypeEnum.BATCH.getCode(), batchScheduleStrategy);

        log.info("排程策略工厂初始化完成，已注册策略数量: {}", strategyMap.size());
    }

    /**
     * 获取排程策略
     *
     * @param strategyType 策略类型
     * @return 排程策略
     */
    public ScheduleStrategy getStrategy(String strategyType) {
        ScheduleStrategy strategy = strategyMap.get(strategyType);
        if (strategy == null) {
            log.warn("未找到策略类型[{}]对应的策略，使用默认均衡策略", strategyType);
            strategy = balancedScheduleStrategy;
        }
        return strategy;
    }

    /**
     * 注册策略
     *
     * @param strategyType 策略类型
     * @param strategy 策略实现
     */
    public void registerStrategy(String strategyType, ScheduleStrategy strategy) {
        strategyMap.put(strategyType, strategy);
        log.info("注册排程策略: {} -> {}", strategyType, strategy.getName());
    }

    /**
     * 获取所有已注册的策略类型
     *
     * @return 策略类型列表
     */
    public java.util.Set<String> getAllStrategyTypes() {
        return strategyMap.keySet();
    }
}
