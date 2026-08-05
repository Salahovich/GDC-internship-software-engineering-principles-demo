package codingstandards.defensivecode.example;

/**
 * BEFORE: equals() overridden, hashCode() is not — the object goes
 * missing in hash-based collections.
 */
public class OrderIdBroken {
    private final String value;

    public OrderIdBroken(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof OrderIdBroken other && other.value.equals(this.value);
    }
    // no hashCode() override — falls back to identity hash, so two "equal" instances
    // land in different hash buckets.
}
