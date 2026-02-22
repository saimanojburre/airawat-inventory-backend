package com.store.auth.service;

import com.store.auth.dto.InventoryResponse;
import com.store.auth.entity.Item;
import com.store.auth.repository.ItemRepository;
import com.store.auth.repository.PurchaseRepository;
import com.store.auth.repository.UsageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

	private final ItemRepository itemRepository;
	private final PurchaseRepository purchaseRepository;
	private final UsageRepository usageRepository;

	public InventoryService(ItemRepository itemRepository, PurchaseRepository purchaseRepository,
			UsageRepository usageRepository) {
		this.itemRepository = itemRepository;
		this.purchaseRepository = purchaseRepository;
		this.usageRepository = usageRepository;
	}

	// ✅ MAIN INVENTORY VIEW
	public List<InventoryResponse> getInventory() {

		List<Item> items = itemRepository.findAll();
		List<InventoryResponse> response = new ArrayList<>();

		for (Item item : items) {

			// Total Purchased
			Double purchased = purchaseRepository.getTotalPurchased(item.getId());

			// Total Used
			Double used = usageRepository.getTotalUsed(item.getId());

			// Available Quantity
			Double availableQty = purchased - used;

			// Average Price Per Unit
			Double avgPrice = purchaseRepository.getAveragePrice(item.getId());
			if (avgPrice == null)
				avgPrice = 0.0;

			// Total Inventory Value
			Double totalPrice = availableQty * avgPrice;

			response.add(new InventoryResponse(item.getId(), item.getItemName(), item.getUnit(), availableQty, avgPrice,
					totalPrice, item.getCategory()));
		}

		return response;
	}

	// ✅ Used by UsageService to validate stock
	public Double getAvailableStock(Long itemId) {

		Double purchased = purchaseRepository.getTotalPurchased(itemId);
		Double used = usageRepository.getTotalUsed(itemId);

		return purchased - used;
	}
}