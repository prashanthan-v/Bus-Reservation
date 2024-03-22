package com.bus;

import com.bus.Repository.Bookingrepository;
import com.bus.model.Booking;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableDiscoveryClient
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(Main.class,args);
    }
    @Bean
    public CommandLineRunner loadData(Bookingrepository bookingrepository) {
        return args -> {

          Booking booking1 = new Booking();
          booking1.setName("prasanth");
          booking1.setDate("2024/4/24");
          booking1.setBusno(1);
          bookingrepository.save(booking1);

            Booking booking2 = new Booking();
            booking2.setName("priya");
            booking2.setDate("2024/5/12");
            booking2.setBusno(1);
            bookingrepository.save(booking2);



        };
    }
}