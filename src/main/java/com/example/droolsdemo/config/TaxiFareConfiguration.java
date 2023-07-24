package com.example.droolsdemo.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaxiFareConfiguration {
  private static final String drlFile = "rules/TAXI_FARE_RULE.drl";

  @Bean
  public KieContainer kieContainer() {
    KieServices kieServices = KieServices.Factory.get();

    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    kieFileSystem.write(ResourceFactory.newClassPathResource(drlFile));
    KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
    kieBuilder.buildAll();
    KieModule kieModule = kieBuilder.getKieModule();

    return kieServices.newKieContainer(kieModule.getReleaseId());
  }

}
