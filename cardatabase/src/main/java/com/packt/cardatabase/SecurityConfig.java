package com.packt.cardatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;

import com.packt.cardatabase.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// Disable CSRF protection
				.csrf().disable()
				// Disable session creation
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// Allow EVERYONE access the LOGIN via POST
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login")
				.permitAll()
				// Any other requests are allowed by authenticated users
				.anyRequest().authenticated();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
				.userDetailsService(userDetailsServiceImpl)
				.passwordEncoder(new BCryptPasswordEncoder());
	}

	// Annotating it as Bean (mark it as 'could be used') for LoginController to use
	@Bean
	public AuthenticationManager getAuthenticationManger() throws Exception {
		return authenticationManager();
	}
}