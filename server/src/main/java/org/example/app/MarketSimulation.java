package org.example.app;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

enum ResourceType {
    A, B, C, MONEY
}

class Balance {
    private final Map<ResourceType, Integer> resources = new EnumMap<>(ResourceType.class);

    void add(ResourceType type, int amount) {
        resources.put(type, resources.getOrDefault(type, 0) + amount);
    }

    int get(ResourceType type) {
        return resources.getOrDefault(type, 0);
    }
}

enum DayStatus {
    PRODUCTION, TRADING
}

class Game {
    final String name;
    final int lastDay;
    private int currentDay = 1;
    private DayStatus dayState = DayStatus.PRODUCTION;
    private final List<String> tradeOrder;
    private int currentBuyer = 0;

    Game(String name, int lastDay, List<String> manufacturers) {
        this.name = name;
        this.lastDay = lastDay;

        this.tradeOrder = initManufacturers(manufacturers);
    }

    void nextDay() {
        currentDay++;
        dayState = DayStatus.PRODUCTION;
    }

    void toTrading() {
        dayState = DayStatus.TRADING;
    }

    int getCurrentDay() {
        return currentDay;
    }

    boolean isOver() {
        return currentDay > lastDay;
    }


    DayStatus getDayState() {
        return dayState;
    }

    List<String> currentTradeOrder() {
        return List.copyOf(tradeOrder);
    }

    void nextBuyer() {
        currentBuyer++;
        currentBuyer %= tradeOrder.size();
    }

    void nextTradeOrder() {
        tradeOrder.add(tradeOrder.removeFirst());
    }

    private static List<String> initManufacturers(List<String> manufacturers) {
        var mutableManufacturers = new ArrayList<>(manufacturers);
        Collections.shuffle(mutableManufacturers);
        return List.copyOf(mutableManufacturers);

    }
}

@Component
class GameRepository {
    private final Map<String, Game> games = new HashMap<>();

    Game getGame(String name) {
        if (!games.containsKey(name)) {
            throw new RuntimeException("Game " + name + " not found");
        }
        return games.get(name);
    }

    List<Game> getAllGames() {
        return new ArrayList<>(games.values());
    }

    void save(Game game) {
        games.put(game.name, game);
    }
}

@Builder
record StartGameManufacturer(
        String name,
        String productionType,
        int demandA,
        int demandB,
        int demandC
) {}

@Builder
record StartGameCommand(
        String name,
        int lastDay,
        int resourceAProductionCost,
        int resourceBProductionCost,
        int resourceCProductionCost,
        List<StartGameManufacturer> manufacturers
) {}

record StartGameResult(int status, String gameName, String message) {}

@Component
@RequiredArgsConstructor
class StartGameCommandHandler {
    private static final int SUCCESS = 0;
    private static final int INCORRECT_NUMBER_OF_MANUFACTURERS = 400;
    private static final String SUCCESSFUL_MESSAGE = "Game %s successfully created.";

    private final GameRepository gameRepository;
    private final ManufacturerRepository manufacturerRepository;

    StartGameResult handle(StartGameCommand command) {
        if (command.manufacturers().size() < 6 || command.manufacturers().size() > 15) {
            return new StartGameResult(
                    INCORRECT_NUMBER_OF_MANUFACTURERS,
                    null,
                    "Expected 6 or more manufacturers and less than 15."
            );
        }

        Game game = new Game(
                command.name(),
                command.lastDay(),
                command.manufacturers().stream().map(StartGameManufacturer::name).toList()
        );

        gameRepository.save(game);
        buildManufacturers(command).forEach(manufacturerRepository::save);

        return new StartGameResult(SUCCESS, command.name(), String.format(SUCCESSFUL_MESSAGE, command.name()));
    }

    private List<Manufacturer> buildManufacturers(StartGameCommand command) {
        List<Manufacturer> manufacturers = new ArrayList<>();
        for (var manufacturer1 : command.manufacturers()) {
            ResourceType playerProductionResourceType = ResourceType.valueOf(manufacturer1.productionType());

            Demands demands = new Demands(
                    manufacturer1.demandA(),
                    manufacturer1.demandB(),
                    manufacturer1.demandC()
            );

            Manufacturer manufacturer = new Manufacturer(
                    manufacturer1.name(),
                    command.name(),
                    playerProductionResourceType,
                    getProductionCost(command, playerProductionResourceType),
                    demands
            );

            manufacturers.add(manufacturer);
        }

        return manufacturers;
    }

    private int getProductionCost(StartGameCommand command, ResourceType resourceType) {
        if (resourceType == ResourceType.A) {
            return command.resourceAProductionCost();
        }

        if (resourceType == ResourceType.B) {
            return command.resourceBProductionCost();
        }

        if (resourceType == ResourceType.C) {
            return command.resourceCProductionCost();
        }

        throw new RuntimeException("Unexpected resource type: " + resourceType);
    }
}
