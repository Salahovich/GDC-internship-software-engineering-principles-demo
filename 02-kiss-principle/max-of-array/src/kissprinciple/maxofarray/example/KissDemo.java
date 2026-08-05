package kissprinciple.maxofarray.example;

import kissprinciple.maxofarray.exercise.GetMinComplex;

/**
 * KISS PRINCIPLE
 * ----------------------------------------------------------------------
 * "Keep It Simple, Stupid": prefer the simplest solution that correctly
 * solves the problem. Extra layers, extra validation, and clever-looking
 * approaches that aren't needed make code harder to read, slower, and
 * more likely to hide bugs — without adding any real value.
 */
public class KissDemo {

    public static void main(String[] args) {
        int[] numbers = {7, 2, 9, 4, 9, 1, 5};

        System.out.println("== BEFORE: sort the whole array just to find the max ==");
        System.out.println("getMaxComplex: " + GetMaxComplex.getMaxComplex(numbers)
                + "  (boxes to a List, validates 4x, sorts O(n log n))");

        System.out.println();
        System.out.println("== AFTER: one pass, one validation, O(n) ==");
        System.out.println("getMax:        " + GetMax.getMax(numbers));

        System.out.println();
        System.out.println("== TODO exercise: see exercise/GetMinComplex.java ==");
        System.out.println("getMinComplex: " + GetMinComplex.getMinComplex(numbers)
                + "  (same over-engineering, just for the minimum)");
    }
}
