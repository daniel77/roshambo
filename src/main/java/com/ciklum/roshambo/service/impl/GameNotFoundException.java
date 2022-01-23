package com.ciklum.roshambo.service.impl;

class GameNotFoundException extends RuntimeException {
  GameNotFoundException() {
    super("Game not found, please check the game's UUID");
  }
}
