package com.ciklum.roshambo.service;

import com.ciklum.roshambo.model.Game;
import com.ciklum.roshambo.model.Round;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.ciklum.roshambo.model.Player.RANDOM_PLAYER;
import static com.ciklum.roshambo.model.Player.ROCK_PLAYER;

@Service
public class GameService {

  private final Map<String, Game> games = new ConcurrentHashMap<>();

  public String newGame() {
    Game game = new Game();
    game.setUuid(UUID.randomUUID().toString());
    game.setPlayer1(ROCK_PLAYER);
    game.setPlayer2(RANDOM_PLAYER);
    games.put(game.getUuid(), game);
    return game.getUuid();
  }

  public Round round(String uuid) {
    Game game = games.get(uuid);
    Round round = new Round();
    round.setShapeP1(game.getPlayer1().play());
    round.setShapeP2(game.getPlayer2().play());
    round.setResult(round.getShapeP1().strongerThen(round.getShapeP2()).getFriendlyName());
    game.getRounds().add(round);
    return round;
  }

  public void reset(String uuid) {
    games.get(uuid).getRounds().clear();
  }

}
