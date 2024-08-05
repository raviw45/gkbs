package com.service_provider.config;


import java.security.Key;
import java.util.List;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    public static final String SECRET = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    public Claims extractAllClaims(String token) {
    	return Jwts.parserBuilder()
    			.setSigningKey(getSignInKey())
    			.build()
    			.parseClaimsJws(token)
    			.getBody();
    }
    
    public Key getSignInKey() {
    	byte[] keyBytes=Decoders.BASE64.decode(SECRET);
    	return Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String extractUsername(String token) {
    	return extractAllClaims(token).getSubject();
    }
    
    @SuppressWarnings("unchecked")
	public List<String> extractRoles(String token){
    	return (List<String>) extractAllClaims(token).get("role");
    }
    
    public boolean validateToken(String token) {
    	try {
			extractAllClaims(token);
			return true;
		} catch (Exception e) {
			return false;
		}
    }
}
