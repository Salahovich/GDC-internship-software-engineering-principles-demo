package solidisp.orderprocessorinterfaces.exercise;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * CustomerAccountManagerFat below has the same problem OrderProcessorFat
 * had: SupportDashboardView only wants to look up order history, but is
 * forced to implement updateProfile() and deleteAccount() too.
 *
 * Task:
 *   a) Split CustomerAccountManagerFat into ProfileEditable
 *      (updateProfile), OrderHistoryViewable (viewOrderHistory), and
 *      AccountDeletable (deleteAccount) — each its own interface.
 *   b) Make a new ReadOnlySupportDashboard implement only
 *      OrderHistoryViewable.
 *   c) Update IspDemo.main() to use it, then delete this class and the
 *      CustomerAccountManagerFat interface below.
 */
interface CustomerAccountManagerFat {
    void updateProfile(String customerEmail, String newName);
    String viewOrderHistory(String customerEmail);
    void deleteAccount(String customerEmail);
}

public class SupportDashboardView implements CustomerAccountManagerFat {

    public void updateProfile(String customerEmail, String newName) {
        throw new UnsupportedOperationException("support dashboard is read-only");
    }

    public String viewOrderHistory(String customerEmail) {
        return "3 past orders for " + customerEmail;
    }

    public void deleteAccount(String customerEmail) {
        throw new UnsupportedOperationException("support dashboard is read-only");
    }
}
