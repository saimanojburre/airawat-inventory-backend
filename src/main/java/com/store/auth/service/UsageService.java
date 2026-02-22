package com.store.auth.service;

import com.store.auth.entity.Usage;
import com.store.auth.repository.UsageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsageService {

	private final UsageRepository usageRepository;
	private final InventoryService inventoryService;

	public UsageService(UsageRepository usageRepository, InventoryService inventoryService) {

		this.usageRepository = usageRepository;
		this.inventoryService = inventoryService;
	}

	// ✅ SINGLE USAGE
	public Usage addUsage(Usage usage) {

		Double availableStock = inventoryService.getAvailableStock(usage.getItemId());

		if (usage.getQuantity() > availableStock) {
			throw new RuntimeException("Not enough stock available!");
		}

		usage.setUsageTime(LocalDateTime.now());

		return usageRepository.save(usage);
	}

	// ✅ BULK USAGE (NEW)
	public List<Usage> bulkAdd(List<Usage> usages) {

		for (Usage usage : usages) {

			Double availableStock = inventoryService.getAvailableStock(usage.getItemId());

			if (usage.getQuantity() > availableStock) {
				throw new RuntimeException("Not enough stock for itemId: " + usage.getItemId());
			}

			usage.setUsageTime(LocalDateTime.now());
		}

		return usageRepository.saveAll(usages);
	}

	public List<Usage> getAll() {
		return usageRepository.findAll();
	}
}