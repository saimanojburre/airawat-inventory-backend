package com.store.auth.service;

import com.store.auth.entity.Item;
import com.store.auth.entity.User;
import com.store.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.store.auth.dto.LoginRequest;
import com.store.auth.security.JwtService;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final JwtService jwtService;

	public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtService jwtService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
	}

	// Register
	public User register(User user) {

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}

	// Login
	public String login(LoginRequest request) {

		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {

			return jwtService.generateToken(user.getUsername());
		}

		throw new RuntimeException("Invalid password");
	}
	//get all users
	public List<User> getAll() {
        return userRepository.findAll();
    }
}