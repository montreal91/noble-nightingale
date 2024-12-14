package org.example.app;

import lombok.Builder;

import java.util.List;

@Builder
record Offer(String manufacturer, String resourceType, int amount, int price) {}

@Builder
public record ManufacturerTradingQueryResult(
        List<Offer> offers,
        boolean isCurrentTrader,
        List<String> tradeOrder
) {}
