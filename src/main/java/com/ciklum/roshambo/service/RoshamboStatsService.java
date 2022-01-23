package com.ciklum.roshambo.service;

/**
 * Service to use Roshambo Stats microservice
 */
public interface RoshamboStatsService {
  void incrementTotalRoundsPlayed();

  void incrementP2Wins();

  void incrementDraws();

  void incrementP1Wins();

  String getStatistics();
}
