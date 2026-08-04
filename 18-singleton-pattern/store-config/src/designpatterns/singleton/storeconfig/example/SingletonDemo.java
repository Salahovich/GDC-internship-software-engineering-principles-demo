package designpatterns.singleton.storeconfig.example;

/**
 * SINGLETON PATTERN
 * ----------------------------------------------------------------------
 * Guarantees a class has exactly one instance, and gives one global point
 * of access to it. Useful for shared config, caches, connection pools —
 * anything where having two "different" copies would be a bug.
 */
public class SingletonDemo {

    public static void main(String[] args) {
        System.out.println("== BEFORE: StoreConfigNoSingleton — anyone can create their own copy ==");
        StoreConfigNoSingleton configA = new StoreConfigNoSingleton();
        StoreConfigNoSingleton configB = new StoreConfigNoSingleton();
        configA.setDiscountRate(0.25); // checkout module sets a Black Friday discount
        System.out.println("configA discount: " + configA.getDiscountRate());
        System.out.println("configB discount: " + configB.getDiscountRate());
        System.out.println("^ Bug: configB never saw the update. It's a different object.");

        System.out.println();
        System.out.println("== AFTER: StoreConfig.getInstance() — everyone shares the same object ==");
        StoreConfig first = StoreConfig.getInstance();
        StoreConfig second = StoreConfig.getInstance();
        first.setDiscountRate(0.25);
        System.out.println("first discount:  " + first.getDiscountRate());
        System.out.println("second discount: " + second.getDiscountRate());
        System.out.println("same instance? " + (first == second));

        System.out.println();
        System.out.println("== TODO exercise: see exercise/RequestIdGeneratorTodo.java ==");
    }
}
