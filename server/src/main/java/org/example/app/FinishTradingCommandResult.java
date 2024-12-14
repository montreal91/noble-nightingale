package org.example.app;

import lombok.Builder;

@Builder
public record FinishTradingCommandResult(
        String status,
        String reason
) {}
