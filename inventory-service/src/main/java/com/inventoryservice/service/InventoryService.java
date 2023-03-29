package com.inventoryservice.service;

import com.inventoryservice.dto.InventoryResponse;
import com.inventoryservice.entity.Inventory;

import java.util.List;

public interface InventoryService {

    public List<InventoryResponse> isInStock(List<String> skuCode);
    public Inventory saveInventory(Inventory inventory);

}
