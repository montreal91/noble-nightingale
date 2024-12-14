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
public class BuyResourceCommandController {
    private final BuyResourceCommandHandler handler;
    private final BuyResourceCommandMapper mapper;

    @PostMapping("/buy-resource/")
    ResponseEntity<BuyResourceCommandResult> buyResource(
            @Valid @RequestBody
            BuyResourceCommandRequest request
    ) {
        return ResponseEntity.ok(handler.handle(mapper.map(request)));
    }
}
