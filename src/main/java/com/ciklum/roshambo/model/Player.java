package com.ciklum.roshambo.model;

import java.util.Random;

/**
 * Represent one player of the match.
 */
@FunctionalInterface
public interface Player {
  /**
   * Play a shape
   */
  Shape play();

  /**
   * Choose a shape randomly
   * @return random shape
   */
  static Shape randomShape()  {
    return Shape.values()[new Random().nextInt(Shape.values().length)];
  }
}
