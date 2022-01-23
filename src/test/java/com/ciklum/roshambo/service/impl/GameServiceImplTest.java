package com.ciklum.roshambo.service.impl;

import com.ciklum.roshambo.model.Result;
import com.ciklum.roshambo.model.Round;
import com.ciklum.roshambo.model.Shape;
import com.ciklum.roshambo.service.RoshamboStatsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

  @InjectMocks
  @Autowired
  private static GameServiceImpl gameService;

  @Mock
  private RoshamboStatsService stats;

  @Test
  void newGame() {
    String game = gameService.newGame();
    assertNotNull(game);
  }

  @Test
  void newRound() {
    String gameUuid = gameService.newGame();
    Round round = gameService.newRound(gameUuid);
    assertEquals(Shape.ROCK, round.getShapeP1());
    verify(this.stats, times(1))
        .incrementTotalRoundsPlayed();
  }

  @Test
  void evaluateP1Wins() {
    Result result = gameService.evaluate(Shape.ROCK, Shape.SCISSORS);
    assertEquals(Result.P1_WINS, result);
    verify(this.stats, times(1))
        .incrementP1Wins();
  }

  @Test
  void evaluateP2Wins() {
    Result result = gameService.evaluate(Shape.SCISSORS, Shape.ROCK);
    assertEquals(Result.P2_WINS, result);
    verify(this.stats, times(1))
        .incrementP2Wins();
  }

  @Test
  void evaluatDraw() {
    Result result = gameService.evaluate(Shape.SCISSORS, Shape.SCISSORS);
    assertEquals(Result.DRAW, result);
    verify(this.stats, times(1))
        .incrementDraws();
  }

  @Test
  void restart() {
    String gameUuid = gameService.newGame();
    IntStream.rangeClosed(0, 4)
        .forEach(value -> gameService.newRound(gameUuid));
    gameService.restart(gameUuid);
    assertEquals(0, gameService.countRounds(gameUuid));
  }

  @Test
  void countRounds() {
    String gameUuid = gameService.newGame();
    int totalRounds = 4;
    IntStream.rangeClosed(1, totalRounds)
        .forEach(value -> gameService.newRound(gameUuid));
    verify(this.stats, times(totalRounds))
        .incrementTotalRoundsPlayed();
    assertEquals(totalRounds, gameService.countRounds(gameUuid));
  }

  @Test
  void stats() {
    gameService.stats();
    verify(this.stats, times(1))
        .getStatistics();
  }
}