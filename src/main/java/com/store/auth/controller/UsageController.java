package com.store.auth.controller;

import com.store.auth.entity.Usage;
import com.store.auth.service.UsageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usage")
@CrossOrigin(origins = "http://localhost:4200")
public class UsageController {

	private final UsageService usageService;

	public UsageController(UsageService usageService) {
		this.usageService = usageService;
	}

	// ✅ BULK SAVE
	@PostMapping("/bulk")
	public List<Usage> bulkUsage(@RequestBody List<Usage> usages) {
		return usageService.bulkAdd(usages);
	}

	// ✅ GET ALL
	@GetMapping
	public List<Usage> getAll() {
		return usageService.getAll();
	}
}