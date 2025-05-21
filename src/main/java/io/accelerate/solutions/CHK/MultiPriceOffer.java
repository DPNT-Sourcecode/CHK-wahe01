package io.accelerate.solutions.CHK;
import java.util.Map;

public class MultiPriceOffer implements Offer {
    private final String sku;
    private final int quantityRequired;
    private final int offerPrice;

    @Override
    public int apply(Map<String, Integer> quantities, ItemRepository itemRepository) {
        if (!quantities.containsKey(sku)) return 0;

        int

    }
}
