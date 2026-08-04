# Design Pattern: Adapter — Session Demo

| Demo | Run |
|---|---|
| [`payment-adapter/`](payment-adapter/README.md) | `cd payment-adapter && ./run.sh` |

Fourth of nine design-pattern demos (18-26), and the first of the three
Structural patterns. A small, standalone example: a legacy payment
gateway with an incompatible method gets wrapped so it fits the
`PaymentProcessor` interface checkout code expects. See the session-level
note in `18-singleton-pattern/README.md` for the `example/`/`exercise/`
package layout used across all nine.

Requires JDK 21+.
