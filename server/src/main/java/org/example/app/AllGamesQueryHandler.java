package org.example.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AllGamesQueryHandler {
    private final GameRepository gameRepository;

    public AllGamesResponse handle() {
        var games = gameRepository.getAllGames();
        return new AllGamesResponse(
                games.stream().map(game -> new GameListView(game.name)).toList()
        );
    }
}
