package codingstandards.readability.exercise;

/**
 * TODO EXERCISE
 * ----------------------------------------------------------------------
 * The other half of "Nulls & Magic Numbers": prefer never producing a
 * null in the first place. Given:
 *
 *   public class AdminDirectory {
 *       public static List<String> findAdminEmailsBroken(Map<String, List<String>> teamAdmins, String team) {
 *           return teamAdmins.get(team); // returns null if the team has no entry
 *       }
 *   }
 *
 * Calling code that does a for-each over the result throws a
 * NullPointerException whenever a team has no admins configured.
 *
 * Task: write `AdminDirectory` (in its own file, next to this one) with
 * a `findAdminEmails(Map<String, List<String>> teamAdmins, String team)`
 * method that returns `Collections.emptyList()` instead of null when the
 * team isn't found, so callers can safely for-each the result without an
 * extra null check.
 *
 * Then, in a small main(), call it with a team that isn't in the map and
 * confirm you get back an empty list, not a NullPointerException.
 */
public class NullSafetyTodo {
}
