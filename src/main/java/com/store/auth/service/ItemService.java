package com.store.auth.service;

import com.store.auth.entity.Item;
import com.store.auth.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item save(Item item) {
        item.setTotalPrice(
                item.getQuantity() * item.getPricePerUnit()
        );
        return itemRepository.save(item);
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }
}