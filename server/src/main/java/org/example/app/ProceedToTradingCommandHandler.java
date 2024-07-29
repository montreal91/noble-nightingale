package org.example.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProceedToTradingCommandHandler {
    private final GameRepository gameRepository;
    private final ManufacturerRepository manufacturerRepository;

    ProceedToTradingCommandResult handle(ProceedToTradingCommand command) {
        var game = gameRepository.getGameOrThrow(command.gameName());
        var manufacturer = manufacturerRepository.getManufacturerOrThrow(
                command.manufacturerName(),
                command.gameName()
        );

        if (manufacturer.isReadyForTrading()) {
            return ProceedToTradingCommandResult
                    .builder()
                    .status("Rejected")
                    .reason(String.format(
                            "Manufacturer [%s] is already ready for trading.",
                            command.manufacturerName()
                    ))
                    .build();
        }

        manufacturer.setReadyForTrading(true);
        manufacturerRepository.save(manufacturer);

        if (allAreReadyForTrading(command.gameName())) {
            game.toTrading();
            gameRepository.save(game);
        }

        return ProceedToTradingCommandResult
                .builder()
                .status("Success")
                .reason("Everything is OK")
                .build();
    }

    private boolean allAreReadyForTrading(String gameName) {
        return manufacturerRepository.getGameManufacturers(gameName)
                .stream()
                .allMatch(Manufacturer::isReadyForTrading);
    }
}
