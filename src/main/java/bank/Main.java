package bank;

import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory bankSession = SessionFactorySingleton.getInstance();
        AccountsRep accountsRep = new AccountsRep(bankSession);
    }
}
