package org.example.app;

import org.springframework.stereotype.Component;

@Component
public class DealToResponseMapper {
    public ResponseDeal map(Deal deal) {
        return ResponseDeal.builder()
                .day(deal.day())
                .buyerName(deal.buyerName())
                .sellerName(deal.sellerName())
                .resourceType(deal.resourceType())
                .resourceAmount(deal.resourceAmount())
                .resourcePrice(deal.resourcePrice())
                .build();
    }
}
