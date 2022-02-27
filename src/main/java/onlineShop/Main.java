package onlineShop;

import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory onlineShopSession = onlineShop.SessionFactorySingleton.getInstance();
        CustomersRepository customersRepository = new CustomersRepository(onlineShopSession);
    }
}