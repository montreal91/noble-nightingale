package org.example.app;

import lombok.Builder;

import java.util.List;

@Builder
record GameListView(String name, boolean isOver) {}

@Builder
public record AllGamesResponse(List<GameListView> games) {}
