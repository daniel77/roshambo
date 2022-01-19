package com.ciklum.roshambo.model;

import lombok.Data;

@Data
public class Round {
  private Shape shapeP1;
  private Shape shapeP2;
  private String result;
}
