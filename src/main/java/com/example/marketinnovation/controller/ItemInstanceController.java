package com.example.marketinnovation.controller;

import com.example.marketinnovation.dto.ItemInstanceDto;
import com.example.marketinnovation.model.ItemInstance;
import com.example.marketinnovation.service.ItemInstanceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item/instances")
public class ItemInstanceController extends GenericController<ItemInstance, ItemInstanceDto> {
    private ItemInstanceService service;

    public ItemInstanceController(ItemInstanceService service) {
        this.service = service;
    }

    @Override
    protected ItemInstanceService getService() {
        return service;
    }
}