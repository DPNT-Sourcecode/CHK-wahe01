package io.accelerate.solutions.CHK;
import java.util.Map;

public class SelFreeItemOffer implements Offer {
    private final String sku;
    private final int requiredQuantity; // Number required in basket
    private final int chargedQuantity; // Number to charge for

    public SelFreeItemOffer(String sku, int requiredQuantity, int chargedQuantity) {
        this.sku = sku;
        this.requiredQuantity = requiredQuantity;
        this.chargedQuantity = chargedQuantity;
    }

    @Override
    public int apply(Map<String, Integer> quantities, ItemsRepo itemRepo) {
        if (!quantities.containsKey(sku)) return 0;

        int count = quantities.get(sku);
        if (count < requiredQuantity) return 0;
        if (requiredQuantity == 0) {
            System.out.println("Invalid: requiredQuantity = 0");
            return 0;
        }

        int applicableTimes = count / requiredQuantity;
        int discountedUnits = applicableTimes * (requiredQuantity - chargedQuantity);

        int unitPrice = itemRepo.getItem(sku).getUnitPrice();
        int discount = discountedUnits * unitPrice;

        // Update quantity: we reduce total because free units are covered
        quantities.put(sku, count - discountedUnits);

        return discount;
    }

    @Override
    public String toString() {
        return "Buy " + chargedQuantity + " get " + (requiredQuantity - chargedQuantity) + " free for " + sku;
    }
}


