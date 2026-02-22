package com.store.auth.controller;

import com.store.auth.entity.Item;
import com.store.auth.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return itemService.save(item);
    }

    @GetMapping
    public List<Item> getItems() {
        return itemService.getAll();
    }
}