package com.bus.Service2;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {
    private static  final  String secret= "40158def11995e5908732080f23986c9590417b3550884cf187425ef37aca423";
    public String genereatetoken(String username ) {

        Map<String, Object> claims = new HashMap<>();
        return createtoken(claims,username);

    }
// claims are unit of token or payload ex:oursubject(ie:username)is the claim,
// expiration time is the claim simply what are all the
// things you need to create an token is called as claim,collection of them called as claims
    private String createtoken(Map<String, Object> claims, String username) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getsignkey(), SignatureAlgorithm.HS256).compact();

    }

    private Key getsignkey() {
        byte[]keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
 //we use function(interface) as the second argument because it accept one kind of object
 // and return different kind of object
 // ex:it accepts claims, but it's returning claims.getsubject
    // actual representation of function is anonymous class
    //this function interface has the function called apply which is accepting claims
    // as a argument and returning username or expiration as we provided
 // new Function<Claims, String>() {
 //    @Override
 //    public String apply(Claims claims) {
 //        return claims.getSubject();
 //    }
 //}

    // we can also implement using lambda
    // claims -> claims.getSubject()
    public<T>T extractclaims(String token, Function<Claims,T> claimsresolver){
        final Claims claims = extractAllclaims(token);
        return claimsresolver.apply(claims);
    }
    private Claims extractAllclaims(String token){
        return Jwts
                .parserBuilder()          //parsing means separating each claim from the token,
                                        // so we need to build it ,for that we need the secret key
                .setSigningKey(getsignkey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
    public boolean isvalid(UserDetails user, String token){
        String username= extractclaims(token,Claims::getSubject);
        return username.equals(user.getUsername())&& !istokenexpired(token);
    }

    private boolean istokenexpired(String token) {
        return extractclaims(token,Claims::getExpiration).before(new Date());
    }
    public String extractUsername(String token) {

        return  extractclaims(token,Claims::getSubject);
    }

    // one way of implementing the Function Interface using anonymous class(no nAME Class)
//public String extractUsername(String token) {
//
//    return  extractclaims(token, new Function<Claims, String>() {
//        @Override
//        public String apply(Claims claims) {
//            return claims.getSubject();
//        }
//    });
//}
    // another way of implementing the Function Interface using Lambda expression
//    public String extractUsername(String token) {
//       // below claims is the reference variable of Claims(inbuilt)
//        return  extractclaims(token,claims->claims.getSubject());
//    }






}
