package com.auth.services;

import com.auth.dto.UserDto;

public interface UserAuthService {

     //Sign up
	UserDto signUp(UserDto user);
	
     //Sign in
	UserDto findUserByUsername(String username);
}
