package com.packt.cardatabase.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.packt.cardatabase.domain.AccountCredentials;
import com.packt.cardatabase.service.JwtService;

@RestController
public class LoginController {
	@Autowired
	private JwtService jwtService;

	// With the help of `AuthenticationManager` marked with `@Bean` in SecurityConfig.java
	@Autowired
	AuthenticationManager authenticationManager;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> tryAuthThenEmptyRespWithJwtToken(@RequestBody(required = false) AccountCredentials accountCredentials) {
		UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
				accountCredentials.getUsername(),
				accountCredentials.getPassword());

		Authentication auth = authenticationManager.authenticate(credentials);

		String jwts = jwtService.generateJwtToken(auth.getName());

		return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwts)
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
				.build();
	}
}