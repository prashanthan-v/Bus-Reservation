package com.bus.feign;

import com.bus.model.Booking;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("BOOKING-SERVICE")
public interface BookingInterface {
    @GetMapping("booking")
     List<Booking> showbookings();

    @GetMapping("booking/getcount/{busno}")
    Integer getcount(@PathVariable Integer busno);
    @PostMapping("booking")
    public String book(@RequestBody Booking booking);
}
