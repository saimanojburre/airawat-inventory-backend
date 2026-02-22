package com.store.auth.service;

import com.store.auth.dto.UsageResponse;
import com.store.auth.entity.Item;
import com.store.auth.entity.Usage;
import com.store.auth.repository.ItemRepository;
import com.store.auth.repository.UsageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsageService {

	private final UsageRepository usageRepository;
	private final InventoryService inventoryService;
	private final ItemRepository itemRepository;

	public UsageService(UsageRepository usageRepository, InventoryService inventoryService,
			ItemRepository itemRepository) {

		this.usageRepository = usageRepository;
		this.inventoryService = inventoryService;
		this.itemRepository = itemRepository;
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

	public List<UsageResponse> getAllUsage() {

		List<Usage> usages = usageRepository.findAll();

		return usages.stream().map(u -> {

			UsageResponse dto = new UsageResponse();

			dto.setId(u.getId());
			dto.setItemId(u.getItemId());

			// ✅ fetch item name
			String itemName = itemRepository.findById(u.getItemId()).map(Item::getItemName).orElse("Unknown Item");

			dto.setItemName(itemName);

			dto.setQuantity(u.getQuantity());
			dto.setDepartment(u.getDepartment());
			dto.setUsedFor(u.getUsedFor());
			dto.setGivenBy(u.getGivenBy());
			dto.setTakenBy(u.getTakenBy());
			dto.setUsageTime(u.getUsageTime());

			return dto;

		}).toList();
	}

}