package com.example.droolsdemo;

import static org.junit.Assert.assertEquals;

import com.example.droolsdemo.config.DebugAgendaEventListener;
import com.example.droolsdemo.config.DroolsConfiguration;
import com.example.droolsdemo.entity.CustomerBean;
import com.example.droolsdemo.entity.ErrorMessage;
import com.example.droolsdemo.entity.SendingInfomation;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.io.Resource;
import org.kie.api.runtime.Globals;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.AgendaFilter;
import org.kie.internal.io.ResourceFactory;

public class CustomerRuleTest {
  private KieSession kieSession;

  @Before
  public void setup() {
    Resource resource = ResourceFactory.newClassPathResource("rules/CUSTOMER_LOGIC.drl", getClass());
    kieSession = new DroolsConfiguration().getKieSession(resource);
    kieSession.addEventListener(new DebugAgendaEventListener());
  }

  @Test
  public void enableUsingMailingAgent_success() {
    CustomerBean customerBean = new CustomerBean();
    customerBean.setName("Dante");
    customerBean.setZipCode("10000");
    customerBean.setAddress1("dante");

    SendingInfomation sendingInfomation = new SendingInfomation();
    sendingInfomation.setSenderInfo("senderInfo");

    ErrorMessage errorMessage = new ErrorMessage();
    kieSession.setGlobal("Errmessage" , errorMessage);
    kieSession.insert(customerBean);
    kieSession.insert(sendingInfomation);
    kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals(true, customerBean.isUsingMailingAgent());

  }

  @Test
  public void enableUsingMailingAgent_fail() {
    CustomerBean customerBean = new CustomerBean();
    customerBean.setName("Dante");
    customerBean.setZipCode("10000");
    customerBean.setAddress1("dante 1");
    SendingInfomation sendingInfomation = new SendingInfomation();
    ErrorMessage errorMessage = new ErrorMessage();

    kieSession.setGlobal("Errmessage" , errorMessage);
    kieSession.insert(customerBean);
    kieSession.insert(sendingInfomation);
    kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals("No sending information", errorMessage.getErrorString());

  }

  @Test
  public void enableUsingMailingAgent_notExistSendingInfor_fail() {
    CustomerBean customerBean = new CustomerBean();
    customerBean.setName("Dante");
    customerBean.setZipCode("10000");
    customerBean.setAddress1("dante 1");
    ErrorMessage errorMessage = new ErrorMessage();

    kieSession.setGlobal("Errmessage" , errorMessage);
    kieSession.insert(customerBean);
    kieSession.fireAllRules();
    kieSession.dispose();

    assertEquals("Not exists sending information", errorMessage.getErrorString());

  }

}
