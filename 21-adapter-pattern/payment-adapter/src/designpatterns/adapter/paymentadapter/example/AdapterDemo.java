package designpatterns.adapter.paymentadapter.example;

import java.util.List;

/**
 * ADAPTER PATTERN
 * ----------------------------------------------------------------------
 * Wraps a class with an incompatible interface so it can be used wherever
 * the expected interface is required, without changing the class being
 * wrapped (often because you don't own it).
 */
public class AdapterDemo {

    public static void main(String[] args) {
        System.out.println("== BEFORE: LegacyPaymentGateway doesn't fit PaymentProcessor ==");
        LegacyPaymentGateway legacyGateway = new LegacyPaymentGateway();
        legacyGateway.submitPaymentInCents(4200);
        System.out.println("^ Different method name, different unit (cents) — checkout code");
        System.out.println("  written against PaymentProcessor can't call this directly.");

        System.out.println();
        System.out.println("== AFTER: checkout code only ever talks to PaymentProcessor ==");
        List<PaymentProcessor> processors = List.of(
                new ModernPaymentGateway(),
                new LegacyPaymentGatewayAdapter(new LegacyPaymentGateway())
        );
        for (PaymentProcessor processor : processors) {
            processor.pay(42.00);
        }
        System.out.println("^ Same pay(42.00) call, whether the real work happens in a modern");
        System.out.println("  gateway or gets translated to cents by the adapter underneath.");

        System.out.println();
        System.out.println("== TODO exercise: see exercise/InventoryLookupAdapterTodo.java ==");
    }
}
