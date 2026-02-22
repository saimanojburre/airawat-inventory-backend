package com.store.auth.controller;

import com.store.auth.dto.InventoryResponse;
import com.store.auth.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = "http://localhost:4200")
public class InventoryController {

	private final InventoryService inventoryService;

	public InventoryController(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@GetMapping("/stock/{itemId}")
	public Double getStock(@PathVariable Long itemId) {
		return inventoryService.getAvailableStock(itemId);
	}

	@GetMapping
	public List<InventoryResponse> getInventory() {
		return inventoryService.getInventory();
	}
}