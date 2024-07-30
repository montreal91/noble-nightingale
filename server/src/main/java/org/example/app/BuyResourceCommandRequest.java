package org.example.app;

import jakarta.validation.constraints.NotNull;

public record BuyResourceCommandRequest(
        @NotNull
        String gameName,
        @NotNull
        String buyerName,
        @NotNull
        String sellerName,
        @NotNull
        Integer amount
) {}
