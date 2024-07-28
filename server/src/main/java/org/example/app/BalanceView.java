package org.example.app;

import lombok.Builder;

@Builder
public record BalanceView(
        int resourceA,
        int resourceB,
        int resourceC,
        int money
) {}
