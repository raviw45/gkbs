package com.auth.services;

import java.util.Optional;

import com.auth.dto.RefreshTokenDto;
import com.auth.entities.RefreshToken;

public interface RefreshTokenService {

	RefreshTokenDto refreshToken(String token);
	RefreshTokenDto createRefreshToken(String username);
	Optional<RefreshToken> findByToken(String token);
	RefreshToken verifyExpiration(RefreshToken token);
}
