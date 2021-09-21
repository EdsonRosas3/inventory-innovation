package com.example.marketinnovation.service;

import com.example.marketinnovation.model.*;
import com.example.marketinnovation.repository.GenericRepository;
import com.example.marketinnovation.repository.ItemInventoryEntryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class ItemInventoryEntryServiceImpl extends GenericServiceImpl<ItemInventoryEntry> implements ItemInventoryEntryService{

    private final ItemInventoryEntryRepository itemInventoryEntryRepository;
    private final ItemInventoryService itemInventoryService;
    private final ItemInstanceService itemInstanceService;



    public ItemInventoryEntryServiceImpl(ItemInventoryEntryRepository itemInventoryEntryRepository,ItemInventoryService itemInventoryService,ItemInstanceService itemInstanceService){
        this.itemInventoryEntryRepository = itemInventoryEntryRepository;
        this.itemInstanceService = itemInstanceService;
        this.itemInventoryService = itemInventoryService;
    }

    public ItemInventoryEntry registerPurchase(Long idItemInventory, BigDecimal price, LocalDate dueDate, ItemInventoryEntry itemInventoryEntry){
        ItemInventory itemInventory = itemInventoryService.findById(idItemInventory);
        itemInstanceService.saveMany(itemInventory.getItem(),idItemInventory,itemInventoryEntry.getItemInstanceSkus(),price,dueDate);
        if(itemInventory!=null){
            itemInventoryEntry.setItemInventory(itemInventory);
        }
        return  save(itemInventoryEntry);
    }

    public ItemInventoryEntry sellProducts(Long idItemInventory, BigDecimal units){
        ItemInventory itemInventory = itemInventoryService.findById(idItemInventory);
        List<ItemInstance> itemInstances = itemInstanceService.findAllByItemInstanceStatusService(ItemInstanceStatus.AVAILABLE);
        String skus = "";
        BigDecimal amountSell = new BigDecimal(0);
        for (int i = itemInstances.size()-1 ; i>itemInstances.size()-units.doubleValue()-1 ; i--){
            ItemInstance itemInstance = itemInstances.get(i);
            itemInstanceService.changeStatus(itemInstance.getId(),ItemInstanceStatus.SOLD);
            skus+=" "+itemInstance.getIdentifier();
            amountSell.add(itemInstance.getPrice());
        }
        itemInventoryService.subtractStockQuantity(itemInventory.getId(),units);
        itemInventoryService.subtractTotalPrice(itemInventory.getId(),amountSell);
        ItemInventoryEntry itemInventoryEntry = new ItemInventoryEntry();
        itemInventoryEntry.setItemInventory(itemInventory);
        itemInventoryEntry.setItemInstanceSkus(skus);
        itemInventoryEntry.setQuantity(units);
        itemInventoryEntry.setMovementType(MovementType.SALE);
        return itemInventoryEntry;
    }

    public ItemInventoryEntry disposeOfProducts(Long idItemInventory){
        ItemInventory itemInventory = itemInventoryService.findById(idItemInventory);
        List<ItemInstance> itemInstances = itemInstanceService.findAll();
        BigDecimal amount  = new BigDecimal(0);
        BigDecimal units = new BigDecimal(0);
        LocalDate dateCurrent = LocalDate.now();
        String skus = "";
        for (ItemInstance itemInstance : itemInstances) {
            if(itemInstance.getDueDate().getYear()< dateCurrent.getYear()){
                itemInstance.setItemInstanceStatus(ItemInstanceStatus.SCREWED);
                amount.add(itemInstance.getPrice());
                units.add(new BigDecimal(1));
                itemInstance.setPrice(new BigDecimal(0));
                skus+=" "+itemInstance.getIdentifier();
            }else{
                if(itemInstance.getDueDate().getYear()== dateCurrent.getYear()){
                    if(itemInstance.getDueDate().getMonthValue() < dateCurrent.getMonthValue()){
                        itemInstance.setItemInstanceStatus(ItemInstanceStatus.SCREWED);
                        amount.add(itemInstance.getPrice());
                        units.add(new BigDecimal(1));
                        itemInstance.setPrice(new BigDecimal(0));
                        skus+=" "+itemInstance.getIdentifier();
                    }else{
                        if(itemInstance.getDueDate().getMonthValue() == dateCurrent.getMonthValue()){
                            if(itemInstance.getDueDate().getDayOfMonth() < dateCurrent.getDayOfMonth()){
                                itemInstance.setItemInstanceStatus(ItemInstanceStatus.SCREWED);
                                amount.add(itemInstance.getPrice());
                                units.add(new BigDecimal(1));
                                itemInstance.setPrice(new BigDecimal(0));
                                skus+=" "+itemInstance.getIdentifier();
                            }
                        }
                    }
                }
            }
        }

        itemInventoryService.subtractTotalPrice(itemInventory.getId(),amount);

        ItemInventoryEntry itemInventoryEntry = new ItemInventoryEntry();
        itemInventoryEntry.setItemInventory(itemInventory);
        itemInventoryEntry.setItemInstanceSkus(skus);
        itemInventoryEntry.setQuantity(units);
        itemInventoryEntry.setMovementType(MovementType.REMOVED);
        return itemInventoryEntry;
    }

    @Override
    protected GenericRepository<ItemInventoryEntry> getRepository() {
        return itemInventoryEntryRepository;
    }



    /*
    Take into account sku cannot be duplicated
    In the service make possible:
       register buy item instances -> Si no existe el producto crearlo, registrar instancias,
                                        crear y actualizar el ItemInventory correspondiente con sus totalizados
                                        Generar los ItemInventoryEntry para reflejar la operacion de entrada o salida
                                         de almacen

       vender un producto
       desechar un producto similar a una venta pero a costo 0. Debe reflejar el totalizado correctamente de
       ItemInventory

       Debe haber tests unitarios que muestren escenarios para estas operaciones en casos de exito y de error.
    */
}
