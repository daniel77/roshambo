package com.ciklum.roshambo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Result {
  P1_WINS("player 1 wins"), P2_WINS("player 2 wins"), DRAW("draw");

  @Getter
  private final String friendlyName;

}
