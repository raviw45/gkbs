package com.auth.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.config.CustomUserDetails;
import com.auth.dto.AuthRequest;
import com.auth.dto.AuthResponse;
import com.auth.dto.JwtResponseDto;
import com.auth.dto.RefreshTokenRequestDto;
import com.auth.dto.SignUpResponse;
import com.auth.dto.UserDto;
import com.auth.entities.RefreshToken;
import com.auth.exceptions.ResourceNotFoundException;
import com.auth.services.RefreshTokenService;
import com.auth.services.UserAuthService;
import com.auth.services.impl.JwtService;
@RestController
@RequestMapping("/api/auth/")
public class UserAuthController {
	
	@Autowired
	private UserAuthService userAuthService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JwtService jwtservice;

	@PostMapping("/signUp")
	public ResponseEntity<SignUpResponse> signUp(@RequestBody UserDto user){
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		
			UserDto userDto =this.userAuthService.signUp(user);
			
			if(userDto!=null) {		
				SignUpResponse signUpResponse=SignUpResponse.builder()
				               .message("User Signed Up Successfully!!")
				               .success(true)
				               .data(userDto)
				               .build();
				return ResponseEntity.status(HttpStatus.CREATED).body(signUpResponse);
			}else {
				throw new Exception("Something went wrong!!");
			}
		} catch (Exception e) {
			System.out.println("Error =>"+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					 SignUpResponse.builder()
					               .message("Something Went Wrong!!!")
					               .success(false)
					               .data(null)
					               .build()
					);
		}
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthResponse> signIn(@RequestBody AuthRequest authRequest){
	      try {
              // Authentication manager to authenticate user
	    	  Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	    	  
	    	  if(authentication.isAuthenticated()) {
	    		      RefreshToken refreshToken =this.modelMapper.map(refreshTokenService.createRefreshToken(authRequest.getUsername()),RefreshToken.class);
	    		      
	    		      CustomUserDetails  customUserDetails= (CustomUserDetails)authentication.getPrincipal();
	    		      List<String> roles=customUserDetails.getAuthorities()
	    		                       .stream()
	    		                       .map(item->item.getAuthority())
	    		                       .collect(Collectors.toList());
	    		      
	    		      String token=jwtservice.generateToken(authRequest.getUsername(),roles);
	    		      
	 	    		 return ResponseEntity.status(HttpStatus.OK).body(
	 	    				 AuthResponse.builder()
	 	    				              .accessToken(token)
	 	    				              .refreshToken(refreshToken.getToken())
	 	    				              .message("LoggedIn successfully!!")
	 	    				              .roles(roles)
	 	    				              .success(true)
	 	    				              .status(HttpStatus.OK)
	 	    				              .build()
	 	    				 );
	    	  }else {
	    		   throw new ResourceNotFoundException("Invalid Credentials!!");
	    	  }
		} catch (Exception e) {
			System.out.println("Exception=>"+e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					 AuthResponse.builder()
					              .accessToken(null)
					              .refreshToken(null)
					              .message(e.getMessage())
					              .status(HttpStatus.INTERNAL_SERVER_ERROR)
					              .success(false)
					             .build()
					);
		}
	}
	
	@PostMapping("/refreshToken")
	public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto){
		 try {
			return  refreshTokenService.findByToken(refreshTokenRequestDto.getToken())
					.map(refreshTokenService::verifyExpiration)
					.map(RefreshToken::getUser)
					.map(user->{
						// Convert Set<Role> to List<String>
						List<String> rolesList = user.getRole().stream()
                                .map(role->role.getName().toString())
                                .collect(Collectors.toList());
						System.out.println(rolesList);
						String accessToken=jwtservice.generateToken(user.getUsername(),rolesList);
						JwtResponseDto jwtresponse=JwtResponseDto.builder()
								.accessToken(accessToken)
								.token(refreshTokenRequestDto.getToken())
								.build();
						return ResponseEntity.ok(jwtresponse);
					}).orElseThrow(()->new RuntimeException("Refresh token is not in DB..."));
		} catch (Exception e) {
		   System.out.println("Error=>"+e.getMessage());
		   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				                                   JwtResponseDto.builder()
														   .accessToken(null)
														   .token(null)
														   .build()
	         );
		}
	}
	
	@GetMapping("/getMessage")
	public String getMessage() {
		return "Hello from the api call";
	}
}


