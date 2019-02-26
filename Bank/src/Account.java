public class Account {
    private int accountID;
    private int balance;
    private int customerID;

    public Account(int accountID, int customerID){
        this.accountID = accountID;
        this.customerID = customerID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getBalance() {
        return balance;
    }

    public int getCustomerID() {
        return customerID;
    }
}
