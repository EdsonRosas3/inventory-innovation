package com.example.marketinnovation.controller;

import com.example.marketinnovation.dto.ItemDto;
import com.example.marketinnovation.model.Item;
import com.example.marketinnovation.service.ItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController extends GenericController<Item, ItemDto> {
    private ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @Override
    protected ItemService getService() {
        return service;
    }
}