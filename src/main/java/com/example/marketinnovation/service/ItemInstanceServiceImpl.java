package com.example.marketinnovation.service;

import com.example.marketinnovation.model.ItemInstance;
import com.example.marketinnovation.repository.GenericRepository;
import com.example.marketinnovation.repository.ItemInstanceRepository;

public class ItemInstanceServiceImpl extends GenericServiceImpl<ItemInstance> implements ItemInstanceService{
    private final ItemInstanceRepository itemInstanceRepository;

    public ItemInstanceServiceImpl(ItemInstanceRepository itemInstanceRepository){
        this.itemInstanceRepository = itemInstanceRepository;
    }
    @Override
    protected GenericRepository<ItemInstance> getRepository() {
        return null;
    }
}
