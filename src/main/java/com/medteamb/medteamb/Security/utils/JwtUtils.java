package com.medteamb.medteamb.Security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import com.medteamb.medteamb.model.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;

@Service
public class JwtUtils {

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(Constants.getJwtKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }
  public Claims extractAllClaims(String token){
      return Jwts.parserBuilder()
              .setSigningKey(getKey())
              .build().parseClaimsJws(token)
              .getBody();
  }

  public String extractUsername(String token){
      return Jwts.parserBuilder().setSigningKey(getKey())
              .build().parseClaimsJws(token).getBody().toString();
  }

    public String getJws(String user, Long userId, List<String> roles) {
        return Jwts.builder().setSubject(user)
                .setExpiration(new Date(System.currentTimeMillis() + Constants.getJwtExpire())).setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date()).setNotBefore(new Date()).addClaims(
                       new HashMap(){
                           private static final long serialVersionUID = 1L;
                           {
                               put("role", roles);
                               put("id", userId);
                           }
                       }).signWith(getKey()).compact();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
       String username = extractUsername(token);
       return username.equals(userDetails.getUsername());
    }

}
