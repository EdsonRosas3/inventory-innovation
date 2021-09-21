package com.example.marketinnovation.repository;

import com.example.marketinnovation.model.ItemInstance;
import com.example.marketinnovation.model.ItemInstanceStatus;

import java.util.List;

public interface ItemInstanceRepository extends GenericRepository<ItemInstance>{

    List<ItemInstance> findAllByItemInstanceStatus(ItemInstanceStatus status);
}
