package com.ciklum.roshambo.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@RequiredArgsConstructor
public class Game {
  @NonNull
  private String uuid;
  private Player player1 = () -> Shape.ROCK;
  private Player player2 = Player::randomElement;
  private List<Round> rounds = new ArrayList<>();
}
