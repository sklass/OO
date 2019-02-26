import java.util.Date;

public class Bank {
    String Name;
    Account[] BankAccounts;
    Customer[] BankCustomers;

    public Bank(){
        BankAccounts = new Account[3];
        BankCustomers = new Customer[3];

        BankCustomers[0] = createNewCustomer("Max","Mustermann","Musterstr. 3", "Berlin",10);
        BankCustomers[1] = createNewCustomer("Heinz","Heinzelmann","Heinzestr. 5", "Heinersdorf",11);
        BankCustomers[2] = createNewCustomer("Karl","Karlmann","Karlestr. 15", "Karlsberg",12);
        BankAccounts[0] = createNewAccount(10,BankCustomers[0].getCustomerID());
        BankAccounts[1] = createNewAccount(11,BankCustomers[1].getCustomerID());
        BankAccounts[2] = createNewAccount(12,BankCustomers[2].getCustomerID());
    }

    private Customer createNewCustomer(String firstname, String lastname, String street,String city, int customerID){
        Customer myNewCustomer = new Customer(firstname,lastname,street,city,customerID);
        return myNewCustomer;
    }

    private Account createNewAccount(int accountID, int customerID){
        Account myNewAccount = new Account(accountID,customerID);
        return myNewAccount;
    }

    public void printAllCustomers(){
        for(Customer customer: BankCustomers){
            printCustomerInfo(customer);
            for(Account account: BankAccounts){
                if(customer.getCustomerID() == account.getCustomerID()) {
                    System.out.println("Kontoinformationen:");
                    printAccountInfo(account);
                }
            }
        }
    }

    private void printCustomerInfo(Customer customer){
        System.out.println();
        System.out.println("Kundennummer: " + customer.getCustomerID());
        System.out.println("Vorname: " + customer.getFirstname());
        System.out.println("nachname: " + customer.getLastname());
        System.out.println("Straße: " + customer.getStreet());
        System.out.println("Ort: " + customer.getCity());
    }

    private void printAccountInfo(Account account) {
        System.out.println("Kontonummer:\t" + account.getAccountID());
        System.out.println("Guthaben:\t" + account.getBalance());

    }
}
