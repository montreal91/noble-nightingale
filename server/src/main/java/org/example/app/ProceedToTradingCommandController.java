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
public class ProceedToTradingCommandController {
    private final ProceedToTradingCommandHandler handler;
    private final ProceedToTradingCommandMapper mapper;

    @PostMapping("/proceed-to-trading/")
    public ResponseEntity<ProceedToTradingCommandResult> prepareForTrading(
            @Valid @RequestBody
            ProceedToTradingCommandRequest request
    ) {
        return ResponseEntity.ok(handler.handle(mapper.map(request)));
    }
}
