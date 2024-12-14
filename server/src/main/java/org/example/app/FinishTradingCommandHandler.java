package org.example.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FinishTradingCommandHandler {
    private final GameRepository gameRepository;
    private final ManufacturerRepository manufacturerRepository;

    public FinishTradingCommandResult handle(FinishTradingCommand command) {
        var game = gameRepository.getGameOrThrow(command.gameName());
        if (!game.isCurrentBuyer(command.manufacturerName())) {
            return FinishTradingCommandResult
                    .builder()
                    .status("Failure")
                    .reason("[" + command.manufacturerName() + "] is not a current buyer.")
                    .build();
        }

        game.nextBuyer();

        if (game.getDayStatus() == DayStatus.PRODUCTION) {
            proceedManufacturersToTheNextDay(command.gameName());
        }

        gameRepository.save(game);

        return FinishTradingCommandResult
                .builder()
                .status("Success")
                .reason("Everything is fine")
                .build();
    }

    private void proceedManufacturersToTheNextDay(String gameName) {
        var manufacturers = manufacturerRepository.getGameManufacturers(gameName);
        manufacturers.forEach(manufacturer -> {
            manufacturer.setReadyForTrading(false);
            manufacturer.consume();
        });
        manufacturerRepository.saveAll(manufacturers);
    }
}
