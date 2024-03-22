package com.bus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Booking {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingno;
    private String name;
    private Integer busno;
    private String date;
}
