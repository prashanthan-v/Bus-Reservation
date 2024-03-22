package com.bus.Filter;
import com.bus.Service2.Detailservice;
import com.bus.Service2.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class Jwtfilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private Detailservice userdetailservice;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull  HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
   //here Header refers to the first part of jwt
      String authheader = request.getHeader("Authorization");
      if(authheader==null||!authheader.startsWith("Bearer ")){
          // shift to other filter chain
         filterChain.doFilter(request,response);
         return;
      }
     String token = authheader.substring(7);
      String username = jwtService.extractUsername(token);
      // checkin if the username is not null and it is
        // not authenticated yet so authentication has to be null
      if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
        UserDetails userDetails= userdetailservice.loadUserByUsername(username);
       // preferably store different names because same name people cant able to login
          if(jwtService.isvalid(userDetails,token)){
              // if its valid give the user ,authentication
              UsernamePasswordAuthenticationToken authenticationToken =
                      new UsernamePasswordAuthenticationToken(userDetails,null,
                              userDetails.getAuthorities());

              authenticationToken.setDetails
                      (new WebAuthenticationDetailsSource().buildDetails(request));
              SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          }

      }
      filterChain.doFilter(request,response);

    }
}
