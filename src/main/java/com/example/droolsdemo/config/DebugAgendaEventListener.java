package com.example.droolsdemo.config;

import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.DefaultAgendaEventListener;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugAgendaEventListener extends DefaultAgendaEventListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(DebugAgendaEventListener.class);

  @Override
  public void afterMatchFired(AfterMatchFiredEvent event) {
    Rule rule = event.getMatch().getRule();
    LOGGER.info("(afterMatchFired) Rule fired: " + rule.getName());
  }

  @Override
  public void matchCreated(MatchCreatedEvent event) {
    Rule rule = event.getMatch().getRule();
    LOGGER.info("(matchCreated) Rule fired: " + rule.getName());
  }

  @Override
  public void beforeMatchFired(BeforeMatchFiredEvent event) {
    Rule rule = event.getMatch().getRule();
    LOGGER.info("(beforeMatchFired) Rule fired: " + rule.getName());
  }
}
