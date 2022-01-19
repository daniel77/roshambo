package com.ciklum.roshambo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Result {
  P1_WIN("Player 1 won"), P2_WIN("Player 2 won"), DRAW("Draw");

  @Getter
  private final String friendlyName;

}
