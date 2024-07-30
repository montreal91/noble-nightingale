package org.example.app;

import lombok.Builder;

@Builder
public record BuyResourceCommand(
        String gameName,
        String buyerName,
        String sellerName,
        int amount
) {}
