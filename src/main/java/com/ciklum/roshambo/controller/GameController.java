package com.ciklum.roshambo.controller;

import com.ciklum.roshambo.model.Round;
import com.ciklum.roshambo.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Game Roshambo controller.
 * Provide all operations for playing.
 */
@RestController
@Api(value = "Game API Controller")
public class GameController {

  private final Logger logger = LoggerFactory.getLogger(GameController.class);

  @Autowired
  GameService gameService;

  @ApiOperation(value = "/start", notes = "${api.swagger.operation.start}")
  @GetMapping("/start")
  public ResponseEntity<String> start() {
    String game = gameService.newGame();
    logger.info("New game started with uuid: {}", game);
    return ResponseEntity.ok(game);
  }

  @ApiOperation(value = "/stats", notes = "${api.swagger.operation.round}")
  @GetMapping("/stats")
  public ResponseEntity<String> stats() {
    logger.info("Getting game Statistics");
    return ResponseEntity.ok(gameService.stats());
  }

  @ApiOperation(value = "/{uuid}/round", notes = "${api.swagger.operation.round}")
  @ApiImplicitParams(
      @ApiImplicitParam(name="uuid", value="${api.swagger.param}", required = true)
  )
  @GetMapping(value = "/{uuid}/round", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Round> playRound(@PathVariable(value = "uuid") String uuid) {
    logger.info("New round {}", uuid);
    return ResponseEntity.ok(gameService.newRound(uuid));
  }

  @GetMapping(value = "/{uuid}/rounds")
  @ApiOperation(value = "/{uuid}/rounds", notes = "${api.swagger.operations.rounds}")
  @ApiImplicitParams(
      @ApiImplicitParam(name="uuid", value="${api.swagger.param}", required = true)
  )
  public ResponseEntity<Integer> countRounds(@PathVariable(value = "uuid") String uuid) {
    logger.info("Counting all rounds: {}", uuid);
    return ResponseEntity.ok(gameService.countRounds(uuid));
  }

  @GetMapping(value = "/{uuid}/restart")
  @ApiOperation(value = "/{uuid}/rounds", notes = "${api.swagger.operations.restart}")
  @ApiImplicitParams(
      @ApiImplicitParam(name="uuid", value="${api.swagger.param}", required = true)
  )
  public ResponseEntity<Void> restart(@PathVariable(value = "uuid") String uuid) {
    logger.info("Restarting game: {}", uuid);
    gameService.restart(uuid);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
