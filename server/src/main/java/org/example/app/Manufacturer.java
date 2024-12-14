package org.example.app;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
@Getter
class Manufacturer implements Agent {
    private final String name;
    private final String gameName;
    private final Balance balance = new Balance();
    private final ResourceType productionResource;
    private final int productionCost;
    final Pricing pricing = new Pricing();
    private final Map<ResourceType, Integer> demand = new EnumMap<>(ResourceType.class);

    @Getter
    @Setter
    private boolean isReadyForTrading = false;

    Manufacturer(
            String name,
            String gameName,
            ResourceType productionResource,
            int productionCost,
            Demands demands
    ) {
        this.name = name;
        this.gameName = gameName;
        this.productionResource = productionResource;
        this.productionCost = productionCost;

        setDemand(demands.demandA(), demands.demandB(), demands.demandC());
    }

    void addResource(ResourceType type, int amount) {
        balance.add(type, amount);
    }

    int getSellingPrice() {
       return pricing.get(productionResource);
    }

    void setSellingPrice(int price) {
        pricing.setResourcePrice(productionResource, price);
    }

    int getResourceForSale() {
        return balance.get(productionResource);
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

    private void setDemand(int demandA, int demandB, int demandC) {
        demand.put(ResourceType.A, demandA);
        demand.put(ResourceType.B, demandB);
        demand.put(ResourceType.C, demandC);
    }
}

@Component
class ManufacturerRepository {
    private final Map<String, Manufacturer> manufacturers = new HashMap<>();

    Manufacturer getManufacturerOrThrow(String name, String gameName) {
        String key = makeKey(name, gameName);

        if (!manufacturers.containsKey(key)) {
            throw new RuntimeException("Account " + name + " not found in game: " + gameName);
        }

        return manufacturers.get(key);
    }

    List<Manufacturer> getGameManufacturers(String gameName) {
        return manufacturers.values()
                .stream()
                .filter(m -> Objects.equals(gameName, m.getGameName()))
                .toList();
    }

    void save(Manufacturer manufacturer) {
        String key = makeKey(manufacturer.getName(), manufacturer.getGameName());
        manufacturers.put(key, manufacturer);
    }

    void saveAll(List<Manufacturer> manufacturers) {
        manufacturers.forEach(this::save);
    }

    private String makeKey(String accName, String gameName) {
        return accName + gameName;
    }
}
