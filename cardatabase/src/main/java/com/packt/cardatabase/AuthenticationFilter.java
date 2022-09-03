package com.packt.cardatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.packt.cardatabase.service.JwtService;


@Component
public class AuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest,
	                                HttpServletResponse httpServletResponse,
	                                FilterChain filterChain) throws java.io.IOException, ServletException {

		String jwts = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
		
		if (jwts != null) {
			String user = jwtService.getAuthenticatedUser(httpServletRequest);

			Authentication authentication = new UsernamePasswordAuthenticationToken(
					user,
					null,
					java.util.Collections.emptyList()
			);

			SecurityContextHolder
					.getContext()
					.setAuthentication(authentication);
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}