package com.ciklum.roshambo.service.impl;

import com.ciklum.roshambo.service.RoshamboStatsService;
import com.ciklum.roshambo.stats.RoshamboStatsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoshamboStatsServiceImpl implements RoshamboStatsService {

  @Autowired
  private RoshamboStatsClient stats;

  @Override
  public void incrementTotalRoundsPlayed() {
    stats.incrementTotalRoundsPlayed();
  }

  @Override
  public void incrementP2Wins() {
    stats.incrementP2Wins();
  }

  @Override
  public void incrementDraws() {
    stats.incrementDraws();
  }

  @Override
  public void incrementP1Wins() {
    stats.incrementP1Wins();
  }

  @Override
  public String getStatistics() {
    return stats.stats();
  }

}
