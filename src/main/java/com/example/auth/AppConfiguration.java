package com.example.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
@Deprecated
public class AppConfiguration {

	//@Bean
	public WebMvcConfigurer corsConfigurer() {
	    return new WebMvcConfigurerAdapter() {
	        @Override
	        public void addCorsMappings(CorsRegistry registry) {
	            registry.addMapping("/**")
	                    .allowedOrigins("http://localhost:4200")
	                    .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD","OPTIONS")
	                    .allowedHeaders("Content-Type", "Date", "Total-Count", "loginInfo","jwt_token")
	                    .exposedHeaders("Content-Type", "Date", "Total-Count", "loginInfo", "jwt_token")
	                    .maxAge(3600);
	        }
	    };
	}
	
}
