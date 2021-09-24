package com.example.marketinnovation.service;

import com.example.marketinnovation.model.ItemInventory;
import com.example.marketinnovation.model.ItemInventoryEntry;
import com.example.marketinnovation.model.MovementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.testng.Assert.*;

@SpringBootTest
public class ItemInventoryEntryServiceImplTest {

    @Autowired
    private ItemInventoryService itemInventoryService;
    @Autowired
    private ItemInventoryEntryService itemInventoryEntryService;

    @Test
    public void testRegisterPurchase() {

        ItemInventoryEntry itemInventoryEntry = new ItemInventoryEntry();
        itemInventoryEntry.setMovementType(MovementType.BUY);
        itemInventoryEntry.setQuantity(new BigDecimal(3));
        itemInventoryEntry.setItemInstanceSkus("SKU-77721106006196 SKU-7772110600617 SKU-77721106006179");
        ItemInventoryEntry itemInventoryEntryRes =  itemInventoryEntryService.registerPurchase(1L,new BigDecimal(6), LocalDate.parse("2021-10-03"),itemInventoryEntry);
        ItemInventory itemInventory =  itemInventoryEntryRes.getItemInventory();
        itemInventory.getStockQuantity();
        int stock = itemInventory.getStockQuantity().intValue();
        int price = itemInventory.getTotalPrice().intValue();
        assertEquals(stock,10);
    }

    @Test
    public void testSellProducts() {

    }

    @Test
    public void testDisposeOfProducts() {
    }

    @Test
    public void testGetRepository() {
    }
}