package org.example.app;

import lombok.Builder;

@Builder
public record Deal(
        int day,
        ResourceType resourceType,
        String sellerName,
        String buyerName,
        int resourceAmount,
        int resourcePrice
) {}
