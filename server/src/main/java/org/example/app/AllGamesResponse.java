package org.example.app;

import java.util.List;

record GameListView(String name) {}

public record AllGamesResponse(List<GameListView> games) {}
