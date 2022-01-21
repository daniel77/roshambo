package com.ciklum.roshambo.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Component
@Scope(value = SCOPE_SINGLETON)
@Getter
@ToString
public class Stats {
  AtomicInteger totalRoundsPlayed = new AtomicInteger();
  AtomicInteger totalWinsP1 = new AtomicInteger();
  AtomicInteger totalWinsP2 = new AtomicInteger();
  AtomicInteger totalDraws = new AtomicInteger();
}
