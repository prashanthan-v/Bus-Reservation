package com.bus.Service2;


import com.bus.Repository.Userrepository;
import com.bus.model2.AuthenticationResponse;
import com.bus.model2.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private Userrepository userrepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;


    public AuthenticationResponse register(User user){
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setRole(user.getRole());
        user1= userrepository.save(user1);
       String token = jwtService.genereatetoken(user1.getUsername());
       return new AuthenticationResponse(token);
    }


    public AuthenticationResponse login(User user){
        authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken
                        (user.getUsername(),user.getPassword()));
        User user1 = userrepository.findByUsername(user.getUsername()).orElseThrow();
        String token = jwtService.genereatetoken(user1.getUsername());
        return new AuthenticationResponse(token);

    }
}
