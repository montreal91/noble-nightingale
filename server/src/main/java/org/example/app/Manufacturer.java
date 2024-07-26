package org.example.app;

import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

record Demands(int demandA, int demandB, int demandC) {}

class Pricing {
    final Map<ResourceType, Integer> resources = new EnumMap<>(ResourceType.class);

    void setResourcePrice(ResourceType type, int price) {
        if (type == ResourceType.MONEY) {
            // Money have no price
            return;
        }
        resources.put(type, price);
    }

    int get(ResourceType type) {
        return resources.getOrDefault(type, 0);
    }
}

/**
 * Every Manufacturer is an Agent, but not all Agents are manufacturers.
 */
class Manufacturer implements Agent {
    private final String name;
    private final String gameName;
    private final Balance balance = new Balance();
    private final ResourceType productionResource;
    private final int productionCost;
    final Pricing pricing = new Pricing();
    private final Map<ResourceType, Integer> demand = new EnumMap<>(ResourceType.class);

    Manufacturer(String name, String gameName, ResourceType productionResource, int productionCost, Demands demands) {
        this.name = name;
        this.gameName = gameName;
        this.productionResource = productionResource;
        this.productionCost = productionCost;

        setDemand(demands.demandA(), demands.demandB(), demands.demandC());
    }

    String getName() {
        return name;
    }

    void setSellingPrice(int price) {
        pricing.setResourcePrice(productionResource, price);
    }

    void produce(int amount) {
        balance.add(productionResource, amount);
        balance.add(ResourceType.MONEY, -(amount * productionCost));
    }

    void consume() {
        balance.add(ResourceType.A, -demand.get(ResourceType.A));
        balance.add(ResourceType.B, -demand.get(ResourceType.B));
        balance.add(ResourceType.C, -demand.get(ResourceType.C));
    }

    String getGameName() {
        return gameName;
    }

    private void setDemand(int demandA, int demandB, int demandC) {
        demand.put(ResourceType.A, demandA);
        demand.put(ResourceType.B, demandB);
        demand.put(ResourceType.C, demandC);
    }
}

//class AgentRepository
@Component
class ManufacturerRepository {
    private final Map<String, Manufacturer> accounts = new HashMap<>();

    Manufacturer getAccount(String name, String gameName) {
        String key = makeKey(name, gameName);

        if (!accounts.containsKey(key)) {
            throw new RuntimeException("Account " + name + " not found in game: " + gameName);
        }

        return accounts.get(key);
    }

    void save(Manufacturer manufacturer) {
        String key = makeKey(manufacturer.getName(), manufacturer.getGameName());
        accounts.put(key, manufacturer);
    }

    private String makeKey(String accName, String gameName) {
        return accName + gameName;
    }
}
