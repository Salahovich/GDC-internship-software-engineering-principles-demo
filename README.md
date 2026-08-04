# Intern Demos

Demos and live exercises for intern training sessions. Java 21, no build
tool — just `javac`/`java`.

## Structure

One numbered folder per session/topic, in the order the sessions were
introduced (e.g. `01-dry-principle/`). Inside each session folder, one
folder per demo:

```
<NN-session>/
  <demo-name>/
    src/.../<DemoName>.java   # one file, one public class, its own main()
    run.sh                    # compiles to ./out and runs it
    README.md                 # what it shows + the 5-minute exercise
```

Each demo is self-contained and runnable on its own — no shared classpath
across demos. The five SOLID demos (12-16) are the one exception: they're
still separate, independently runnable modules, but intentionally reuse
the same `Order`/`OrderItem` (and later `DiscountPolicy`) entity
definitions, redeclared in each file, so the five read as one connected
system rather than five unrelated snippets.

The nine design-pattern demos (17-25) use a different file layout: each
one splits its `src/` package into an `example/` sub-package (the BEFORE
class, the AFTER class, and the runnable `main()`) and an `exercise/`
sub-package (the standalone TODO exercise), so the pattern you're
explaining and the exercise the intern does are never mixed together in
the same folder. Each demo is also deliberately small — 2-3 core classes
in `example/` — so it can be walked through in about 7-10 minutes.

## Running a demo

```bash
cd <NN-session>/<demo-name>
./run.sh
```

(or manually: `javac --release 21 -d out src/.../example/*.java src/.../exercise/*.java && java -cp out <package>.example.<Class>`)

## Sessions

1. [`01-dry-principle/`](01-dry-principle/README.md) — DRY: explanation + pitfall
2. [`02-kiss-principle/`](02-kiss-principle/README.md) — KISS: get max of array
3. [`03-yagni-principle/`](03-yagni-principle/README.md) — YAGNI: extra fields the business doesn't need
4. [`04-separation-of-concerns/`](04-separation-of-concerns/README.md) — SoC: order processing
5. [`05-fail-fast/`](05-fail-fast/README.md) — Fail Fast: discount batch
6. [`06-least-astonishment/`](06-least-astonishment/README.md) — Least Astonishment: shopping cart
7. [`07-tell-dont-ask/`](07-tell-dont-ask/README.md) — Tell, Don't Ask: inventory reservation
8. [`08-law-of-demeter/`](08-law-of-demeter/README.md) — Law of Demeter: shipping label
9. [`09-cohesion-and-coupling/`](09-cohesion-and-coupling/README.md) — Cohesion & Coupling: store operations
10. [`10-composition-over-inheritance/`](10-composition-over-inheritance/README.md) — Composition over Inheritance: bird behaviors
11. [`11-anemic-vs-rich-domain-model/`](11-anemic-vs-rich-domain-model/README.md) — Anemic vs. Rich Domain Model: order domain model
12. [`12-solid-srp/`](12-solid-srp/README.md) — SOLID (S): Single Responsibility — order responsibilities
13. [`13-solid-ocp/`](13-solid-ocp/README.md) — SOLID (O): Open/Closed — order discounts
14. [`14-solid-lsp/`](14-solid-lsp/README.md) — SOLID (L): Liskov Substitution — discount policies
15. [`15-solid-isp/`](15-solid-isp/README.md) — SOLID (I): Interface Segregation — order processor interfaces
16. [`16-solid-dip/`](16-solid-dip/README.md) — SOLID (D): Dependency Inversion — order notifications
17. [`17-singleton-pattern/`](17-singleton-pattern/README.md) — Design Pattern: Singleton — store config
18. [`18-builder-pattern/`](18-builder-pattern/README.md) — Design Pattern: Builder — order builder
19. [`19-prototype-pattern/`](19-prototype-pattern/README.md) — Design Pattern: Prototype — order template
20. [`20-adapter-pattern/`](20-adapter-pattern/README.md) — Design Pattern: Adapter — payment adapter
21. [`21-facade-pattern/`](21-facade-pattern/README.md) — Design Pattern: Facade — checkout facade
22. [`22-proxy-pattern/`](22-proxy-pattern/README.md) — Design Pattern: Proxy — inventory proxy
23. [`23-chain-of-responsibility-pattern/`](23-chain-of-responsibility-pattern/README.md) — Design Pattern: Chain of Responsibility — order validation chain
24. [`24-observer-pattern/`](24-observer-pattern/README.md) — Design Pattern: Observer — order confirmation events
25. [`25-visitor-pattern/`](25-visitor-pattern/README.md) — Design Pattern: Visitor — order item pricing
26. [`26-java-coding-standards/`](26-java-coding-standards/README.md) — Java Coding Standards: readability, defensive code, modern Java idioms (3 demos)
