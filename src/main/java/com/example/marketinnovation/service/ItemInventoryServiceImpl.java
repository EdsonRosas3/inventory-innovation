package com.example.marketinnovation.service;

import com.example.marketinnovation.model.ItemInventory;
import com.example.marketinnovation.repository.GenericRepository;
import com.example.marketinnovation.repository.ItemInventoryRepository;

public class ItemInventoryServiceImpl extends GenericServiceImpl<ItemInventory> implements ItemInventoryService{
    private final ItemInventoryRepository itemInventoryRepository;

    public ItemInventoryServiceImpl (ItemInventoryRepository itemInventoryRepository){
        this.itemInventoryRepository = itemInventoryRepository;
    }
    @Override
    protected GenericRepository<ItemInventory> getRepository() {
        return null;
    }
}
