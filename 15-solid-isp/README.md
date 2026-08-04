# SOLID (I) — Interface Segregation — Session Demo

| Demo | Run |
|---|---|
| [`order-processor-interfaces/`](order-processor-interfaces/README.md) | `cd order-processor-interfaces && ./run.sh` |

Fourth of the five SOLID demos (12-16), continuing the same
`Order`/`OrderItem` entities and showing the ISP side of the trap LSP
covered in [`14-solid-lsp/`](../14-solid-lsp/README.md): a fat interface
forcing implementers to fake methods they can't support.

One Java file with its own `main()`, prints a before/after comparison to
the console, and ends with a **~5 minute TODO exercise** for interns to
complete live. See the demo's README for the exercise instructions and a
solution hint.

Requires JDK 21+.
