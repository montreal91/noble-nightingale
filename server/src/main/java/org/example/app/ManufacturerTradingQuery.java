package org.example.app;

import lombok.Builder;

@Builder
public record ManufacturerTradingQuery(
        String manufacturerName,
        String gameName
) {}
