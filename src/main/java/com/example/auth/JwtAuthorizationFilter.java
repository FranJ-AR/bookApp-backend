package com.example.auth;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
 
    private static final String HEADER_TOKEN_PREFIX = "Bearer";
    private static final String HEADER_AUTHORIZATION = "Authorization";
 
    @Autowired
    private CustomUserDetailsService CustomUserDetailsService;
 
    @Autowired
    private JwtService jwtService;
     
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        if (authorizationHeader != null && 
        		
        		authorizationHeader.startsWith(HEADER_TOKEN_PREFIX )) {
        	
            String token = authorizationHeader.replace(HEADER_TOKEN_PREFIX, "");
            
            if( jwtService != null && jwtService.hasTokenExpired(token)) {
            	
            	//JwtAuthorization warn: Token expired or not valid
            	filterChain.doFilter(request, response);
            	return;
            	
            }
            String username = jwtService.extractUsername(token);
            
            UserDetails userDetails;
            
            try {
 
            userDetails = CustomUserDetailsService.loadUserByUsername(username);
            
            }catch(UsernameNotFoundException exception) {
            	
            	//User not found with that token
            	filterChain.doFilter(request, response);
                return; 
            }
            
        
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

}