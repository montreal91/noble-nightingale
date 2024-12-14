package org.example.app;

import jakarta.validation.constraints.NotNull;

public record ManufacturerTradingQueryRequest(
        @NotNull
        String gameName,
        @NotNull
        String manufacturerName
) {}
