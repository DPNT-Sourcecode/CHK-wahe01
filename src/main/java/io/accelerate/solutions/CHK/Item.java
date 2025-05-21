package io.accelerate.solutions.CHK;
import java.util.List;

public class Item {
    private final String sku;
    private final int unitPrice;
    private final List<Offer> offers;

    public Item(String sku, int unitPrice, List<Offer> offers) {
        this.sku = sku;
        this.unitPrice = unitPrice;
        this.offers = offers;
    }

    public String getSku(){
        return sku;
    }

    public int getUnitPrice(){
        return unitPrice;
    }

    public List<Offer> getOffers(){
        return offers;
    }

}
