package io.accelerate.solutions.CHK;
import java.util.HashMap;
import java.util.Map;

public class ItemsRepo {
    private final Map <String, Item> itemMap = new HashMap<>();
    public ItemsRepo () {
        itemMap.put("A", new Item("A", 50, new SpecialOffer(3, 130)));
        itemMap.put("B", new Item("B", 30, new SpecialOffer(2, 45)));
        itemMap.put("C", new Item("C", 20, null));
        itemMap.put("D", new Item("D", 15, null));
    }

    public Item getItem (String sku){
        return itemMap.get(sku);
    }

    public boolean isSkuValid (String sku){
        return itemMap.containsKey(sku);
    }
}
