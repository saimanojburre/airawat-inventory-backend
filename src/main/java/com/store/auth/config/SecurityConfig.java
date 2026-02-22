package com.store.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.store.auth.security.JwtAuthFilter;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	private final JwtAuthFilter jwtAuthFilter;

	public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
		this.jwtAuthFilter = jwtAuthFilter;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.cors(cors -> {
		}).csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth

				// ✅ PUBLIC APIs
				.requestMatchers("/", "/auth/**", "/usage/**", "/purchases/**", "/inventory/**", "/items/**", "/error")
				.permitAll()

				// 🔒 others protected
				.anyRequest().authenticated())
				.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
				.formLogin(form -> form.disable());

		return http.build();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}