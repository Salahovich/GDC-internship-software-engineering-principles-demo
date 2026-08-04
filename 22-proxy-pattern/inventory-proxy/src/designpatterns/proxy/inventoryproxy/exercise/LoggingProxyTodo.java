package designpatterns.proxy.inventoryproxy.exercise;

/**
 * TODO EXERCISE
 * ----------------------------------------------------------------------
 * Ops wants every order save logged with a timestamp, without changing
 * RealOrderRepository itself.
 *
 * Task: write `LoggingOrderSaverProxy implements OrderSaver` (in its own
 * file, next to this one) that wraps a `RealOrderRepository` and, in
 * save(orderId):
 *   1. prints a line like "[LoggingProxy] Saving order <id> at <time>"
 *      (System.currentTimeMillis() is fine for "time")
 *   2. delegates to the wrapped RealOrderRepository.save(orderId)
 *
 * Then, in a small main(), save an order through the proxy and confirm
 * you see both the log line and RealOrderRepository's own output.
 */
public class LoggingProxyTodo {
}
