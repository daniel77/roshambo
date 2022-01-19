package com.ciklum.roshambo.model;

import java.util.Random;

public enum Shape {
  ROCK,
  SCISSORS,
  PAPER;

  private Shape strongerThen;
  
  static {
    ROCK.strongerThen = SCISSORS;
    SCISSORS.strongerThen = PAPER;
    PAPER.strongerThen = ROCK;
  }

  public Result strongerThen(Shape shape) {
    if (this.strongerThen == shape) return Result.P1_WIN;
    else if (this == shape) return Result.DRAW;
    return Result.P2_WIN;
  }

  public static Shape randomElement()  {
    return Shape.values()[new Random().nextInt(Shape.values().length)];
  }

}
