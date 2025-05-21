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

        //Apply best offers
        List<String> sortedSkus = new ArrayList<>(quantities.keySet());
        sortedSkus.sort(String::compareTo);

        for (String sku : sortedSkus) {
            Item item = itemsRepo.getItem(sku);
            List<Offer> offers = item.getOffers();

            // Separate MultiPriceOffers and other offers
            List<MultiPriceOffer> multiOffers = new ArrayList<>();
            List<Offer> otherOffers = new ArrayList<>();

            for (Offer offer : offers) {
                if (offer instanceof MultiPriceOffer) {
                    multiOffers.add((MultiPriceOffer) offer);
                } else {
                    otherOffers.add(offer);
                }
            }

            // Sort multi-offers by quantity descending (larger bundles first)
            multiOffers.sort((o1, o2) ->
                    Integer.compare(o2.getQuantityRequired(), o1.getQuantityRequired()));

            // Apply multi-offers greedily
            for (MultiPriceOffer offer : multiOffers) {
                totalDiscount += offer.apply(quantities, itemsRepo);
            }

            // Apply other types of offers (e.g., free item offers)
            for (Offer offer : otherOffers) {
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

    private int estimateDiscountValue(Offer offer, Map<String, Integer> quantities, ItemsRepo itemsRepo) {
        Map<String, Integer> cloned = new HashMap<>(quantities);
        return offer.apply(cloned, itemsRepo);
    }
}
