package org.example.app;

import jakarta.validation.constraints.NotNull;

public record ProduceCommandRequest(
        @NotNull
        String gameName,
        @NotNull
        String manufacturerName,
        int amount
) {}
