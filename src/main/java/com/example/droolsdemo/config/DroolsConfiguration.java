package com.example.droolsdemo.config;

import java.util.Arrays;
import java.util.List;
import org.drools.decisiontable.DecisionTableProviderImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

public class DroolsConfiguration {

  private KieServices kieServices = KieServices.Factory.get();

  private KieFileSystem getKieFileSystem() {
    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    List<String> rules = Arrays.asList("rules/CUSTOMER_LOGIC.drl", "rules/TAXI_FARE_RULE.drl", "rules/Discount.drl.xls");
    for (String rule : rules) {
      kieFileSystem.write(ResourceFactory.newClassPathResource(rule));
    }
    return kieFileSystem;
  }

  public KieContainer getKieContainer() {
    getKieRepository();

    KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
    kb.buildAll();

    KieModule kieModule = kb.getKieModule();
    return kieServices.newKieContainer(kieModule.getReleaseId());
  }

  private void getKieRepository() {
    final KieRepository kieRepository = kieServices.getRepository();
    kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
  }

  public KieSession getKieSession(Resource dt) {
    KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    kieFileSystem.write(dt);
    KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
    kb.buildAll();
    KieRepository kieRepository = kieServices.getRepository();
    ReleaseId krDefaultReleaseId = kieRepository.getDefaultReleaseId();
    KieContainer kieContainer = kieServices.newKieContainer(krDefaultReleaseId);
    return kieContainer.newKieSession();
  }

  /*
   * Can be used for debugging
   * Input excelFile example: rules/Discount.drl.xls
   */
  public String getDrlFromExcel(String excelFile) {
    DecisionTableConfiguration configuration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
    configuration.setInputType(DecisionTableInputType.XLS);

    Resource dt = ResourceFactory.newClassPathResource(excelFile, getClass());

    DecisionTableProviderImpl decisionTableProvider = new DecisionTableProviderImpl();

    return decisionTableProvider.loadFromResource(dt, null);

  }





}
