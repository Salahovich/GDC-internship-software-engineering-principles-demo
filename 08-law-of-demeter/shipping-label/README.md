# Law of Demeter — Shipping Label

**File:** `src/lawofdemeter/shippinglabel/LawOfDemeterDemo.java`

## What it shows

Reading a customer's shipping city, two ways.

1. **BEFORE (`OrderTrainWreck` / `CustomerTrainWreck`)** — callers reach
   through `order.getCustomer().getAddress().getCity()`, a "train wreck"
   duplicated across two call sites (`printShippingLabelTrainWreck` and
   `isRegionalDiscountEligibleTrainWreck`). When a customer has no
   address on file, the chain crashes with a `NullPointerException`
   right at the caller — nowhere near where "address is optional" was
   actually decided.
2. **AFTER (`Order` / `Customer`)** — each object only talks to its
   immediate collaborator: `Order.getShippingCity()` asks `Customer`,
   `Customer.getCity()` asks `Address`. Callers never see `Address` at
   all, and the "no address on file" case is handled once, inside
   `Customer`.

Run it:

```bash
./run.sh
```

## Exercise (~5 minutes)

`printManagerIdTrainWreck` (bottom of the file, marked `TODO`) reaches
through `order.getWarehouse().getManager().getId()` — the same train
wreck, one hop longer.

**Task:** add `Warehouse.getManagerId()` (delegates to
`manager.getId()`) and `OrderWithWarehouse.getWarehouseManagerId()`
(delegates to `warehouse.getManagerId()`). Update `main` to call
`order.getWarehouseManagerId()` instead of reaching through the chain,
then delete `printManagerIdTrainWreck`.

<details>
<summary>Solution</summary>

```java
static class Warehouse {
    private final Employee manager;

    Warehouse(Employee manager) {
        this.manager = manager;
    }

    String getManagerId() {
        return manager.getId();
    }
}

static class OrderWithWarehouse {
    private final Warehouse warehouse;

    OrderWithWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    String getWarehouseManagerId() {
        return warehouse.getManagerId();
    }
}

// in main():
System.out.println("Manager id: " + warehouseOrder.getWarehouseManagerId());
```

</details>
