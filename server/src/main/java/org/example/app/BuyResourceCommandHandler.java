package org.example.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class BuyResourceCommandHandler {
    private final ManufacturerRepository manufacturerRepository;
    private final GameRepository gameRepository;

    public BuyResourceCommandResult handle(BuyResourceCommand command) {
        if (Objects.equals(command.buyerName(), command.sellerName())) {
            return BuyResourceCommandResult
                    .builder()
                    .status("Failure")
                    .reason("One can't buy from oneself.")
                    .build();
        }

        var game = gameRepository.getGameOrThrow(command.gameName());

        if (!game.isCurrentBuyer(command.buyerName())) {
            return BuyResourceCommandResult
                    .builder()
                    .status("Failure")
                    .reason("[" + command.buyerName() + "] is not currently a buyer")
                    .build();
        }

        var buyer = manufacturerRepository.getManufacturerOrThrow(
                command.buyerName(), command.gameName()
        );

        var seller = manufacturerRepository.getManufacturerOrThrow(
                command.sellerName(), command.gameName()
        );

        if (seller.getResourceForSale() < command.amount()) {
            return BuyResourceCommandResult
                    .builder()
                    .status("Failure")
                    .reason("Seller does not have enough resource for sale")
                    .build();
        }

        var transactionCost = command.amount() * seller.getSellingPrice();
        buyer.addResource(seller.getProductionResource(), command.amount());
        buyer.addResource(ResourceType.MONEY, -transactionCost);
        seller.addResource(seller.getProductionResource(), -command.amount());
        seller.addResource(ResourceType.MONEY, transactionCost);

        game.makeDeal(
                seller.getName(),
                seller.getProductionResource(),
                command.amount(),
                seller.getSellingPrice()
        );

        gameRepository.save(game);
        manufacturerRepository.save(buyer);
        manufacturerRepository.save(seller);

        return BuyResourceCommandResult
                .builder()
                .status("Success")
                .reason("Everything is fine")
                .build();
    }
}
