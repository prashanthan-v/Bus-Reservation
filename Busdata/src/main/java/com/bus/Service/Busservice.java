package com.bus.Service;

import com.bus.Repository.Busrepository;

import com.bus.feign.BookingInterface;
import com.bus.model.Booking;
import com.bus.model.Bus;
import com.bus.model.BusisfullException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Busservice {
    @Autowired
    public Busrepository busrepository;
    @Autowired
    private BookingInterface bookingInterface;
    public List<Bus> showbuses() {
        return busrepository.findAll();
    }


    public String booking(Booking booking) {
      Integer capacity =  busrepository.findCapacity(booking.getBusno());

      Integer booked = bookingInterface.getcount(booking.getBusno());
      if(booked<capacity){
         return bookingInterface.book(booking);
      }
      else throw new BusisfullException("oops!! Your bus is full,Try another bus.");

    }

    public List<Booking> showallbooking() {
        return bookingInterface.showbookings();
    }
}
