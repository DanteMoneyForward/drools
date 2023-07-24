package com.example.droolsdemo;

import static org.junit.Assert.assertEquals;

import com.example.droolsdemo.config.DroolsConfiguration;
import com.example.droolsdemo.entity.Customer;
import com.example.droolsdemo.entity.Customer.CustomerType;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

public class DiscountExcelIntegrationTest {
  private KieSession kSession;

  @Before
  public void setup() {
    Resource resource = ResourceFactory.newClassPathResource("rules/Discount.drl.xls", getClass());
    kSession = new DroolsConfiguration().getKieSession(resource);
  }

  @Test
  public void giveIndvidualLongStanding_whenFireRule_thenCorrectDiscount() throws Exception {
    Customer customer = new Customer(CustomerType.INDIVIDUAL, 5);
    kSession.insert(customer);

    kSession.fireAllRules();

    assertEquals(customer.getDiscount(), 15);
  }

  @Test
  public void giveIndvidualRecent_whenFireRule_thenCorrectDiscount() throws Exception {

    Customer customer = new Customer(CustomerType.INDIVIDUAL, 1);
    kSession.insert(customer);

    kSession.fireAllRules();

    assertEquals(customer.getDiscount(), 5);
  }

  @Test
  public void giveBusinessAny_whenFireRule_thenCorrectDiscount() throws Exception {
    Customer customer = new Customer(CustomerType.BUSINESS, 0);
    kSession.insert(customer);

    kSession.fireAllRules();

    assertEquals(customer.getDiscount(), 20);
  }

}
