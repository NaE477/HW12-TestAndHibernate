package bank;

import onlineShop.Customer;
import onlineShop.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class SessionFactorySingleton {
    private SessionFactorySingleton() {}

    private static class Holder {
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure("hibernate-bank.cfg.xml") //goes and fetches configurations from hibernate-bank.cfg.xml
                    .build();

            //registry is useful for creating session factory
            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(Account.class)
                    .addAnnotatedClass(Branch.class)
                    .addAnnotatedClass(Bank.class)
                    .addAnnotatedClass(Clerk.class)
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(President.class)
                    .addAnnotatedClass(User.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        return Holder.INSTANCE;
    }
}
