package org.example.app;

import org.springframework.stereotype.Component;

@Component
public class ProduceCommandMapper {
    public ProduceCommand map(ProduceCommandRequest request) {
        return ProduceCommand.builder()
                .manufacturerName(request.manufacturerName())
                .gameName(request.gameName())
                .amount(request.amount())
                .build();
    }
}
