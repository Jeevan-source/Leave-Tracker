package com.example.demo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private long expirationTime;

	private SecretKey getSigningKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
	}

	
	public String generateToken(Authentication authentication) {
		String username = authentication.getName();

		
		List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

		
		Map<String, Object> claims = new HashMap<>();
		claims.put("roles", roles);

		return Jwts.builder().claims(claims)
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + expirationTime))
				.signWith(getSigningKey())
				.compact();
	}

	
	public Claims extractClaims(String token) {
		return Jwts.parser() 
				.verifyWith(getSigningKey())
				.build().parseSignedClaims(token) 
				.getPayload(); 
	}

	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}

	
	public boolean validateToken(String token) {
		return !isTokenExpired(token);
	}

	
	private boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date(System.currentTimeMillis()));
	}

}
