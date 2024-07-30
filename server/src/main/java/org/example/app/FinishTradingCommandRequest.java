package org.example.app;

import jakarta.validation.constraints.NotNull;

public record FinishTradingCommandRequest(
        @NotNull
        String gameName,
        @NotNull
        String manufacturerName
) {}
