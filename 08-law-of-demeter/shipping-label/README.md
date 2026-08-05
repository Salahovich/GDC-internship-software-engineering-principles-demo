# Law of Demeter — Shipping Label

**Files:** `src/lawofdemeter/shippinglabel/`

| Package | File | Role |
|---|---|---|
| `example/` | `Address.java` | Shared entity |
| `example/` | `TrainWreck.java` | BEFORE — reaches through Order -> Customer -> Address |
| `example/` | `DemeterCompliant.java` | AFTER — each object talks to its immediate neighbor |
| `example/` | `LawOfDemeterDemo.java` | `main()` — runs everything |
| `exercise/` | `WarehouseManagerTrainWreck.java` | TODO exercise — given train-wreck chain |

## What it shows

Reading a customer's shipping city, two ways.

1. **BEFORE (`TrainWreck.OrderTrainWreck` / `CustomerTrainWreck`)** —
   callers reach through `order.getCustomer().getAddress().getCity()`,
   a "train wreck" duplicated across two call sites
   (`printShippingLabelTrainWreck` and
   `isRegionalDiscountEligibleTrainWreck`). When a customer has no
   address on file, the chain crashes with a `NullPointerException`
   right at the caller — nowhere near where "address is optional" was
   actually decided.
2. **AFTER (`DemeterCompliant.Order` / `Customer`)** — each object only
   talks to its immediate collaborator: `Order.getShippingCity()` asks
   `Customer`, `Customer.getCity()` asks `Address`. Callers never see
   `Address` at all, and the "no address on file" case is handled once,
   inside `Customer`.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`WarehouseManagerTrainWreck.printManagerIdTrainWreck` (see
`exercise/WarehouseManagerTrainWreck.java`) reaches through
`order.getWarehouse().getManager().getId()` — the same train wreck, one
hop longer.

**Task:** add `Warehouse.getManagerId()` (delegates to
`manager.getId()`) and `OrderWithWarehouse.getWarehouseManagerId()`
(delegates to `warehouse.getManagerId()`). Update
`LawOfDemeterDemo.main` to call `order.getWarehouseManagerId()` instead
of reaching through the chain, then delete `printManagerIdTrainWreck`.

<details>
<summary>Solution</summary>

```java
public String getManagerId() {
    return manager.getId();
}

// in OrderWithWarehouse:
public String getWarehouseManagerId() {
    return warehouse.getManagerId();
}

// in main():
System.out.println("Manager id: " + warehouseOrder.getWarehouseManagerId());
```

</details>
