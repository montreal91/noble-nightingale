package org.example.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AllGamesQueryHandler {
    private final GameRepository gameRepository;

    public AllGamesResponse handle() {
        var games = gameRepository.getAllGames();

        return AllGamesResponse
                .builder()
                .games(games.stream().map(AllGamesQueryHandler::gameToView).toList())
                .build();
    }

    private static GameListView gameToView(Game game) {
        return GameListView
                .builder()
                .isOver(game.isOver())
                .name(game.name)
                .build();
    }
}
