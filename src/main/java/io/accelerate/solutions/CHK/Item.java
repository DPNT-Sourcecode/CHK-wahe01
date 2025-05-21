package io.accelerate.solutions.CHK;

public class Item {
    private final String sku;
    private final int unitPrice;
    private final SpecialOffer specialOffer;

    public Item(String sku, int unitPrice, SpecialOffer specialOffer) {
        this.sku = sku;
        this.unitPrice = unitPrice;
        this.specialOffer = specialOffer;
    }

    public String getSku(){
        return sku;
    }

    public int getUnitPrice(){
        return unitPrice;
    }

    public SpecialOffer getSpecialOffer(){
        return specialOffer;
    }

}

