package codingstandards.modernjava.before;

import java.util.ArrayList;
import java.util.List;

/** BEFORE: a side effect buried inside map(), and the stream's own result thrown away. */
public class StreamsUnclear {
    public static List<String> upperCaseLongNamesUnclear(List<String> names) {
        List<String> result = new ArrayList<>();
        names.stream()
                .map(name -> {
                    String upper = name.toUpperCase();
                    if (upper.length() > 4) {
                        result.add(upper); // surprising: mutating something outside the lambda
                    }
                    return upper;
                })
                .count(); // the actual pipeline result is discarded
        return result;
    }
}
