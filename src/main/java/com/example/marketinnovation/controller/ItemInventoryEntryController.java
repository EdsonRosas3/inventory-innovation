package com.example.marketinnovation.controller;

import com.example.marketinnovation.dto.ItemInventoryEntryDto;
import com.example.marketinnovation.model.ItemInventoryEntry;
import com.example.marketinnovation.service.GenericService;
import com.example.marketinnovation.service.ItemInventoryEntryService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/items/entry")
public class ItemInventoryEntryController extends GenericController<ItemInventoryEntry, ItemInventoryEntryDto>{

    private ItemInventoryEntryService itemInventoryEntryService;

    public ItemInventoryEntryController(ItemInventoryEntryService itemInventoryEntryService){
        this.itemInventoryEntryService = itemInventoryEntryService;
    }

    @PostMapping("/buy/{idItemInventory}/{price}/{dueDate}")
    public ItemInventoryEntry toBuy(@PathVariable Long idItemInventory, @PathVariable BigDecimal price, @PathVariable String dueDate, @RequestBody ItemInventoryEntry itemInventoryEntry){
        return itemInventoryEntryService.registerPurchase(idItemInventory,price,LocalDate.parse(dueDate),itemInventoryEntry);
    }

    @GetMapping("/sell/{idItemInventory}/{units}")
    public ItemInventoryEntry sell(@PathVariable Long idItemInventory, @PathVariable BigDecimal units){
        return itemInventoryEntryService.sellProducts(idItemInventory,units);
    }

    @GetMapping("/discard/{idItemInventory}")
    public ItemInventoryEntry discard(@PathVariable Long idItemInventory){
        return itemInventoryEntryService.disposeOfProducts(idItemInventory);
    }

    @Override
    protected GenericService getService() {
        return itemInventoryEntryService;
    }
}
