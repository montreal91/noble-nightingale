package org.example.app;

import org.springframework.stereotype.Component;

@Component
public class FinishTradingCommandMapper {
    public FinishTradingCommand map(FinishTradingCommandRequest request) {
        return FinishTradingCommand
                .builder()
                .gameName(request.gameName())
                .manufacturerName(request.manufacturerName())
                .build();
    }
}
