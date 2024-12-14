package org.example.app;

import jakarta.validation.constraints.NotNull;

public record ProceedToTradingCommandRequest(
        @NotNull
        String manufacturerName,
        @NotNull
        String gameName
) {}
