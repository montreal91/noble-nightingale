package org.example.app;

import lombok.Builder;

@Builder
public record SetSellingPriceCommandResult(
        String status,
        String reason
) {}
