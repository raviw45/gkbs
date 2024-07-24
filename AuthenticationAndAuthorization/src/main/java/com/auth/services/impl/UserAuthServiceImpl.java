package com.auth.services.impl;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.dto.UserDto;
import com.auth.entities.ERole;
import com.auth.entities.Role;
import com.auth.entities.User;
import com.auth.repository.RoleRepository;
import com.auth.repository.UserRepository;
import com.auth.services.UserAuthService;

@Service
public class UserAuthServiceImpl implements UserAuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDto signUp(UserDto user) {
		User userDataToSave=this.modelMapper.map(user, User.class);
		
		Set<String> strRoles=user.getRole();
		Set<Role> roles=new HashSet<>();
		
		if(strRoles==null) {
			Role userRole=this.roleRepository.findByName(ERole.ROLE_USER).orElseThrow(()->new RuntimeException("Error:Role is not found!!"));
		    roles.add(userRole);
		}else {
			 strRoles.forEach(role->{
				switch(role) {
				case "admin":
				  Role adminRole=this.roleRepository.findByName(ERole.ROLE_ADMINISTRATOR)
					      .orElseThrow(()->new RuntimeException("Error:Role is not found!!"));
				  roles.add(adminRole);
				  break;
				case "technician":
					Role technicianRole=this.roleRepository.findByName(ERole.ROLE_TECHNICIAN)
					       .orElseThrow(()->new RuntimeException("Error: Role is not found!!"));
					roles.add(technicianRole);
					break;
				  default:
					  Role userRole=this.roleRepository.findByName(ERole.ROLE_USER).orElseThrow(()->new RuntimeException("Error:Role is not found!!"));
					    roles.add(userRole);
				}
			 });
		}
		
		userDataToSave.setRole(roles);
		User savedUser=this.userRepository.save(userDataToSave);
		return this.modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto findUserByUsername(String username) {
		User user=this.userRepository.findByUsername(username).get();
		return this.modelMapper.map(user, UserDto.class);
	}
    
	
}
