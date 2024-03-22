package com.bus.Service2;


import com.bus.Repository.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Detailservice implements UserDetailsService {

    @Autowired
    private Userrepository userrepository;
    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userrepository.findByUsername(username).orElseThrow
                (()->new UsernameNotFoundException("oops!! user not found"));
    }
}
