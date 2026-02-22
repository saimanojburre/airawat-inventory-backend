package com.store.auth.service;

import com.store.auth.dto.PurchaseResponse;
import com.store.auth.entity.Item;
import com.store.auth.entity.Purchase;
import com.store.auth.repository.ItemRepository;
import com.store.auth.repository.PurchaseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseService {

	private final PurchaseRepository purchaseRepository;
	private final ItemRepository itemRepository;

	public PurchaseService(PurchaseRepository purchaseRepository, ItemRepository itemRepository) {
		this.purchaseRepository = purchaseRepository;
		this.itemRepository = itemRepository;
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
	public List<PurchaseResponse> getAllPurchases() {

		List<Purchase> purchases = purchaseRepository.findAll();

		return purchases.stream().map(p -> {

			PurchaseResponse dto = new PurchaseResponse();

			dto.setId(p.getId());
			dto.setItemId(p.getItemId());

			// ✅ FETCH ITEM NAME
			String itemName = itemRepository.findById(p.getItemId()).map(Item::getItemName).orElse("Unknown Item");

			dto.setItemName(itemName);

			dto.setQuantity(p.getQuantity());
			dto.setPricePerUnit(p.getPricePerUnit());
			dto.setTotalPrice(p.getTotalPrice());
			dto.setPurchasedFrom(p.getPurchasedFrom());
			dto.setPurchaseDate(p.getPurchaseDate());

			return dto;

		}).toList();
	}
}