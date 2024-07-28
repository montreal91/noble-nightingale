package org.example.app;

public record Deal(
        int day,
        String resourceType,
        String sellerName,
        String buyerName,
        int resourceAmount,
        int resourcePrice
) {}
