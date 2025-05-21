package io.accelerate.solutions.CHK;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class ItemsRepo {
    private final Map <String, Item> itemMap = new HashMap<>();
    public ItemsRepo () {
        itemMap.put("A", new Item("A", 50, List.of(
                new MultiPriceOffer("A", 5, 200),
                new MultiPriceOffer("A", 3, 130)
        )));
        itemMap.put("B", new Item("B", 30, List.of(
                new MultiPriceOffer("B", 2, 45)
        )));
        itemMap.put("C", new Item("C", 20, Collections.emptyList()));
        itemMap.put("D", new Item("D", 15, Collections.emptyList()));
        itemMap.put("E", new Item("E", 40, List.of(
                new FreeItemOffer("E", 2, "B", 1)
        )));
    }

    public Item getItem (String sku){
        return itemMap.get(sku);
    }

    public boolean isSkuValid (String sku){
        return itemMap.containsKey(sku);
    }
}
