package com.service_provider.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
	SecurityFilterChain filter(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
	    .authorizeHttpRequests(auth->{
	    	auth.requestMatchers("/api/service-provider/**").hasRole("ADMINISTRATOR");
	    	auth.anyRequest().authenticated();
	    }).sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	
    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    
	return http.build();
	}
	
	 @Bean
	    AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception {
	    	return config.getAuthenticationManager();
	    }
}
