package com.example.marketinnovation.service;

import com.example.marketinnovation.model.ItemInventoryEntry;
import com.example.marketinnovation.repository.GenericRepository;
import com.example.marketinnovation.repository.ItemInventoryEntryRepository;

public class ItemInventoryEntryServiceImpl extends GenericServiceImpl<ItemInventoryEntry> implements ItemInventoryEntryService{

    private final ItemInventoryEntryRepository itemInventoryEntryRepository;

    public ItemInventoryEntryServiceImpl(ItemInventoryEntryRepository itemInventoryEntryRepository){
        this.itemInventoryEntryRepository = itemInventoryEntryRepository;
    }

    @Override
    protected GenericRepository<ItemInventoryEntry> getRepository() {
        return null;
    }
}
