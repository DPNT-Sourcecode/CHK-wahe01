package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

public class CheckoutSolution {
    private TotalPriceCalculator totalPriceCalculator;

    public CheckoutSolution() {
        ItemsRepo itemsRepo = new ItemsRepo();
        this.totalPriceCalculator = new TotalPriceCalculator(itemsRepo);
    }

    public Integer checkout(String skus) {
        return totalPriceCalculator.calculateTotal(skus);
    }
}
