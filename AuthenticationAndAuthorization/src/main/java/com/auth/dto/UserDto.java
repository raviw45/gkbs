package com.auth.dto;

import java.util.Set;
import com.auth.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDto {

	private String username;
	private String fullName;
	private String email;
	private String phoneNumber;
	private Set<String> role;
	private Status status;
	private String address;
	private String password;
}
