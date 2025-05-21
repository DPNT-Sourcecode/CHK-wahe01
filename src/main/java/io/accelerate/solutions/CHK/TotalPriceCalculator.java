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

        // Clone for manipulation during offer application
        Map<String, Integer> quantities = new HashMap<>(originalQuantities);
        int totalDiscount = 0;

        // Step 2: Apply Apply all "rewarding" offers first (cross-SKU or self-rewarding)
        for (String sku : quantities.keySet()) {
            Item item = itemsRepo.getItem(sku);
            for (Offer offer : item.getOffers()) {
                if (offer instanceof FreeItemOffer || offer instanceof SelFreeItemOffer) {
                    totalDiscount += offer.apply(quantities, itemsRepo);
                }
            }
        }

        //Apply best MultiPriceOffers (sorted by quantity desc)
        for (String sku : quantities.keySet()) {
            Item item = itemsRepo.getItem(sku);

            List<MultiPriceOffer> multiOffers = new ArrayList<>();
            for (Offer offer : item.getOffers()) {
                if (offer instanceof MultiPriceOffer) {
                    multiOffers.add((MultiPriceOffer) offer);
                }
            }

            multiOffers.sort((o1, o2) ->
                    Integer.compare(o2.getQuantityRequired(), o1.getQuantityRequired()));

            for (MultiPriceOffer offer : multiOffers) {
                totalDiscount += offer.apply(quantities, itemsRepo);
            }
        }


        // Calculate full original price
        int fullPrice = 0;
        for (Map.Entry<String, Integer> entry : originalQuantities.entrySet()) {
            Item item = itemsRepo.getItem(entry.getKey());
            fullPrice += entry.getValue() * item.getUnitPrice();
        }

        return fullPrice - totalDiscount;

    }
/*
    private int estimateDiscountValue(Offer offer, Map<String, Integer> quantities, ItemsRepo itemsRepo) {
        Map<String, Integer> cloned = new HashMap<>(quantities);
        return offer.apply(cloned, itemsRepo);
    }
 */
}

