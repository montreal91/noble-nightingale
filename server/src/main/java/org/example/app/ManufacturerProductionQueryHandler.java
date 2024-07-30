package org.example.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ManufacturerProductionQueryHandler {
    private final GameRepository gameRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final DealToResponseMapper dealMapper;

    public ManufacturerProductionQueryResult handle(ManufacturerProductionQuery query) {
        Game game = gameRepository.getGameOrThrow(query.gameName());
        Manufacturer manufacturer = manufacturerRepository.getManufacturerOrThrow(
                query.manufacturerName(), query.gameName()
        );

        var demand = manufacturer
                .getDemand()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(e -> e.getKey().name(), Map.Entry::getValue));

        var balance = BalanceView.builder()
                .money(manufacturer.getBalance().get(ResourceType.MONEY))
                .resourceA(manufacturer.getBalance().get(ResourceType.A))
                .resourceB(manufacturer.getBalance().get(ResourceType.B))
                .resourceC(manufacturer.getBalance().get(ResourceType.C))
                .build();

        var deals = game.currentDeals()
                .stream()
                .map(dealMapper::map)
                .toList();

        return ManufacturerProductionQueryResult
                .builder()
                .balance(balance)
                .currentDay(game.getCurrentDay())
                .sellingPrice(manufacturer.getSellingPrice())
                .lastDay(game.lastDay)
                .productionResource(manufacturer.getProductionResource().name())
                .productionCost(manufacturer.getProductionCost())
                .tradeOrder(game.currentTradeOrder())
                .ownDemand(demand)
                .totalDemand(getTotalDemand(
                        manufacturer.getProductionResource(), query.gameName()
                ))
                .dealHistory(deals)
                .isReadyForTrading(manufacturer.isReadyForTrading())
                .build();
    }

    private int getTotalDemand(ResourceType type, String gameName) {
        var mfs = manufacturerRepository.getGameManufacturers(gameName);
        var totalDemand = 0;

        for (var mf : mfs) {
            totalDemand += mf.getDemand().get(type);
        }

        return totalDemand;
    }
}
