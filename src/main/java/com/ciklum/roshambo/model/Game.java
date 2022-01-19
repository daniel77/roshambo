package com.ciklum.roshambo.model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Game {
  private String uuid;
  private Player player1;
  private Player player2;
  private List<Round> rounds = new ArrayList<>();
}
