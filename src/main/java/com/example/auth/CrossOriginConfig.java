package com.example.auth;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Deprecated
//@Configuration
public class CrossOriginConfig {

    //@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
                        
                
            }
        };
    }

}
