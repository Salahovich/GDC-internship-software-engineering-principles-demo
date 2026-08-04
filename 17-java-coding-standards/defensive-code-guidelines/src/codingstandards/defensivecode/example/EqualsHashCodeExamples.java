package codingstandards.defensivecode.example;

import java.util.Objects;

/** Equals & hashCode: break the contract and the object disappears inside a HashSet/HashMap. */
public class EqualsHashCodeExamples {

    // BEFORE: equals() overridden, hashCode() is not — the object goes missing in hash-based collections
    public static class OrderIdBroken {
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

    // AFTER: both overridden together, consistently
    public static class OrderId {
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
}
