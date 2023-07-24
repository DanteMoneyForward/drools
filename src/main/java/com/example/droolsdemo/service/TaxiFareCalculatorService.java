package com.example.droolsdemo.service;

import com.example.droolsdemo.config.DebugAgendaEventListener;
import com.example.droolsdemo.entity.Fare;
import com.example.droolsdemo.entity.TaxiRide;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaxiFareCalculatorService {

  @Autowired
  private KieContainer kieContainer;

  public Long calculateFare(TaxiRide taxiRide, Fare rideFare) {
    KieSession kieSession = kieContainer.newKieSession();
    kieSession.addEventListener(new DebugAgendaEventListener());
    kieSession.setGlobal("rideFare", rideFare);
    kieSession.insert(taxiRide);
    kieSession.fireAllRules();
    kieSession.dispose();
    System.out.println("fare: " + rideFare.getTotalFare());
    return rideFare.getTotalFare();
  }
}