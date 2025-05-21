package io.accelerate.solutions.CHK;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class TotalPriceCalculator {
    private final ItemsRepo itemsRepo;
    public TotalPriceCalculator (ItemsRepo itemsRepo){
        this.itemsRepo = itemsRepo;
    }

    public int calculateTotal (String skus) {
        if (skus == null || !skus.matches("^[A-Z]*$")){
            System.out.println("Invalid sku found!");
            return -1;
        }

        // Count original quantities
        Map<String, Integer> originalQuantities = new HashMap<>();
        for (char c : skus.toCharArray()) {
            String sku = String.valueOf(c);
            if (!itemsRepo.isSkuValid(sku)) {
                System.out.println("Unknown sku found!");
                return -1; // Unknown SKU
            }
            originalQuantities.put(sku, originalQuantities.getOrDefault(sku, 0) + 1);
        }

        // Clone for manipulation
        

    }
}


