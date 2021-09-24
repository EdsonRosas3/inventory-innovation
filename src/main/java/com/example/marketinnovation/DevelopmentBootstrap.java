package com.example.marketinnovation;

import com.example.marketinnovation.model.Item;
import com.example.marketinnovation.model.ItemInstance;
import com.example.marketinnovation.model.ItemInstanceStatus;
import com.example.marketinnovation.model.ItemInventory;
import com.example.marketinnovation.service.ItemInstanceService;
import com.example.marketinnovation.service.ItemInventoryService;
import com.example.marketinnovation.service.ItemService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DevelopmentBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final ItemInstanceService itemInstanceService;
    private final ItemInventoryService itemInventoryService;
    private final ItemService itemService;

    public DevelopmentBootstrap(ItemInstanceService itemInstanceService, ItemInventoryService itemInventoryService,  ItemService itemService){
        this.itemInstanceService = itemInstanceService;
        this.itemInventoryService = itemInventoryService;
        this.itemService = itemService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Item item = persistItems();
        persistItemInventory(item);
        persistItemInstances(item);
    }
    public void persistItemInventory(Item item){
        ItemInventory itemInventory = new ItemInventory();
        itemInventory.setItem(item);
        itemInventory.setUpperBoundThreshold(new BigDecimal(10));
        itemInventory.setLowerBoundThreshold(new BigDecimal(3));
        itemInventory.setStockQuantity(new BigDecimal(7));
        itemInventory.setTotalPrice(new BigDecimal(35));
        itemInventoryService.save(itemInventory);
    }
    private void persistItemInstances(Item maltinItem) {
        ItemInstance maltinItem1 = createItem(maltinItem, "SKU-77721106006158","2021-12-28", 5D);
        ItemInstance maltinItem2 = createItem(maltinItem, "SKU-77721106006159","2021-10-11",5D);
        ItemInstance maltinItem3 = createItem(maltinItem, "SKU-77721106006160","2021-04-25",5D);
        ItemInstance maltinItem4 = createItem(maltinItem, "SKU-77721106006161","2021-08-30",5D);
        ItemInstance maltinItem5 = createItem(maltinItem, "SKU-77721106006157","2021-10-11",5D);
        ItemInstance maltinItem6 = createItem(maltinItem, "SKU-77721106006162","2021-04-25",5D);
        ItemInstance maltinItem7 = createItem(maltinItem, "SKU-77721106006163","2021-08-30",5D);
        itemInstanceService.save(maltinItem1);
        itemInstanceService.save(maltinItem2);
        itemInstanceService.save(maltinItem3);
        itemInstanceService.save(maltinItem4);
        itemInstanceService.save(maltinItem5);
        itemInstanceService.save(maltinItem6);
        itemInstanceService.save(maltinItem7);

    }

    private ItemInstance createItem(Item maltinItem, String sku,String dueDate ,double price) {
        ItemInstance itemInstance = new ItemInstance();
        itemInstance.setItem(maltinItem);
        itemInstance.setFeatured(true);
        itemInstance.setPrice(new BigDecimal(price));
        itemInstance.setDueDate(LocalDate.parse(dueDate));
        itemInstance.setIdentifier(sku);
        itemInstance.setItemInstanceStatus(ItemInstanceStatus.AVAILABLE);
        return itemInstance;
    }

    private Item persistItems() {
        Item item = new Item();
        item.setCode("B-MALTIN");
        item.setName("MALTIN");

        return itemService.save(item);
    }
}
