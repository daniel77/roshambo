package com.ciklum.roshambo.controller;

import com.ciklum.roshambo.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsControler
{

  @Autowired
  Stats stats;

  @GetMapping("/stats")
  public ResponseEntity<Stats> start() {
    return ResponseEntity.ok(stats);
  }
}
