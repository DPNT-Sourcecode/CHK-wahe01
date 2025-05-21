package io.accelerate.solutions.CHK;
import java.util.Map;

public class SelFreeItemOffer implements Offer {
    private final String sku;
    private final int requiredQuantity; // Number required in basket
    private final int chargedQuantity; // Number to charge for

    public SelfFreeItemOffer(String sku, int requiredQuantity, int chargedQuantity) {
        this.sku = sku;
        this.requiredQuantity = requiredQuantity;
        this.chargedQuantity = chargedQuantity;
    }

    @Override
    public int apply(Map<String, Integer> quantities, ItemsRepo itemRepo) {
        
    }


}
