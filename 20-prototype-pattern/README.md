# Design Pattern: Prototype — Session Demo

| Demo | Run |
|---|---|
| [`order-template/`](order-template/README.md) | `cd order-template && ./run.sh` |

Third of nine design-pattern demos (18-26). Follows Refactoring Guru's
Java Prototype example: an abstract `Order` with a copy constructor
(not `Cloneable`/`clone()`), and two concrete templates —
`StandardOrder` and `GiftOrder` — cloned polymorphically through one
`List<Order>`, so client code never branches on concrete type. See the
session-level note in `18-singleton-pattern/README.md` for the
`example/`/`exercise/` package layout used across all nine — this demo
adds a third package, `before/`, so the naive hand-copied version sits
apart from the official implementation instead of both living in
`example/`.

Requires JDK 21+.
