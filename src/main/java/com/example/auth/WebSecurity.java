package com.example.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	//String pass = passwordEncoder().encode("123");
    	//auth.inMemoryAuthentication()
        //.withUser("user1").password(pass).roles("SENSEI");
    	auth.authenticationProvider(daoAuthenticationProvider());
        //auth.userDetailsService(customUserDetailsService);
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	
    	  http.csrf().disable()
    	  .authorizeRequests()
    	  .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()//allow CORS option calls
    	  .and()
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
         .authorizeRequests().antMatchers("/register").permitAll()
         .and()
         .authorizeRequests().antMatchers("/login").permitAll()
         .and()
         .authorizeRequests().antMatchers("/add-default").permitAll()
         .and()
         .authorizeRequests().antMatchers("/public-action").permitAll()
         .and()
         .authorizeRequests().antMatchers("/all-books").permitAll()
         .and()
         .authorizeRequests().antMatchers("/all-categories").permitAll()
         .and()
         .authorizeRequests().antMatchers("/all-subcategories").permitAll()
         .and()
         .authorizeRequests().antMatchers("/authors-by-substring-name").permitAll()
         .and()
         .authorizeRequests().antMatchers("/books-by-params").permitAll()
         .and()
         //.authorizeRequests().antMatchers("/endpoint").authenticated()
         //.and()
         .authorizeRequests().antMatchers("/prot","/private-action",
        		 "/add-loan/{\\d+}","/add-reservation/{\\d+}","/remove-reservation/{\\d+}").authenticated()
         .and().addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }
 
    @Primary
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Bean
   @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	
        return super.authenticationManagerBean();
    	
    	//return new auth
    }
    
    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return daoAuthenticationProvider;
    }
    
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .authenticationProvider(daoAuthenticationProvider());
    }
}
