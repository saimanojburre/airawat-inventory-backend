package com.store.auth.controller;

import com.store.auth.service.CsvImportService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private final CsvImportService csvImportService;

    public UploadController(CsvImportService csvImportService) {
        this.csvImportService = csvImportService;
    }

    @PostMapping("/items")
    public String uploadItems(@RequestParam("file") MultipartFile file) {
        return csvImportService.importItems(file);
    }
}