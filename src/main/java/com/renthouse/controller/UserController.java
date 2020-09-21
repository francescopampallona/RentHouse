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
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
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
	
	@GetMapping("/home/")
	public String getByUsername(HttpServletRequest request) {
		User user = this.userService.getCurrentUser();
		request.setAttribute("user", user);
		return "home";
	}
	
	@PostMapping("/update/")
	public String updateUserData(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("telephone") String telephone) {
		User user = this.userService.getCurrentUser();
		user.setEmail(email);
		user.setTelephone(telephone);
		this.userService.update(user);
		request.setAttribute("user", user);
		return "home";
	}
	
	
	
	
	
	

	
}
