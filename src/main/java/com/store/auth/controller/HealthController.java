package com.store.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

	@GetMapping("/")
	public String health() {
		return "Airawat Inventory Backend Running";
	}
}