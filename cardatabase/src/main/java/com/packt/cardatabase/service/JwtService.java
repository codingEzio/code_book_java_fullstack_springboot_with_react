package com.packt.cardatabase.service;

import java.util.Date;
import java.security.Key;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtService {
	static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // ms
	static final String PREFIX = "Bearer";

	static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	public String generateJwtToken(String username) {
		String jwtToken = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(secretKey)
				.compact();

		return jwtToken;
	}

	public String getAuthenticatedUser(HttpServletRequest httpServletRequest) {
		String jwtToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

		if (jwtToken != null) {
			String user = Jwts.parserBuilder()
					.setSigningKey(secretKey)
					.build()
					.parseClaimsJws(jwtToken.replace(PREFIX, ""))
					.getBody()
					.getSubject();

			if (user != null) {
				return user;
			}
		}

		return null;
	}
}