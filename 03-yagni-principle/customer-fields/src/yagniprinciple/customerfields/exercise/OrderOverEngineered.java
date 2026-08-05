package yagniprinciple.customerfields.exercise;

import java.util.List;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * OrderOverEngineered below has the same problem: three fields
 * ("estimatedCarbonFootprint", "vipPriorityFlag", "giftWrapNoteHistory")
 * were added speculatively and are never read by calculateTotal() — the
 * only method that exists today.
 *
 * Task:
 *   a) Create a trimmed-down `Order` (a record works well, in its own
 *      file) with only the fields calculateTotal actually uses:
 *      itemPrices and discountPercent.
 *   b) Write a calculateTotal for your new Order type and update the
 *      call in YagniDemo.main().
 *   c) Delete this class once nothing calls it.
 *
 * Goal: don't design for hypothetical future requirements — add a field
 * when a real feature needs it, not before.
 */
public class OrderOverEngineered {
    public final List<Double> itemPrices;
    public final double discountPercent;

    // Speculative — nothing in the codebase reads these today.
    public final double estimatedCarbonFootprint;
    public final boolean vipPriorityFlag;
    public final List<String> giftWrapNoteHistory;

    public OrderOverEngineered(List<Double> itemPrices, double discountPercent,
                                double estimatedCarbonFootprint, boolean vipPriorityFlag,
                                List<String> giftWrapNoteHistory) {
        this.itemPrices = itemPrices;
        this.discountPercent = discountPercent;
        this.estimatedCarbonFootprint = estimatedCarbonFootprint;
        this.vipPriorityFlag = vipPriorityFlag;
        this.giftWrapNoteHistory = giftWrapNoteHistory;
    }

    public double calculateTotal() {
        double subtotal = itemPrices.stream().mapToDouble(Double::doubleValue).sum();
        return subtotal * (1 - discountPercent / 100);
    }
}
