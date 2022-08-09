package com.packt.cardatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.packt.cardatabase.domain.User;
import com.packt.cardatabase.domain.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// Fetch the user from database
		User currentUser = userRepository.findByUsername(username);

		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				username,
				currentUser.getPassword(),
				true,
				true,
				true,
				true,
				AuthorityUtils.createAuthorityList(currentUser.getRole()));

		return userDetails;
	}
}