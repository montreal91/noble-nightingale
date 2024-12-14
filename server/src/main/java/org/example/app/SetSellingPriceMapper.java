package org.example.app;

import org.springframework.stereotype.Component;

@Component
public class SetSellingPriceMapper {
    SetSellingPriceCommand map(SetSellingPriceCommandRequest request) {
        return SetSellingPriceCommand
                .builder()
                .sellingPrice(request.sellingPrice())
                .manufacturerName(request.manufacturerName())
                .gameName(request.gameName())
                .build();
    }
}
