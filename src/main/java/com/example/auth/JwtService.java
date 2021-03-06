package com.example.auth;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
    public static final int EXPIRATION_TIME_TOKEN = 1000 * 60 * 60; // in ms
	//public static final int EXPIRATION_TIME_TOKEN = 1000 * 60; // in ms
    private static final String AUTHORITIES = "authorities";
    private final String SECRET_KEY;
 
    public JwtService() {
        SECRET_KEY = Base64.getEncoder().encodeToString("key".getBytes());
    }
 
    public String createToken(UserDetails userDetails) {
        String username = userDetails.getUsername();
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return Jwts.builder()
                .setSubject(username)
                .claim(AUTHORITIES, authorities)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_TOKEN))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
 
    public Boolean hasTokenExpired(String token) {
    	
    			boolean result = true;
    	
        try { 
        	    result =  Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
   
        }catch(ExpiredJwtException e) {
        	
        	System.out.println("JwtService warn: Token expired");
        	return true;
        	
        }catch(Exception e) {
        	
        	System.out.println("Error processing the token or token not valid");
        	return true;
        }
        
        return result;
    }
 
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (userDetails.getUsername().equals(username) && !hasTokenExpired(token));
 
    }
 
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
 
    public Collection<? extends GrantedAuthority> getAuthorities(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return (Collection<? extends GrantedAuthority>) claims.get(AUTHORITIES);
    }
}