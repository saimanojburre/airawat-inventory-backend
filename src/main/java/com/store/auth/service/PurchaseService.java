package com.store.auth.service;

import com.store.auth.entity.Purchase;
import com.store.auth.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseService {

	private final PurchaseRepository purchaseRepository;

	public PurchaseService(PurchaseRepository purchaseRepository) {
		this.purchaseRepository = purchaseRepository;
	}

	// ✅ ADD SINGLE PURCHASE (existing)
	public Purchase addPurchase(Purchase purchase) {

		calculateFields(purchase);

		return purchaseRepository.save(purchase);
	}

	// ✅ ADD MULTIPLE PURCHASES (NEW)
	public List<Purchase> addBulkPurchases(List<Purchase> purchases) {

		for (Purchase purchase : purchases) {
			calculateFields(purchase);
		}

		return purchaseRepository.saveAll(purchases);
	}

	// ✅ COMMON LOGIC (Reusable)
	private void calculateFields(Purchase purchase) {

		// Auto total price
		Double totalPrice = purchase.getQuantity() * purchase.getPricePerUnit();

		purchase.setTotalPrice(totalPrice);

		// Auto purchase date
		if (purchase.getPurchaseDate() == null) {
			purchase.setPurchaseDate(LocalDateTime.now());
		}
	}

	// ✅ GET ALL PURCHASES
	public List<Purchase> getAllPurchases() {
		return purchaseRepository.findAll();
	}
}