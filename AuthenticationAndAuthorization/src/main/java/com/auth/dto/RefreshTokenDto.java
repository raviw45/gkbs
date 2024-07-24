package com.auth.dto;

import java.time.Instant;

import com.auth.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDto {

	private String token;
	private Instant expiryDate;
	private User user;
}
