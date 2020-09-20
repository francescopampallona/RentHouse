package com.renthouse.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.renthouse.model.User;
import com.renthouse.service.UserService;

@Controller 
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping	
	public String getAll(HttpServletRequest request) {
		setAll(request);
		return "users";
	}
	
	@PostMapping("/register")
	public String register(HttpServletRequest request,
		    @RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("email") String email,
			@RequestParam("telephone") String telephone) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setTelephone(telephone);
		userService.insert(user);
		return "redirect:/";
		
	}
	
	private void setAll(HttpServletRequest request) {
		request.getSession().setAttribute("list", userService.findAll());
	}
	
}
