package org.example.app;

import lombok.Builder;

@Builder
public record ProceedToTradingCommandResult(
        String status,
        String reason
) {}
