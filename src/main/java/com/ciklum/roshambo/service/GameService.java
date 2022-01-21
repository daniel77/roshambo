package com.ciklum.roshambo.service;

import com.ciklum.roshambo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.ciklum.roshambo.model.Player.RANDOM_PLAYER;
import static com.ciklum.roshambo.model.Player.ROCK_PLAYER;

@Service
public class GameService {

  private final Map<String, Game> games = new ConcurrentHashMap<>();

  @Autowired
  StatsService stats;

  public String newGame() {
    Game game = Game.builder().uuid(UUID.randomUUID().toString()).player1(ROCK_PLAYER).player2(RANDOM_PLAYER).
        build();
    games.put(game.getUuid(), game);
    return game.getUuid();
  }

  public Round newRound(String uuid) {
    Game game = games.get(uuid);
    Round round = Round.builder().shapeP1(game.getPlayer1().play()).shapeP2(game.getPlayer2().play()).build();
    round.setResult(strongerThen(round.getShapeP1(), round.getShapeP2()).getFriendlyName());
    game.getRounds().add(round);
    stats.incrementTotalRoundsPlayed();
    return round;
  }



  public Result strongerThen(Shape shapeP1, Shape shapeP2) {
    if (shapeP1.getStrongerThen() == shapeP2){
      stats.incrementP1Wins();
      return Result.P1_WINS;
    }
    else if (shapeP1 == shapeP2){
      stats.incrementP2Wins();
      return Result.DRAW;
    }
    stats.incrementP2Wins();
    return Result.P2_WINS;
  }

  public void reset(String uuid) {
    games.get(uuid).getRounds().clear();
  }

  public int countRounds(String uuid) {
    return games.get(uuid).getRounds().size();
  }
}
