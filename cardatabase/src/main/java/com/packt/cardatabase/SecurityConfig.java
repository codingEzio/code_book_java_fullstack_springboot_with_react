package com.packt.cardatabase;

import java.util.Arrays;

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
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private AuthenticationFilter authenticationFilter;

	@Autowired
	private AuthenticationEntryPoint exceptionHandler;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// Disable CSRF protection
				.csrf().disable()
				// Configure CORS (Cross Origin Resource Sharing)
				.cors().and()
				// Allow EVERYONE access the LOGIN via POST
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login")
				.permitAll();

		// // Disable session creation
		// .sessionManagement()
		// .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		// // Required to be authenticated for accessing any other requests
		// .anyRequest().authenticated().and()
		// .exceptionHandling()
		// .authenticationEntryPoint(exceptionHandler).and()
		// // Go find existing JWT token if there is one and make use of it
		// .addFilterBefore(authenticationFilter,
		// UsernamePasswordAuthenticationFilter.class);
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

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();

		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
		corsConfiguration.setAllowedMethods(Arrays.asList("*"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
		corsConfiguration.setAllowCredentials(false);
		corsConfiguration.applyPermitDefaultValues();

		urlBasedCorsConfigurationSource.registerCorsConfiguration(
				"/**",
				corsConfiguration);

		return urlBasedCorsConfigurationSource;
	}
}