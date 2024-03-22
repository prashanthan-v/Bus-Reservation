package com.bus.Config;


import com.bus.Filter.Jwtfilter;
import com.bus.Service2.Detailservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class JwtConfig {
    @Autowired
    private Jwtfilter jwtfilter;
    @Autowired
    private Detailservice userdetailsserviceimp;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return   http
                   .csrf(AbstractHttpConfigurer::disable)
                   .authorizeHttpRequests(
                           req->req.requestMatchers("/bus/login","/bus/register")
                          .permitAll()
                                   .requestMatchers("/bus/showbookings").hasAuthority("ADMIN")
                          .anyRequest().authenticated()
                   )//for other end points we need userdetailservice
              // and jwtfilter to handle the request so we add this both in our config class
                  .userDetailsService(userdetailsserviceimp)
                   .sessionManagement(session->session.
                           sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter.class)
              .build();
    }
    @Bean
    //to create a encrypted password
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
   @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration configuration) throws Exception {
       return configuration.getAuthenticationManager();

    }

}
