package io.accelerate.solutions.CHK;
import java.util.Map;

public class FreeItemOffer implements Offer {
    private final String requiredSku;
    private final int requiredQuantity;
    private final String freeSku;
    private final int freeQuantity;

    public FreeItemOffer(String requiredSku, int requiredQuantity, String freeSku, int freeQuantity) {
        this.requiredSku = requiredSku;
        this.requiredQuantity = requiredQuantity;
        this.freeSku = freeSku;
        this.freeQuantity = freeQuantity;
    }

    @Override
    public int apply(Map<String, Integer> quantities, ItemsRepo itemsRepo) {
        
    }


}
