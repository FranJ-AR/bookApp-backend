package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Constants;
import com.example.demo.ErrorMessages;
import com.example.exceptions.CustomUserPasswordSizeException;
import com.example.services.CustomValidatorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
 
	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
    private CustomUserDetailsService customUserDetailsService;
	@Autowired
    private JwtService jwtService;
	@Autowired
	private CustomValidatorService customValidatorService;
 
	@CrossOrigin(origins = Constants.HOST_URL)
    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody(required=false) AuthenticationRequest authenticationRequest) throws Exception {
		
		if(authenticationRequest == null) {
			
			return new ResponseEntity<ErrorMessages>(ErrorMessages.PARAMETERS_REQUIRED, HttpStatus.BAD_REQUEST);
			
		}
		
        try {
        	
        	customValidatorService.isValidSizeUserPassword(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            authenticationManager.authenticate(authentication);
            
        }catch(CustomUserPasswordSizeException e1) {
        	
        	return new ResponseEntity<ErrorMessages>(ErrorMessages.SIZE_USERNAME_PASSWORD,HttpStatus.BAD_REQUEST);
        	
        
        } catch (UsernameNotFoundException e2) {
        	
        	return new ResponseEntity<ErrorMessages>(ErrorMessages.USERNAME_NOT_FOUND, HttpStatus.BAD_REQUEST);
        
        } catch (BadCredentialsException e3) {
            //throw new Exception("Invalid username or password", e);
        	
        	return new ResponseEntity<ErrorMessages>(ErrorMessages.INCORRECT_PASSWORD, HttpStatus.BAD_REQUEST);
        
        } catch (Exception e4){
        	
        	return new ResponseEntity<ErrorMessages>(ErrorMessages.SYSTEM_ERROR, HttpStatus.BAD_REQUEST);

        	
        }
        
        
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtService.createToken(userDetails);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(token);
        return new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);
    }
}
