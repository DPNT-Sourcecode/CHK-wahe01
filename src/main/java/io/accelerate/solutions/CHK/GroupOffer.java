package io.accelerate.solutions.CHK;
import java.util.*;

public class GroupOffer implements Offer {
    private final List<String> groupSkus;
    private final int groupSize;
    private final int groupPrice;

    public GroupOffer(List<String> groupSkus, int groupSize, int groupPrice) {
        this.groupSkus = groupSkus;
        this.groupSize = groupSize;
        this.groupPrice = groupPrice;
    }

    @Override
    public int apply(Map<String, Integer> quantities, ItemsRepo itemsRepo) {
        // Collect all group items and sort by descending unit price
        List<String> itemsInGroup = new ArrayList<>();
        for (String sku : groupSkus) {
            int count = quantities.getOrDefault(sku, 0);
            for (int i = 0; i < count; i++) {
                itemsInGroup.add(sku);
            }
        }

        // Sort to maximize savings: apply offer to highest-priced items first
        itemsInGroup.sort((a, b) -> {
            int priceA = itemsRepo.getItem(a).getUnitPrice();
            int priceB = itemsRepo.getItem(b).getUnitPrice();
            return Integer.compare(priceB, priceA); // Descending
        });

        int totalDiscount = 0;
        int totalGroups = itemsInGroup.size() / groupSize;

        




    }


}
