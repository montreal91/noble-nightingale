package org.example.app;

import jakarta.validation.constraints.NotNull;

public record ManufacturerProductionQuery(
        @NotNull
        String gameName,
        @NotNull
        String manufacturerName
) {}
