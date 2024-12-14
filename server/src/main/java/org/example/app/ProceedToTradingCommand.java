package org.example.app;

import lombok.Builder;

@Builder
public record ProceedToTradingCommand(
        String manufacturerName,
        String gameName
) {}
