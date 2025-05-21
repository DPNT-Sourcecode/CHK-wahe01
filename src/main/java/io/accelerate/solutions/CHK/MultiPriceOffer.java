package io.accelerate.solutions.CHK;
import java.util.Map;

public class MultiPriceOffer implements Offer {
    private final String sku;
    private final int quantityRequired;
    private final int offerPrice;

    @Override
    public int apply(Map<String, Integer> quantities, ItemRepository itemRepository) {
        if (!quantities.containsKey(sku)) return 0;
        int count = quantities.get(sku);
        if (count < quantityRequired) return 0;
        if (count < quantityRequired) {
            System.out.println("Invalid: quantityRequired = 0");
            return 0;
        }

        Item item = itemRepository.getItem(sku);
        int unitPrice = item.getUnitPrice();



        int numOfOffers = count / quantityRequired;


    }
}

