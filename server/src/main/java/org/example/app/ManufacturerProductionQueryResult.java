package org.example.app;

import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record ManufacturerProductionQueryResult(
        int currentDay,
        int lastDay,
        String productionResource,
        int productionCost,
        BalanceView balance,
        List<String> tradeOrder,
        List<Deal> dealHistory,
        Map<String, Integer> demand
) {}
