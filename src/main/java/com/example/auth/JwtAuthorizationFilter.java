package com.example.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.ErrorMessages;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
 
    private static final String HEADER_TOKEN_PREFIX = "Bearer";
    private static final String HEADER_AUTHORIZATION = "Authorization";
 
    @Autowired
    private CustomUserDetailsService CustomUserDetailsService;
 
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private CustomExceptionController customExceptionController;
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        if (authorizationHeader != null && 
        		
        		authorizationHeader.startsWith(HEADER_TOKEN_PREFIX )) {
        	
            String token = authorizationHeader.replace(HEADER_TOKEN_PREFIX, "");
            
            if( jwtService != null && jwtService.hasTokenExpired(token)) {
            	
            	System.out.println("JwtAuthorization warn: Token expired or not valid");
            	filterChain.doFilter(request, response);
            	return;
            	
            }
            String username = jwtService.extractUsername(token);
            
            UserDetails userDetails;
            
            try {
 
            userDetails = CustomUserDetailsService.loadUserByUsername(username);
            
            }catch(UsernameNotFoundException exception) {
            	
            	//sendFilterErrorResponse(response, 403, ErrorMessages.INVALID_OR_EXPIRED_TOKEN_OR_USER_NOT_FOUND);

            	System.out.println("User not found with that token");
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
    
    @Deprecated 
    // Better not to inform the user whether the token is expired, not valid or it is not associated to a current user
    private void sendFilterErrorResponse(HttpServletResponse response, int errorCode, ErrorMessages errorMessages) throws IOException {
    	
    	response.setStatus(errorCode);
        response.setContentType("application/json");

        //pass down the actual obj that exception handler normally send
        ObjectMapper mapper = new ObjectMapper();
        PrintWriter out = response.getWriter(); 
        out.print(mapper.writeValueAsString(errorMessages));
        out.flush();
    	
    
    }

}