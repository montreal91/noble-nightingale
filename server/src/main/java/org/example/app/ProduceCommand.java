package org.example.app;

import lombok.Builder;

@Builder
public record ProduceCommand(
        String gameName,
        String manufacturerName,
        int amount
) {}
