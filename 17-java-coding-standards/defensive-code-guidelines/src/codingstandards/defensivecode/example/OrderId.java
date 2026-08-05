package codingstandards.defensivecode.example;

import java.util.Objects;

/** AFTER: both overridden together, consistently. */
public class OrderId {
    private final String value;

    public OrderId(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof OrderId other && other.value.equals(this.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
