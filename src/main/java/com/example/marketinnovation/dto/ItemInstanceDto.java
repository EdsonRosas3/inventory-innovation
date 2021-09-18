package com.example.marketinnovation.dto;

import com.example.marketinnovation.model.ItemInstance;

import java.math.BigDecimal;

public class ItemInstanceDto extends DtoBase<ItemInstance> {
    private ItemDto item;
    private String identifier;// sku
    private Boolean featured = Boolean.FALSE;
    private BigDecimal price;


    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}