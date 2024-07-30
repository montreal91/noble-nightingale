package org.example.app;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class FinishTradingCommandController {
    private final FinishTradingCommandHandler handler;
    private final FinishTradingCommandMapper mapper;

    @PostMapping("/finish-trading/")
    public ResponseEntity<FinishTradingCommandResult> finishTrading(
            @RequestBody @Valid
            FinishTradingCommandRequest request
    ) {
        return ResponseEntity.ok(handler.handle(mapper.map(request)));
    }
}
