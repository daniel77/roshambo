package com.ciklum.roshambo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class to represent a result of round
 */
@AllArgsConstructor
public enum Result {
  P1_WINS("player 1 wins"), P2_WINS("player 2 wins"), DRAW("draw");

  /**
   * Friendly Name for the UI usage.
   */
  @Getter
  private final String friendlyName;

}
