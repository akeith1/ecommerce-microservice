package com.ecommerceMicroservice.inventoryservice.service;

import com.ecommerceMicroservice.inventoryservice.dto.InventoryResponse;
import com.ecommerceMicroservice.inventoryservice.model.Inventory;
import com.ecommerceMicroservice.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode){
        log.info("Wait started");
        Thread.sleep(10000);
        log.info("Wait ended");
        List<InventoryResponse> list = new ArrayList<>();
        for (Inventory inventory : inventoryRepository.findBySkuCodeIn(skuCode)) {
            InventoryResponse build = InventoryResponse.builder()
                    .skuCode(inventory.getSkuCode())
                    .isInStock(inventory.getQuantity() > 0)
                    .build();
            list.add(build);
        }
        return list;
    }
}
