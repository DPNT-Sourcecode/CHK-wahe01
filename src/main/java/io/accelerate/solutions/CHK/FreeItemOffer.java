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
        if (!quantities.containsKey(requiredSku)) return 0;
        int qualifyingCount = quantities.get(requiredSku);
        if (requiredQuantity == 0){
            System.out.println("Invalid: requiredQuantity = 0");
            return 0;
        }
        int applicableTimes = qualifyingCount / requiredQuantity;
        if (applicableTimes == 0 || !quantities.containsKey(freeSku)) return 0;

        int freeItems = Math.min(applicableTimes * freeQuantity, quantities.get(freeSku));
        int discount = freeItems * itemsRepo.getItem(freeSku).getUnitPrice();

        //placeholder, may not be required
        quantities.put(freeSku, quantities.get(freeSku) - freeItems);

        return discount;
    }


}
