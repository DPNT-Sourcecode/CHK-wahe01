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

        // Count occurrences of each SKU
        Map<String, Integer> skuCount = new HashMap<>();
        for (char c : skus.toCharArray()) {
            String sku = String.valueOf(c);
            if (!itemsRepo.isSkuValid(sku)) {
                System.out.println("Unknown sku found!");
                return -1; // Unknown SKU
            }
            skuCount.put(sku, skuCount.getOrDefault(sku, 0) + 1);
        }

        // Compute total price
        int total = 0;
        for (Map.Entry<String, Integer> entry : skuCount.entrySet()) {
            Item item = itemRepository.getItem(entry.getKey());
            int quantity = entry.getValue();
            SpecialOffer offer = item.getSpecialOffer();

            if (offer != null) {
                int offerQty = offer.getQuantityRequired();
                int offerPrice = offer.getOfferPrice();
                total += (quantity / offerQty) * offerPrice;
                total += (quantity % offerQty) * item.getUnitPrice();
            } else {
                total += quantity * item.getUnitPrice();
            }
        }

        return total;


    }
}



