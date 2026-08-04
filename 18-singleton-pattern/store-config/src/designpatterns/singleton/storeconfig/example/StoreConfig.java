package designpatterns.singleton.storeconfig.example;

/** AFTER: Singleton. One instance for the whole app, reached through getInstance(). */
public class StoreConfig {
    private static final StoreConfig INSTANCE = new StoreConfig();

    private double discountRate = 0.10;

    private StoreConfig() {
        // private: nobody outside this class can call `new StoreConfig()`
    }

    public static StoreConfig getInstance() {
        return INSTANCE;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
}
