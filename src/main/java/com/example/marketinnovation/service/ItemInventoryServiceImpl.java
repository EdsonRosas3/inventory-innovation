package com.example.marketinnovation.service;

import com.example.marketinnovation.model.ItemInventory;
import com.example.marketinnovation.repository.GenericRepository;
import com.example.marketinnovation.repository.ItemInventoryRepository;
import com.example.marketinnovation.util.EmailParams;
import com.example.marketinnovation.util.SendEmailUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ItemInventoryServiceImpl extends GenericServiceImpl<ItemInventory> implements ItemInventoryService{

    private final ItemInventoryRepository itemInventoryRepository;

    private SendEmailUtils sendEmailUtils;

    public ItemInventoryServiceImpl (ItemInventoryRepository itemInventoryRepository, SendEmailUtils sendEmailUtils){
        this.sendEmailUtils = sendEmailUtils;
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
               sendEmailController(itemInventory);
           }
        }
    }
    public void addStockQuantity(Long idItemInventory,BigDecimal units){
        ItemInventory itemInventory = findById(idItemInventory);
        if(itemInventory!= null){
            itemInventory.setStockQuantity(itemInventory.getStockQuantity().add(units));
            save(itemInventory);
            sendEmailController(itemInventory);
        }
    }
    public void sendEmailController(ItemInventory itemInventory){
        String message = "";
        if(itemInventory.getLowerBoundThreshold().doubleValue()> itemInventory.getStockQuantity().doubleValue()){
            message= "El Stock del producto "+itemInventory.getItem().getName()+" esta en su punto mínimo.";
            sendEmailWhenMinimumOrMaximum(message,itemInventory.getStockQuantity());
        }
        if(itemInventory.getUpperBoundThreshold().doubleValue()< itemInventory.getStockQuantity().doubleValue()){
            message= "El Stock del producto "+itemInventory.getItem().getName()+" esta en su punto máximo.";
            sendEmailWhenMinimumOrMaximum(message,itemInventory.getStockQuantity());
        }
    }
    public void sendEmailWhenMinimumOrMaximum(String message, BigDecimal stock){
        try{
            EmailParams emailParams = new EmailParams();
            emailParams.setMessage(message);
            emailParams.setCurrentStock(stock.toString());
            String[] to = {"rosas.eds.000333@gmail.com"};
            sendEmailUtils.thymeleafEmail("edsonrosas321@gmail.com",to,"Informe de emergencia de Stock", emailParams, "email/email");
        }catch (Exception e){
            e.printStackTrace();
        }

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
            BigDecimal total = itemInventory.getTotalPrice().subtract(amount);
            if(itemInventory.getTotalPrice().doubleValue() >= amount.doubleValue()){
                itemInventory.setTotalPrice(total);
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
