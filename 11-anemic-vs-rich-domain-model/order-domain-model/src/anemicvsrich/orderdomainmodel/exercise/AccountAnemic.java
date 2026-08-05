package anemicvsrich.orderdomainmodel.exercise;

/**
 * TODO EXERCISE (~5 minutes)
 * ----------------------------------------------------------------------
 * AccountAnemic below has the same problem OrderAnemic had:
 * AccountServiceAnemic owns the "can't withdraw more than the balance"
 * rule, but AccountAnemic's own setBalance() lets any other code bypass
 * it.
 *
 * Task:
 *   a) Create a rich Account class (in its own file) with a private
 *      balance field and a withdraw(double amount) method that checks
 *      the balance and throws IllegalStateException if amount is too
 *      large — no public setBalance().
 *   b) Update AnemicVsRichDemo.main() to build an Account and call
 *      account.withdraw(amount) directly instead of
 *      AccountServiceAnemic.withdraw(account, amount).
 *   c) Delete this class once nothing calls it.
 */
public class AccountAnemic {
    private double balance;

    public AccountAnemic(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance; // no validation — it's just a bag of fields
    }

    public static class AccountServiceAnemic {
        public static void withdraw(AccountAnemic account, double amount) {
            if (amount > account.getBalance()) {
                throw new IllegalStateException("insufficient balance");
            }
            account.setBalance(account.getBalance() - amount);
        }
    }
}
