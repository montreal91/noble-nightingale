package org.example.app;

import org.springframework.stereotype.Component;

@Component
public class BuyResourceCommandMapper {
    public BuyResourceCommand map(BuyResourceCommandRequest request) {
        return BuyResourceCommand
                .builder()
                .gameName(request.gameName())
                .buyerName(request.buyerName())
                .sellerName(request.sellerName())
                .amount(request.amount())
                .build();
    }
}
