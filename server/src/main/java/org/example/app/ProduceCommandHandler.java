package org.example.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProduceCommandHandler {
    private final ManufacturerRepository manufacturerRepository;

    public ProduceCommandResult handle(ProduceCommand command) {
        var manufacturer = manufacturerRepository.getManufacturerOrThrow(
                command.manufacturerName(),
                command.gameName()
        );

        if (manufacturer.isReadyForTrading()) {
            return ProduceCommandResult.builder().status("Rejected").build();
        }

        manufacturer.produce(command.amount());
        manufacturerRepository.save(manufacturer);

        return ProduceCommandResult.builder().status("Success").build();
    }
}
