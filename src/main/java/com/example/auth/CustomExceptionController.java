package com.example.auth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@Deprecated
public class CustomExceptionController {

	    //sample handler
	    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(UsernameNotFoundException.class)
	    public @ResponseBody Integer handleException(HttpServletRequest request,
	            Exception ex){
	        return 245;
	    }
	    //other handlers
	}

