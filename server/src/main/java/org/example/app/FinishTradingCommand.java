package org.example.app;

import lombok.Builder;

@Builder
public record FinishTradingCommand(
        String gameName,
        String manufacturerName
) {}
