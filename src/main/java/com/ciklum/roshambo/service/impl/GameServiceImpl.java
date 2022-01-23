package com.ciklum.roshambo.service.impl;

import com.ciklum.roshambo.model.Game;
import com.ciklum.roshambo.model.Result;
import com.ciklum.roshambo.model.Round;
import com.ciklum.roshambo.model.Shape;
import com.ciklum.roshambo.service.GameService;
import com.ciklum.roshambo.service.RoshamboStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameServiceImpl implements GameService {
  private final Map<String, Game> games = new ConcurrentHashMap<>();

  @Autowired
  RoshamboStatsService stats;

  @Override
  public String newGame() {
    Game game = new Game(UUID.randomUUID().toString());
    games.put(game.getUuid(), game);
    return game.getUuid();
  }

  @Override
  public Round newRound(String uuid) {
    Game game = games.get(uuid);
    if (game == null) throw new GameNotFoundException();
    Round round = Round.builder().shapeP1(game.getPlayer1().play()).shapeP2(game.getPlayer2().play()).build();
    round.setResult(evaluate(round.getShapeP1(), round.getShapeP2()).getFriendlyName());
    game.getRounds().add(round);
    stats.incrementTotalRoundsPlayed();
    return round;
  }

  @Override
  public Result evaluate(Shape shapeP1, Shape shapeP2) {
    if (shapeP1.getStrongerThen() == shapeP2) {
      stats.incrementP1Wins();
      return Result.P1_WINS;
    } else if (shapeP1 == shapeP2) {
      stats.incrementDraws();
      return Result.DRAW;
    }
    stats.incrementP2Wins();
    return Result.P2_WINS;
  }

  @Override
  public void restart(String uuid) {
    Game game = games.get(uuid);
    if (game == null) throw new GameNotFoundException();
    game.getRounds().clear();
  }

  @Override
  public int countRounds(String uuid) {
    Game game = games.get(uuid);
    if (game == null) throw new GameNotFoundException();
    return game.getRounds().size();
  }

  @Override
  public String stats() {
    return stats.getStatistics();
  }
}
