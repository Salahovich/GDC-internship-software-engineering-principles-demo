package codingstandards.modernjava.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Streams & loops: readability decides, not fashion. */
public class StreamsExamples {

    // BEFORE: a side effect buried inside map(), and the stream's own result thrown away
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

    // AFTER: linear — no side effects, the pipeline IS the result
    public static List<String> upperCaseLongNames(List<String> names) {
        return names.stream()
                .map(String::toUpperCase)
                .filter(name -> name.length() > 4)
                .collect(Collectors.toList());
    }
}
