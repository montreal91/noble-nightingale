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
public class ProduceCommandController {
    private final ProduceCommandHandler handler;
    private final ProduceCommandMapper mapper;

    @PostMapping("/produce/")
    public ResponseEntity<ProduceCommandResult> produce(
            @Valid @RequestBody
            ProduceCommandRequest command
    ) {
        return ResponseEntity.ok(handler.handle(mapper.map(command)));
    }
}
