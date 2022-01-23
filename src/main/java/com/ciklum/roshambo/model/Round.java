package com.ciklum.roshambo.model;

import lombok.Builder;
import lombok.Data;

/**
 * Class to represent a round
 */
@Data
@Builder
public class Round {
  /**
   * Shape chosen by player one
   */
  private Shape shapeP1;

  /**
   * Shape chosen by player two
   */
  private Shape shapeP2;

  /**
   * Result with friendly name.
   */
  private String result;
}
