package com.bus.Service;

import com.bus.Repository.Bookingrepository;
import com.bus.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingService {
    @Autowired
    private Bookingrepository bookingrepository;
    public List<Booking> showbookings() {
       return bookingrepository.findAll();
    }

    public Integer getcount(Integer busno) {
        return bookingrepository.getcount(busno);
    }

    public String book(Booking booking) {
       return bookingrepository.save(booking)+"your bus is successfully booked,Thank You";
    }
}
