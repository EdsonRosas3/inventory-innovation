package com.example.marketinnovation.service;

import com.example.marketinnovation.model.Item;
import com.example.marketinnovation.repository.GenericRepository;
import com.example.marketinnovation.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl extends GenericServiceImpl<Item> implements ItemService {
    private final ItemRepository repository;

    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<Item> getRepository() {
        return repository;
    }
}
