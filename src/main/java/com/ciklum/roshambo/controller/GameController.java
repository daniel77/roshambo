package com.ciklum.roshambo.controller;

import com.ciklum.roshambo.model.Round;
import com.ciklum.roshambo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class GameController {

  @Autowired
  GameService gameService;

  @GetMapping("/start")
  public ResponseEntity<String> start() {
    return ResponseEntity.ok(gameService.newGame());
  }

  @GetMapping(value = "/{uuid}/round", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Round> playRound(@PathVariable(value = "uuid") String uuid) {
    return ResponseEntity.ok(gameService.round(uuid));
  }

  @GetMapping(value = "/{uuid}/restart")
  public ResponseEntity<Void> restart(@PathVariable(value = "uuid") String uuid) {
    gameService.reset(uuid);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
