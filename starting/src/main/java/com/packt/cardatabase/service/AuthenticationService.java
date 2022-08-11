package com.packt.cardatabase.service;

import java.util.Date;
import static java.util.Collections.emptyList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthenticationService {
	static final long EXPIRATION_TIME = 864_000_00;
	static final String SIGNING_KEY = "secret-key";
	static final String PREFIX = "Bearer";

	static public void addToken(HttpServletResponse httpServletResponse, String username) {
		String jwtToken = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SIGNING_KEY)
				.compact();

		httpServletResponse.addHeader("Authorization", PREFIX + " " + jwtToken);
		httpServletResponse.addHeader("Access-Control-Expose-Headers", "Authorization");
	}

	static public Authentication getAuthentication(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization");

		if (token != null) {
			String user = Jwts.parser()
					.setSigningKey(SIGNING_KEY)
					.parseClaimsJws(token.replace(PREFIX, ""))
					.getBody()
					.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(
						user,
						null,
						emptyList());
			}
		}

		return null;
	}
}