package org.example.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SetSellingPriceCommandHandler {
    private final ManufacturerRepository manufacturerRepository;

    public SetSellingPriceCommandResult handle(SetSellingPriceCommand command) {
        var manufacturer = manufacturerRepository.getManufacturerOrThrow(
                command.manufacturerName(),
                command.gameName()
        );

        if (manufacturer == null) {
            return SetSellingPriceCommandResult
                    .builder()
                    .status("Failure")
                    .reason(String.format(
                            "Manufacturer [%s] not found in game [%s]",
                            command.manufacturerName(),
                            command.gameName()))
                    .build();
        }

        if (manufacturer.isReadyForTrading()) {
            return SetSellingPriceCommandResult
                    .builder()
                    .status("Rejected")
                    .reason(String.format(
                            "Manufacturer [%s] is ready for trading",
                            command.manufacturerName()))
                    .build();
        }

        manufacturer.setSellingPrice(command.sellingPrice());
        manufacturerRepository.save(manufacturer);

        return SetSellingPriceCommandResult
                .builder()
                .status("Success")
                .reason("Everything is ok.")
                .build();
    }
}
