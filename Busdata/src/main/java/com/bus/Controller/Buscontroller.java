package com.bus.Controller;

import com.bus.Filter.Jwtfilter;
import com.bus.Service.Busservice;
import com.bus.Service2.AuthenticationService;
import com.bus.Service2.Detailservice;
import com.bus.Service2.JwtService;
import com.bus.model.Booking;
import com.bus.model.Bus;
import com.bus.model2.AuthenticationResponse;
import com.bus.model2.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bus")
public class Buscontroller {
    @Autowired
    private Busservice busservice;
    @Autowired
    public JwtService jwtService;
    @Autowired
    public Jwtfilter jwtfilter;
    @Autowired
    public Detailservice detailservice;
    @Autowired
    public AuthenticationService authenticationService;
 @GetMapping()
  public List<Bus>showbuses(){
     return busservice.showbuses();
    }
     @PostMapping
    public String booking(@RequestBody Booking booking){
         System.out.println(booking);
     return busservice.booking(booking);
    }
    @GetMapping("showbookings")
    public List<Booking>showallbooking(){
     return busservice.showallbooking();
    }

    // and also get token after registering
    @PostMapping ("/register")
    public AuthenticationResponse register(@RequestBody User user){
        System.out.println(user);
        return authenticationService.register(user);
    }
//for the person whose name is already stored
// in the database but due to token expiry he need to generate the token again
// so he need to log in

    @GetMapping("/login")
    public AuthenticationResponse login(@RequestBody User user){
        System.out.println(user);
        return authenticationService.login(user);
    }


}
