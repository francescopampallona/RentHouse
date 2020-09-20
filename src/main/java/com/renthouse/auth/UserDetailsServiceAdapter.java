package com.renthouse.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.renthouse.dao.UserRepository;
import com.renthouse.model.User;

@Service
public class UserDetailsServiceAdapter implements UserDetailsService{
	
	private final UserRepository userRepository;
	 
	public UserDetailsServiceAdapter(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return new UserDetailsAdapter(user);
	}
}
