package com.example.marketinnovation.dto;

import com.example.marketinnovation.model.ItemInventory;
import com.example.marketinnovation.model.ItemInventoryEntry;
import com.example.marketinnovation.model.MovementType;

import java.math.BigDecimal;

public class ItemInventoryEntryDto extends DtoBase<ItemInventoryEntry>{
    private ItemInventoryDto itemInventory;
    private MovementType movementType;
    private BigDecimal quantity;
    private String itemInstanceSkus;

    public ItemInventoryDto getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(ItemInventoryDto itemInventory) {
        this.itemInventory = itemInventory;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getItemInstanceSkus() {
        return itemInstanceSkus;
    }

    public void setItemInstanceSkus(String itemInstanceSkus) {
        this.itemInstanceSkus = itemInstanceSkus;
    }
}
