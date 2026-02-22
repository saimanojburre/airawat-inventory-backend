package com.store.auth.controller;

import com.store.auth.dto.PurchaseResponse;
import com.store.auth.entity.Purchase;
import com.store.auth.service.PurchaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

	private final PurchaseService purchaseService;

	public PurchaseController(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	@PostMapping
	public Purchase addPurchase(@RequestBody Purchase purchase) {
		return purchaseService.addPurchase(purchase);
	}

	@PostMapping("/bulk")
	public List<Purchase> addBulk(@RequestBody List<Purchase> purchases) {
	    return purchaseService.addBulkPurchases(purchases);
	}

	@GetMapping
	public List<PurchaseResponse> getAll() {
	    return purchaseService.getAllPurchases();
	}
}