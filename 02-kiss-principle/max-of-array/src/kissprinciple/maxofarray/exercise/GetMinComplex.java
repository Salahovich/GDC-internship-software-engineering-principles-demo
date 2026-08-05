package kissprinciple.maxofarray.exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * getMinComplex() below has the exact same problem GetMaxComplex had —
 * box into a List, validate repeatedly, sort the whole array — just to
 * read off the FIRST element this time.
 *
 * Task: replace it with a plain single-pass loop, the same way
 * example/GetMax.java simplified example/GetMaxComplex.java. Update the
 * call in KissDemo.main() to use your simplified version.
 */
public class GetMinComplex {

    public static int getMinComplex(int[] numbers) {
        List<Integer> boxed = new ArrayList<>();
        for (int n : numbers) {
            boxed.add(n);
        }
        List<Integer> sorted = boxed.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        return sorted.get(0);
    }
}
