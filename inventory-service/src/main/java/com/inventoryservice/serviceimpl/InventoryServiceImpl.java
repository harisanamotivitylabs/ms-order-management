package com.inventoryservice.serviceimpl;

import com.inventoryservice.dto.InventoryResponse;
import com.inventoryservice.entity.Inventory;
import com.inventoryservice.repository.InventoryRepository;
import com.inventoryservice.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Transactional
    @Override
    public List<InventoryResponse> isInStock(List<String> skuCode)  {

        return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(inventory ->
            InventoryResponse.builder().skuCode(inventory.getSkuCode()).isInStoke(inventory.getQuantity()>0)
                    .build()
        ).collect(Collectors.toList());
    }

    @Override
    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }
}
