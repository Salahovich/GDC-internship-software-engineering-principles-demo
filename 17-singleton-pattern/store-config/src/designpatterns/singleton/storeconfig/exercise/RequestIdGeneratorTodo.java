package designpatterns.singleton.storeconfig.exercise;

/**
 * TODO EXERCISE
 * ----------------------------------------------------------------------
 * The order service needs a request ID generator: every call to
 * nextId() should return a new, increasing number (1, 2, 3, ...), and the
 * whole app must share one counter — two different counters would hand
 * out duplicate IDs.
 *
 * Task: write a class `RequestIdGenerator` (in its own file, next to this
 * one) as a Singleton, following the same shape as StoreConfig in the
 * example package:
 *   - a private static final instance
 *   - a private constructor
 *   - a public static getInstance()
 *   - an instance field `int counter`, and a method `int nextId()` that
 *     increments and returns it
 *
 * Then, in a small main() (or by adding to SingletonDemo), call
 * RequestIdGenerator.getInstance().nextId() a few times from "different"
 * parts of the code and confirm the numbers keep increasing instead of
 * resetting.
 */
public class RequestIdGeneratorTodo {
}
