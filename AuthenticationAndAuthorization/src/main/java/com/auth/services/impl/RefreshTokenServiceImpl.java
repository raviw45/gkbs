package com.auth.services.impl;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.auth.dto.RefreshTokenDto;
import com.auth.entities.RefreshToken;
import com.auth.repository.RefreshTokenRepository;
import com.auth.repository.UserRepository;
import com.auth.services.RefreshTokenService;
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public RefreshTokenDto  refreshToken(String token) {
		    RefreshToken refreshToken=this.refreshTokenRepository.findByToken(token).get();
		return this.modelMapper.map(refreshToken,RefreshTokenDto.class);
	}


	@Override
	public RefreshTokenDto createRefreshToken(String username) {
		RefreshToken refreshToken=RefreshToken.builder()
		            .user(this.userRepository.findByUsername(username).get())
		            .token(UUID.randomUUID().toString())
		            .expiryDate(Instant.now().plusMillis(604800000))
		            .build();
		// 7 days --> refresh token expire duration
		return this.modelMapper.map(this.refreshTokenRepository.save(refreshToken), RefreshTokenDto.class);
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
		if(token.getExpiryDate().compareTo(Instant.now())<0) {
			refreshTokenRepository.delete(token);
			throw new RuntimeException(token.getToken()+" Refresh token is expired. Please make a new login...");
		}
		return token;
	}


	@Override
	public Optional<RefreshToken> findByToken(String token) {
		 return this.refreshTokenRepository.findByToken(token);
	}
}
