package com.ciklum.roshambo.model;

import lombok.Getter;

import java.util.Random;

public enum Shape {
  ROCK,
  SCISSORS,
  PAPER;

  @Getter
  private Shape strongerThen;
  
  static {
    ROCK.strongerThen = SCISSORS;
    SCISSORS.strongerThen = PAPER;
    PAPER.strongerThen = ROCK;
  }

  public static Shape randomElement()  {
    return Shape.values()[new Random().nextInt(Shape.values().length)];
  }

}
