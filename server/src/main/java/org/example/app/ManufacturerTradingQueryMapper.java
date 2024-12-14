package org.example.app;

import org.springframework.stereotype.Component;

@Component
public class ManufacturerTradingQueryMapper {
    public ManufacturerTradingQuery map(ManufacturerTradingQueryRequest request) {
        return ManufacturerTradingQuery
                .builder()
                .manufacturerName(request.manufacturerName())
                .gameName(request.gameName())
                .build();
    }
}
