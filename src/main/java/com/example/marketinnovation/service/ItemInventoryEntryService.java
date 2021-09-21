package com.example.marketinnovation.service;

import com.example.marketinnovation.model.ItemInventoryEntry;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ItemInventoryEntryService extends GenericService<ItemInventoryEntry>{
    ItemInventoryEntry registerPurchase(Long id, BigDecimal price, LocalDate dueDate, ItemInventoryEntry itemInventoryEntry);
    ItemInventoryEntry sellProducts(Long idItemInventory, BigDecimal units);
    ItemInventoryEntry disposeOfProducts(Long idItemInventory);
}
