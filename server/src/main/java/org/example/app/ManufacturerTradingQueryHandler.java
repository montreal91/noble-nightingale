package org.example.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ManufacturerTradingQueryHandler {
    private final ManufacturerRepository manufacturerRepository;
    private final GameRepository gameRepository;

    public ManufacturerTradingQueryResult handle(ManufacturerTradingQuery query) {
        var game = gameRepository.getGameOrThrow(query.gameName());
        var manufacturers = manufacturerRepository.getGameManufacturers(query.gameName());

        List<Offer> offers = switch (game.getDayStatus()) {
            case TRADING -> manufacturers.stream()
                    .map(ManufacturerTradingQueryHandler::getOffer)
                    .toList();
            case PRODUCTION -> List.of();
        };

        return ManufacturerTradingQueryResult
                .builder()
                .offers(offers)
                .isCurrentTrader(game.isCurrentBuyer(query.manufacturerName()))
                .tradeOrder(game.currentTradeOrder())
                .build();
    }

    private static Offer getOffer(Manufacturer manufacturer) {
        return Offer.builder()
                .manufacturer(manufacturer.getName())
                .resourceType(manufacturer.getProductionResource().name())
                .amount(manufacturer.getResourceForSale())
                .price(manufacturer.getSellingPrice())
                .build();
    }
}
