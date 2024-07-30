package org.example.app;

import lombok.Builder;

@Builder
public record BuyResourceCommandResult(
        String status,
        String reason
) {}
