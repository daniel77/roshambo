package com.ciklum.roshambo.model;

import java.util.Random;

@FunctionalInterface
public interface Player {
  Shape play();

  static Shape randomElement()  {
    return Shape.values()[new Random().nextInt(Shape.values().length)];
  }
}
