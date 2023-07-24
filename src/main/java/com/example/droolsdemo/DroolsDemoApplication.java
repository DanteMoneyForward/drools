package com.example.droolsdemo;

import com.example.droolsdemo.entity.Fare;
import com.example.droolsdemo.entity.TaxiRide;
import com.example.droolsdemo.service.TaxiFareCalculatorService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DroolsDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DroolsDemoApplication.class, args);
  }

  @Bean
  ApplicationRunner test(TaxiFareCalculatorService taxiFareCalculatorService) {
    TaxiRide taxiRide = new TaxiRide();
    taxiRide.setIsNightSurcharge(true);
    taxiRide.setDistanceInMile(190L);
    Fare rideFare = new Fare();
    return args -> taxiFareCalculatorService.calculateFare(taxiRide, rideFare);
  }


}
