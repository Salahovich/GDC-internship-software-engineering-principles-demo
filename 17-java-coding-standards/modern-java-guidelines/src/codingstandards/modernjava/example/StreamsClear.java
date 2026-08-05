package codingstandards.modernjava.example;

import java.util.List;
import java.util.stream.Collectors;

/** AFTER: linear — no side effects, the pipeline IS the result. */
public class StreamsClear {
    public static List<String> upperCaseLongNames(List<String> names) {
        return names.stream()
                .map(String::toUpperCase)
                .filter(name -> name.length() > 4)
                .collect(Collectors.toList());
    }
}
