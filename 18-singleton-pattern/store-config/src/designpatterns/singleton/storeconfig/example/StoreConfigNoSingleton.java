package designpatterns.singleton.storeconfig.example;

/** BEFORE: a plain class. Nothing stops two "different" configs from existing at once. */
public class StoreConfigNoSingleton {
    private double discountRate = 0.10;

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
}
