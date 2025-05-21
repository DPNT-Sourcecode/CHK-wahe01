package io.accelerate.solutions.CHK;
import java.util.HashMap;
import java.util.Map;

public class TotalPriceCalculator {
    private ItemsRepo itemsRepo;
    public TotalPriceCalculator (ItemsRepo itemsRepo){
        this.itemsRepo = itemsRepo;
    }

    public int calculateTotal (String skus) {
        if (skus == null || !skus.matches("^[A-Z]*$")){
            System.out.println("Invalid sku found!");
            return -1;
        }


    }
}


