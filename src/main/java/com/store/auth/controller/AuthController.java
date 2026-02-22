package com.store.auth.controller;

import com.store.auth.entity.User;
import com.store.auth.dto.LoginRequest;
import com.store.auth.service.UserService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}

	// Register
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return userService.register(user);
	}

	// Login
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) {
		return userService.login(request);
	}

	@GetMapping("/allusers")
	public List<User> getAllUsers() {
		return userService.getAll();
	}
}