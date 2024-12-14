package org.example.app;

import lombok.Builder;

@Builder
public record ResponseDeal(
        int day,
        String resourceType,
        String sellerName,
        String buyerName,
        int resourceAmount,
        int resourcePrice
) {}
