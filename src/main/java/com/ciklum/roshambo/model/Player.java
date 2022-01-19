package com.ciklum.roshambo.model;

public interface Player {
  Shape play();
  Player ROCK_PLAYER = () -> Shape.ROCK;
  Player RANDOM_PLAYER = Shape::randomElement;
}
