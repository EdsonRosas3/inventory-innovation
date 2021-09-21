package com.example.marketinnovation.service;

import com.example.marketinnovation.model.Item;
import com.example.marketinnovation.model.ItemInstance;
import com.example.marketinnovation.model.ItemInstanceStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ItemInstanceService extends GenericService<ItemInstance>{

    void changeStatus(Long idItemInstance, ItemInstanceStatus status);
    List<ItemInstance> findAllByItemInstanceStatusService(ItemInstanceStatus status);
    void saveMany(Item item,Long idItemInventory, String skus, BigDecimal price, LocalDate dueDate);
}
