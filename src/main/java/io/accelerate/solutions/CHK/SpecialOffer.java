package io.accelerate.solutions.CHK;

public class SpecialOffer {
    private final int quantityRequired;
    private final int offerPrice;

    public SpecialOffer(int quantityRequired, int offerPrice) {
        this.quantityRequired = quantityRequired;
        this.offerPrice = offerPrice;
    }

    public int getQuantityRequired(){
        return quantityRequired;
    }

    public int getOfferPrice(){
        return offerPrice;
    }

}

