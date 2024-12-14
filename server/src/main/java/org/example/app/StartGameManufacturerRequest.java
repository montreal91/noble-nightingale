package org.example.app;

import jakarta.validation.constraints.NotNull;

public record StartGameManufacturerRequest(
        @NotNull
        String name,
        @NotNull
        String productionType,
        int demandA,
        int demandB,
        int demandC
) {}
