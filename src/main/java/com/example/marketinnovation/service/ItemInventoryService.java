package com.example.marketinnovation.service;

import com.example.marketinnovation.model.ItemInventory;

import java.math.BigDecimal;

public interface ItemInventoryService extends GenericService<ItemInventory>{

    void subtractStockQuantity(Long idItemInventory,BigDecimal units);
    void addStockQuantity(Long idItemInventory, BigDecimal units);
    BigDecimal getTotalPrice(Long idItemInventory);
    void subtractTotalPrice(Long idItemInventory, BigDecimal amount);
    void addTotalPrice(Long idItemInventory, BigDecimal amount);
}
