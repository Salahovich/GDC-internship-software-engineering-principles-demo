package kissprinciple.maxofarray.example;

/** AFTER: KISS applied. One validation, one pass, O(n) — readable in five seconds. */
public class GetMax {

    public static int getMax(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("numbers must not be null or empty");
        }
        int max = numbers[0];
        for (int n : numbers) {
            if (n > max) max = n;
        }
        return max;
    }
}
