package com.ciklum.roshambo.controller;

import com.ciklum.roshambo.model.Round;
import com.ciklum.roshambo.service.GameService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(value = "Game API Controller")
public class GameController {

  @Autowired
  GameService gameService;

  @ApiOperation(value = "/start", notes = "${api.swagger.startOp}")
  @GetMapping("/start")
  public ResponseEntity<String> start() {
    return ResponseEntity.ok(gameService.newGame());
  }


  @GetMapping("/stats")
  public ResponseEntity<String> stats() {
    return ResponseEntity.ok(gameService.stats());
  }

  @ApiOperation(value = "/{uuid}/round", notes = "${api.swagger.roundOp}")
  @ApiImplicitParams(
      @ApiImplicitParam(name="uuid", value="${api.swagger.param}", required = true)
  )
  @GetMapping(value = "/{uuid}/round", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<Round> playRound(@PathVariable(value = "uuid") String uuid) {
    return ResponseEntity.ok(gameService.newRound(uuid));
  }

  @GetMapping(value = "/{uuid}/rounds")
  @ApiOperation(value = "/{uuid}/rounds", notes = "${api.swagger.roundsOp}")
  @ApiImplicitParams(
      @ApiImplicitParam(name="uuid", value="${api.swagger.param}", required = true)
  )
  public ResponseEntity<Integer> countRounds(@PathVariable(value = "uuid") String uuid) {
    return ResponseEntity.ok(gameService.countRounds(uuid));
  }

  @GetMapping(value = "/{uuid}/restart")
  public ResponseEntity<Void> restart(@PathVariable(value = "uuid") String uuid) {
    gameService.reset(uuid);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
