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
public class StartGameController {
    private final StartGameCommandHandler startGameCommandHandler;
    private final StartGameRequestToCommandMapper mapper;

    @PostMapping("/start-game/")
    public ResponseEntity<StartGameResponse> startGame(@RequestBody @Valid StartGameRequest startGameRequest) {
        var command = mapper.map(startGameRequest);
        var result = startGameCommandHandler.handle(command);
        return ResponseEntity.ok(new StartGameResponse(result.gameName()));
    }
}
