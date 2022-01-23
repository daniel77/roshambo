package com.ciklum.roshambo.service;

import com.ciklum.roshambo.model.Result;
import com.ciklum.roshambo.model.Round;
import com.ciklum.roshambo.model.Shape;

/**
 * Service that provides Roshambo game options
 */
public interface GameService {

  /**
   * Start a new game
   * @return UUID of the new game
   */
  String newGame();

  /**
   * New round for the game
   * @param uuid game in question
   * @return new Round
   */
  Round newRound(String uuid);

  /**
   *  A player who decides to play rock will beat another player who has chosen scissors,
   *  but will lose to one who has played paper ("paper covers rock");
   *  a play of paper will lose to a play of scissors ("scissors cuts paper").
   *
   *  If both players choose the same shape, the game is tied
   *
   * @param shapeP1 Shape chosen by player one
   * @param shapeP2 Shape chosen by player two
   * @return Result of the roudn.
   *
   */
  Result evaluate(Shape shapeP1, Shape shapeP2);

  /**
   * Restart the game
   * @param uuid game in question
   */
  void restart(String uuid);

  /**
   * Count how many rounds have been played in current game
   * @param uuid game in question
   * @return total of rounds
   */
  int countRounds(String uuid);

  /**
   * Return all time stats.
   * @return String with all stats.
   */
  String stats();
}
