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

            // Sort offers by estimated value (highest discount first)
            List<Offer> sortedOffers = new ArrayList<>(offers);
            sortedOffers.sort((o1, o2) -> {
                int discount1 = estimateDiscountValue(o1, quantities, itemsRepo);
                int discount2 = estimateDiscountValue(o2, quantities, itemsRepo);
                return Integer.compare(discount2, discount1); // Descending
            });

            for (Offer offer : sortedOffers) {
                int discount = offer.apply(quantities, itemsRepo);
                totalDiscount += discount;
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

