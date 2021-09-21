package com.example.marketinnovation.service;

import com.example.marketinnovation.model.ItemInventory;
import com.example.marketinnovation.repository.GenericRepository;
import com.example.marketinnovation.repository.ItemInventoryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ItemInventoryServiceImpl extends GenericServiceImpl<ItemInventory> implements ItemInventoryService{
    private final ItemInventoryRepository itemInventoryRepository;

    public ItemInventoryServiceImpl (ItemInventoryRepository itemInventoryRepository){
        this.itemInventoryRepository = itemInventoryRepository;
    }

    /*return stock quantity available */
    public BigDecimal getStockQuantityAVAILABLE(Long idItemInventory){
        ItemInventory itemInventory = findById(idItemInventory);
        BigDecimal stockQuantity = new BigDecimal(0);
        if(itemInventory!= null){
            stockQuantity.add(itemInventory.getStockQuantity());
        }
        return stockQuantity;
    }

    /*subtract stock quantity*/
    public void subtractStockQuantity(Long idItemInventory,BigDecimal units){
        ItemInventory itemInventory = findById(idItemInventory);
        if(itemInventory!= null){
           if(itemInventory.getStockQuantity().doubleValue()>=units.doubleValue()){
               itemInventory.setStockQuantity(itemInventory.getStockQuantity().subtract(units));
               save(itemInventory);
           }
        }
    }
    public void addStockQuantity(Long idItemInventory,BigDecimal units){
        ItemInventory itemInventory = findById(idItemInventory);
        if(itemInventory!= null){
            itemInventory.setStockQuantity(itemInventory.getStockQuantity().add(units));
            save(itemInventory);
        }
    }

    public void sendEmailWhenMinimumOrMaximum(Long idItemInventory, String content){

    }

    public BigDecimal getTotalPrice(Long idItemInventory){
        ItemInventory itemInventory = findById(idItemInventory);
        BigDecimal totalPrice = new BigDecimal(0);
        if(itemInventory!= null){
            totalPrice.add(itemInventory.getTotalPrice());
        }
        return totalPrice;
    }

    public void subtractTotalPrice(Long idItemInventory, BigDecimal amount){
        ItemInventory itemInventory = findById(idItemInventory);
        if(itemInventory!= null){
            if(itemInventory.getTotalPrice().doubleValue() >= amount.doubleValue()){
                itemInventory.setTotalPrice(itemInventory.getTotalPrice().subtract(amount));
                save(itemInventory);
            }
        }
    }
    public void addTotalPrice(Long idItemInventory, BigDecimal amount){
        ItemInventory itemInventory = findById(idItemInventory);
        if(itemInventory!= null){
            itemInventory.setTotalPrice(itemInventory.getTotalPrice().add(amount));
            save(itemInventory);
        }
    }

    @Override
    protected GenericRepository<ItemInventory> getRepository() {
        return itemInventoryRepository;
    }
}
