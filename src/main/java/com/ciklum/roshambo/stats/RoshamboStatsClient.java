package com.ciklum.roshambo.stats;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * REST Client for the Roshambo Stats micro service.
 * See: https://github.com/daniel77/roshambo-stats
 *
 */
@FeignClient(name = "${api.roshambostats.name}", url = "${api.roshambostats.url}")
public interface RoshamboStatsClient {

  @GetMapping("incrementtotal")
  void incrementTotalRoundsPlayed();

  @GetMapping("incrementp2")
  void incrementP2Wins();

  @GetMapping("incrementdraw")
  void incrementDraws();

  @GetMapping("incrementp1")
  void incrementP1Wins();

  @GetMapping(value = "stats")
  String stats();
}
