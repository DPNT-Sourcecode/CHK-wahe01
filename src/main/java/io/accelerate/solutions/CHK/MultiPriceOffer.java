package io.accelerate.solutions.CHK;
import java.util.Map;

public class MultiPriceOffer implements Offer {
    private final String sku;
    private final int quantityRequired;
    private final int offerPrice;

    public MultiPriceOffer(String sku, int quantityRequired, int offerPrice) {
        this.sku = sku;
        this.quantityRequired = quantityRequired;
        this.offerPrice = offerPrice;
    }

    @Override
    public int apply(Map<String, Integer> quantities, ItemsRepo itemsRepo) {
        if (!quantities.containsKey(sku)) return 0;
        int count = quantities.get(sku);
        if (count < quantityRequired) return 0;
        if (quantityRequired == 0) {
            System.out.println("Invalid: quantityRequired = 0");
            return 0;
        }

        Item item = itemsRepo.getItem(sku);
        int unitPrice = item.getUnitPrice();

        int numOfOffers = count / quantityRequired;
        int discount = numOfOffers * (quantityRequired * unitPrice - offerPrice);

        // Update quantity after applying this offer, maybe required
        quantities.put(sku, count - numOfOffers * quantityRequired);

        return discount;
    }


    public int getQuantityRequired() {
        return quantityRequired;
    }

    public int getUnitSavings(ItemsRepo repo) {
        int unitPrice = repo.getItem(sku).getUnitPrice();
        return quantityRequired * unitPrice - offerPrice;
    }

}



