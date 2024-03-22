package com.bus.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
//@Table(name = "t_bus")
public class Bus {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer busno;
 private  String departure;
 private String arrival;
 private Date date;
 private Integer capacity;
}
