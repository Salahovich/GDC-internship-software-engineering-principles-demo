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
    src/.../before/    # BEFORE class(es) only
    src/.../example/   # AFTER class(es), shared entities, the runnable main()
    src/.../exercise/  # the standalone TODO exercise
    run.sh             # compiles to ./out and runs it
    README.md          # what it shows + the 5-minute exercise
```

Every demo splits its `src/` package into an `exercise/` sub-package
(the TODO exercise) and, for the material being presented, keeps the
BEFORE and AFTER code in separate top-level files so you can diff them
side by side instead of scrolling one file looking for both halves.

Sessions 1-17 go a step further and put BEFORE and AFTER in separate
*packages*: `before/` holds only the BEFORE class(es); `example/` holds
the AFTER class(es), any shared entities the BEFORE/AFTER code both
need, and the runnable `main()`. Sessions 18-26 (the design patterns)
keep BEFORE and AFTER as separate files within one `example/` package.

Each demo is self-contained and runnable on its own — no shared classpath
across demos. The five SOLID demos (12-16) are the one exception: they're
still separate, independently runnable modules, but intentionally reuse
the same `Order`/`OrderItem` (and later `DiscountPolicy`) entity
definitions, redeclared in each one, so the five read as one connected
system rather than five unrelated snippets.

## Running a demo

```bash
cd <NN-session>/<demo-name>
./run.sh
```

(or manually, for sessions 1-17: `javac --release 21 -d out src/.../before/*.java src/.../example/*.java src/.../exercise/*.java && java -cp out <package>.example.<Class>`;
for sessions 18-26: `javac --release 21 -d out src/.../example/*.java src/.../exercise/*.java && java -cp out <package>.example.<Class>`)

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
17. [`17-java-coding-standards/`](17-java-coding-standards/README.md) — Java Coding Standards: readability, defensive code, modern Java idioms (3 demos)
18. [`18-singleton-pattern/`](18-singleton-pattern/README.md) — Design Pattern: Singleton — store config
19. [`19-builder-pattern/`](19-builder-pattern/README.md) — Design Pattern: Builder — order builder
20. [`20-prototype-pattern/`](20-prototype-pattern/README.md) — Design Pattern: Prototype — order template
21. [`21-adapter-pattern/`](21-adapter-pattern/README.md) — Design Pattern: Adapter — payment adapter
22. [`22-facade-pattern/`](22-facade-pattern/README.md) — Design Pattern: Facade — checkout facade
23. [`23-proxy-pattern/`](23-proxy-pattern/README.md) — Design Pattern: Proxy — inventory proxy
24. [`24-chain-of-responsibility-pattern/`](24-chain-of-responsibility-pattern/README.md) — Design Pattern: Chain of Responsibility — order validation chain
25. [`25-observer-pattern/`](25-observer-pattern/README.md) — Design Pattern: Observer — order confirmation events
26. [`26-visitor-pattern/`](26-visitor-pattern/README.md) — Design Pattern: Visitor — order item pricing
