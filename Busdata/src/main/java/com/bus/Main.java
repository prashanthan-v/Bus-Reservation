package com.bus;

import com.bus.Repository.Busrepository;
import com.bus.model.Bus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
//3924-06-14 00:00:00.000000
import java.util.Date;
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients

public class Main {
    public static void main(String[] args) {

        SpringApplication.run(Main.class,args);
    }
    @Bean
    public CommandLineRunner loadData(Busrepository busrepository){
     return args -> {

      Bus bus1 = new Bus();
      bus1.setDate(new Date(2024,5,13));
      bus1.setDeparture("chennai");
      bus1.setArrival("bangalore");
      bus1.setCapacity(3);
     busrepository.save(bus1);

         Bus bus2 = new Bus();
         bus2.setDate(new Date(2024,5,14));
         bus2.setDeparture("salem");
         bus2.setArrival("coimbatore");
         bus2.setCapacity(55);
         busrepository.save(bus2);

         Bus bus3 = new Bus();
         bus3.setDate(new Date(2024,5,15));
         bus3.setDeparture("villupuram");
         bus3.setArrival("erode");
         bus3.setCapacity(60);
         busrepository.save(bus3);



     };

    }
}