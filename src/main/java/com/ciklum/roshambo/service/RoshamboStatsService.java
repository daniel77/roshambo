package com.ciklum.roshambo.service;

import com.ciklum.roshambo.stats.RoshamboStatsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoshamboStatsService {

  @Autowired
  private RoshamboStatsClient stats;

  public void incrementTotalRoundsPlayed() {
    stats.incrementTotalRoundsPlayed();
  }

  public void incrementP2Wins() {
    stats.incrementP2Wins();
  }

  public void incrementDraws() {
    stats.incrementDraws();
  }

  public void incrementP1Wins() {
    stats.incrementP1Wins();
  }

  public String getStatistics() {
    return stats.stats();
  }

}
