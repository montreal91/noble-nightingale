package org.example.app;

import jakarta.validation.constraints.NotNull;

public record SetSellingPriceCommandRequest(
        @NotNull
        String gameName,
        @NotNull
        String manufacturerName,
        int sellingPrice
) {}
