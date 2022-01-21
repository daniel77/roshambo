package com.ciklum.roshambo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${api.roshambostats.name}", url = "${api.roshambostats.url}")
public interface StatsClient {
// TODO MOVE TO CLIENT SERVICE
  @RequestMapping(method = RequestMethod.GET, value = "incrementtotal")
  void incrementTotalRoundsPlayed();

  @RequestMapping(method = RequestMethod.GET, value = "incrementp2")
  void incrementP2Wins();

  @RequestMapping(method = RequestMethod.GET, value = "incrementdraw")
  void incrementDraws();

  @RequestMapping(method = RequestMethod.GET, value = "incrementp1")
  void incrementP1Wins();

  @RequestMapping(method = RequestMethod.GET, value = "stats")
  String stats();
}
