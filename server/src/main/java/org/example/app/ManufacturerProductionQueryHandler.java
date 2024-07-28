package org.example.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ManufacturerProductionQueryHandler {
    private final GameRepository gameRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerProductionQueryResult handle(ManufacturerProductionQuery query) {
        Game game = gameRepository.getGame(query.gameName());
        Manufacturer manufacturer = manufacturerRepository.getManufacturer(
                query.manufacturerName(), query.gameName()
        );

        if (game == null || manufacturer == null) {
            return null;
        }

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

        return ManufacturerProductionQueryResult
                .builder()
                .balance(balance)
                .currentDay(game.getCurrentDay())
                .lastDay(game.lastDay)
                .productionResource(manufacturer.getProductionResource().name())
                .productionCost(manufacturer.getProductionCost())
                .tradeOrder(List.of())
                .demand(demand)
                .build();
    }
}
