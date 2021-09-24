package com.example.marketinnovation.service;

import com.example.marketinnovation.model.Item;
import com.example.marketinnovation.model.ItemInstance;
import com.example.marketinnovation.model.ItemInstanceStatus;
import com.example.marketinnovation.repository.GenericRepository;
import com.example.marketinnovation.repository.ItemInstanceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class ItemInstanceServiceImpl extends GenericServiceImpl<ItemInstance> implements ItemInstanceService{
    private final ItemInstanceRepository itemInstanceRepository;
    private final ItemInventoryService itemInventoryService;

    public ItemInstanceServiceImpl(ItemInstanceRepository itemInstanceRepository, ItemInventoryService itemInventoryService){
        this.itemInstanceRepository = itemInstanceRepository;
        this.itemInventoryService = itemInventoryService;
    }

    public void changeStatus(Long idItemInstance, ItemInstanceStatus status){
        ItemInstance itemInstance = findById(idItemInstance);
        if(itemInstance != null){
            itemInstance.setItemInstanceStatus(status);
        }
    }

    public List<ItemInstance> findAllByItemInstanceStatusService(ItemInstanceStatus status){
        List<ItemInstance> itemInstances = findAll();
        List<ItemInstance> itemInstancesAVAILABLE = new ArrayList<ItemInstance>();

        for (ItemInstance itemInstance : itemInstances) {
            if(itemInstance.getItemInstanceStatus().toString().equals(status.toString())){
                itemInstancesAVAILABLE.add(itemInstance);
            }
        }
        return itemInstancesAVAILABLE;
    }
    @Override
    protected GenericRepository<ItemInstance> getRepository() {
        return itemInstanceRepository;
    }

    @Override
    public void saveMany(Item item,Long idItemInventory, String skus, BigDecimal price, LocalDate dueDate) {
        String[] skusList = skus.split("\\s+");
        for (String sku : skusList) {
            ItemInstance itemInstance = new ItemInstance();
            itemInstance.setItem(item);
            itemInstance.setFeatured(true);
            itemInstance.setIdentifier(sku);
            itemInstance.setItemInstanceStatus(ItemInstanceStatus.AVAILABLE);
            itemInstance.setPrice(price);
            itemInstance.setDueDate(dueDate);
            save(itemInstance);
        }
        itemInventoryService.addStockQuantity(idItemInventory,new BigDecimal(skusList.length));
        itemInventoryService.addTotalPrice(idItemInventory,new BigDecimal(price.doubleValue()*skusList.length));
    }
}
