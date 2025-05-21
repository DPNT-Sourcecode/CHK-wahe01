package io.accelerate.solutions.CHK;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class ItemsRepo {
    private final Map <String, Item> itemMap = new HashMap<>();
    public ItemsRepo () {

        GroupOffer groupSTXYZ = new GroupOffer(
                List.of("S", "T", "X", "Y", "Z"), 3, 45
        );

        itemMap.put("A", new Item("A", 50, List.of(
                new MultiPriceOffer("A", 5, 200),
                new MultiPriceOffer("A", 3, 130)
        )));
        itemMap.put("B", new Item("B", 30, List.of(
                new MultiPriceOffer("B", 2, 45)
        )));
        itemMap.put("C", new Item("C", 20, List.of()));
        itemMap.put("D", new Item("D", 15, List.of()));
        itemMap.put("E", new Item("E", 40, List.of(
                new FreeItemOffer("E", 2, "B", 1)
        )));
        itemMap.put("F", new Item("F", 10, List.of(
                new SelFreeItemOffer("F", 3, 2)
        )));
        itemMap.put("G", new Item("G", 20, List.of()));
        itemMap.put("H", new Item("H", 10, List.of(
                new MultiPriceOffer("H", 10, 80),
                new MultiPriceOffer("H", 5, 45)
        )));
        itemMap.put("I", new Item("I", 35, List.of()));
        itemMap.put("J", new Item("J", 60, List.of()));
        itemMap.put("K", new Item("K", 70, List.of(
                new MultiPriceOffer("K", 2, 120)
        )));
        itemMap.put("L", new Item("L", 90, List.of()));
        itemMap.put("M", new Item("M", 15, List.of()));
        itemMap.put("N", new Item("N", 40, List.of(
                new FreeItemOffer("N", 3, "M", 1)
        )));
        itemMap.put("O", new Item("O", 10, List.of()));
        itemMap.put("P", new Item("P", 50, List.of(
                new MultiPriceOffer("P", 5, 200)
        )));
        itemMap.put("Q", new Item("Q", 30, List.of(
                new MultiPriceOffer("Q", 3, 80)
        )));
        itemMap.put("R", new Item("R", 50, List.of(
                new FreeItemOffer("R", 3, "Q", 1)
        )));
        itemMap.put("S", new Item("S", 20, List.of(groupSTXYZ)));
        itemMap.put("T", new Item("T", 20, List.of(groupSTXYZ)));
        itemMap.put("U", new Item("U", 40, List.of(
                new SelFreeItemOffer("U", 4, 3)
        )));
        itemMap.put("V", new Item("V", 50, List.of(
                new MultiPriceOffer("V", 3, 130),
                new MultiPriceOffer("V", 2, 90)
        )));
        itemMap.put("W", new Item("W", 20, List.of()));
        itemMap.put("X", new Item("X", 17, List.of(groupSTXYZ)));
        itemMap.put("Y", new Item("Y", 20, List.of(groupSTXYZ)));
        itemMap.put("Z", new Item("Z", 21, List.of(groupSTXYZ)));

    }

    public Item getItem (String sku){
        return itemMap.get(sku);
    }

    public boolean isSkuValid (String sku){
        return itemMap.containsKey(sku);
    }
}


