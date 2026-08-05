package codingstandards.readability.before;

/** BEFORE: what is "1"? readers have to go find out, or guess. */
public class MagicNumberBefore {
    public static boolean isClearedMagicNumber(int status) {
        return status == 1;
    }
}
