package com.store.auth.service;

import com.opencsv.CSVReader;
import com.store.auth.entity.Item;
import com.store.auth.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvImportService {

    private final ItemRepository itemRepository;

    public CsvImportService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public String importItems(MultipartFile file) {

        try (CSVReader reader =
                     new CSVReader(new InputStreamReader(file.getInputStream()))) {

            List<Item> items = new ArrayList<>();

            String[] line;
            reader.readNext(); // skip header

            while ((line = reader.readNext()) != null) {

                Item item = new Item();
                item.setItemName(line[0]);
                item.setCategory(line[1]);
                item.setUnit(line[2]);

                items.add(item);
            }

            itemRepository.saveAll(items);

            return items.size() + " items imported successfully";

        } catch (Exception e) {
            throw new RuntimeException("CSV import failed: " + e.getMessage());
        }
    }
}