package org.example.app;

import lombok.Builder;

@Builder
public record SetSellingPriceCommand(
        String gameName,
        String manufacturerName,
        int sellingPrice
) {}
