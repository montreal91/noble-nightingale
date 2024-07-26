package org.example.app;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AllGamesController {
    private final AllGamesQueryHandler allGamesQueryHandler;

    @PostMapping("/all-games/")
    ResponseEntity<AllGamesResponse> allGames() {
        return ResponseEntity.ok(allGamesQueryHandler.handle());
    }
}
