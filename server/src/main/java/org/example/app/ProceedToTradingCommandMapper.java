package org.example.app;

import org.springframework.stereotype.Component;

@Component
public class ProceedToTradingCommandMapper {
    public ProceedToTradingCommand map(ProceedToTradingCommandRequest request) {
        return ProceedToTradingCommand
                .builder()
                .manufacturerName(request.manufacturerName())
                .gameName(request.gameName())
                .build();
    }
}
