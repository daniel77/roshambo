package com.ciklum.roshambo.model;

import lombok.Getter;

import java.util.Comparator;

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
}

