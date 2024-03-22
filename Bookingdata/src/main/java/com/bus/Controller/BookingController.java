package com.bus.Controller;

import com.bus.Service.BookingService;
import com.bus.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @GetMapping
    public List<Booking>showbookings(){
        return bookingService.showbookings();
    }

    @GetMapping("getcount/{busno}")
    public Integer getcount(@PathVariable Integer busno){
        return bookingService.getcount(busno);
    }

    @PostMapping
    public String book(@RequestBody Booking booking){
        return bookingService.book(booking);
    }

}
