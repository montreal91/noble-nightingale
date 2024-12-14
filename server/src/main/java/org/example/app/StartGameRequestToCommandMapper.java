package org.example.app;

import org.springframework.stereotype.Component;

@Component
public class StartGameRequestToCommandMapper {
    StartGameCommand map(StartGameRequest request) {
        var manufacturers = request.manufacturers()
                .stream()
                .map(StartGameRequestToCommandMapper::fromRequest)
                .toList();

        return StartGameCommand.builder()
                .name(request.name())
                .lastDay(request.lastDay())
                .resourceAProductionCost(request.resourceAProductionCost())
                .resourceBProductionCost(request.resourceBProductionCost())
                .resourceCProductionCost(request.resourceCProductionCost())
                .manufacturers(manufacturers)
                .build();
    }

    private static StartGameManufacturer fromRequest(StartGameManufacturerRequest requestManufacturer) {
        return StartGameManufacturer.builder()
                .name(requestManufacturer.name())
                .demandA(requestManufacturer.demandA())
                .demandB(requestManufacturer.demandB())
                .demandC(requestManufacturer.demandC())
                .productionType(requestManufacturer.productionType())
                .build();
    }
}
