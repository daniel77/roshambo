package com.ciklum.roshambo.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent one game.
 */
@Data
@ToString
@RequiredArgsConstructor
public class Game {
  /**
   * UUID of the game
   */
  @NonNull
  private String uuid;
  private Player player1 = () -> Shape.ROCK;
  private Player player2 = Player::randomShape;
  private List<Round> rounds = new ArrayList<>();
}
