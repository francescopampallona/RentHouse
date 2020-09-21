package com.renthouse.service;

import com.renthouse.dao.UserRepository;
import com.renthouse.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User insert(User user) {
		String passwordEnconded = encoder.encode(user.getPassword());
		user.setPassword(passwordEnconded);
		return userRepository.save(user);

	}
	
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
		
	}
	
	public User getCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return this.findByUsername(username);
		
	}

}
