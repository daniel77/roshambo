package com.ciklum.roshambo.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Round {
  private Shape shapeP1;
  private Shape shapeP2;
  private String result;
}
