package org.example.app;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


public record StartGameRequest(
        @NotNull
        String name,
        int lastDay,
        int resourceAProductionCost,
        int resourceBProductionCost,
        int resourceCProductionCost,
        @Size(min = 3, max = 15)
        List<StartGameManufacturerRequest> manufacturers
) {}
