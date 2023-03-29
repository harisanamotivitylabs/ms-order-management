package com.inventoryservice.controller;

import com.inventoryservice.dto.InventoryResponse;
import com.inventoryservice.entity.Inventory;
import com.inventoryservice.repository.InventoryRepository;
import com.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/skuCode")
    public List<InventoryResponse> isInStock(@RequestParam(name = "skuCode") List<String> skuCode ){

        return inventoryService.isInStock(skuCode);
    }
    @PostMapping("/save")
    public Inventory saveInventory(@RequestBody Inventory inventory)
    {
        return inventoryService.saveInventory(inventory);
    }
}
