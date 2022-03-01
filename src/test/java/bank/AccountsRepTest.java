package bank;

import onlineShop.OnlineShopUser;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountsRepTest {
    private static SessionFactory sessionFactory;
    private static AccountsRep accountsRep;
    private static President president;
    private static Bank bank;
    private static Branch branch;
    private static Clerk clerk;
    private static Client client;


    @BeforeAll
    static void initialize() {
        var registry = new StandardServiceRegistryBuilder()
                .configure("hibernate-bank-test.cfg.xml") //goes and fetches configurations from hibernate-bank-test.cfg.xml
                .build();

        //registry is useful for creating session factory
        sessionFactory = new MetadataSources(registry)
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(Branch.class)
                .addAnnotatedClass(Bank.class)
                .addAnnotatedClass(Clerk.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(President.class)
                .addAnnotatedClass(OnlineShopUser.class)
                .buildMetadata()
                .buildSessionFactory();

        accountsRep = new AccountsRep(sessionFactory);

        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();

        bank = new Bank(0,"melli",null,null);
        session.save(bank);

        branch = new Branch(0,"enghelab",bank,null,null,null);
        session.save(branch);

        president = new President(0,"president","lastname","pusername","ppassword",branch,100000d);
        session.save(president);

        clerk = new Clerk(0,"clerk","clerkLastname","clerk","cpassword",branch,president,20000d);
        session.save(clerk);

        client = new Client(0,"naeim","rahimzade","nae","nae123");
        session.save(client);

        bank.setBranches(List.of(branch));
        session.update(bank);

        branch.setPresident(president);
        branch.setClerks(new ArrayList<>(List.of(clerk)));
        session.update(branch);

        transaction.commit();

        session.close();
    }

    @Test
    void insTest() {
        //Arrange
        Account account = new Account(0,"987654321",client,bank,branch,25000d);

        //Act
        Integer accountId = accountsRep.insert(account);

        //Assert
        assertNotNull(accountId);
        assertNotEquals(0,account.getId());
        assertEquals(accountId,account.getId());
    }

    @Test
    void readByIdTest() {
        //Arrange
        Account madeAccount = new Account(0,"987654321",client,bank,branch,25000d);
        Integer accountId = accountsRep.insert(madeAccount);

        //Act
        Account accountToFind = accountsRep.read(accountId);

        //Assert
        assertNotNull(accountToFind);
        assertEquals(accountToFind.getId(),accountId);
        assertEquals(accountToFind.getClient().getFirstname(),client.getFirstname());
        assertEquals(accountToFind.getClient().getUsername(),client.getUsername());
    }

    @Test
    void readAllTest() {
        //Arrange
        Account account1 = new Account(0,"987654321",client,bank,branch,25000d);
        Account account2 = new Account(0,"987654321",client,bank,branch,25000d);
        Account account3 = new Account(0,"987654321",client,bank,branch,25000d);
        accountsRep.insert(account1);
        accountsRep.insert(account2);
        accountsRep.insert(account3);

        //Act
        List<Account> accounts = accountsRep.readAll();

        //Assert
        assertNotNull(accounts);
        assertEquals(3,accounts.size());
    }

    @Test
    void readAllByClientTest() {
        //Arrange
        Account account1 = new Account(0,"987654321",client,bank,branch,25000d);
        Account account2 = new Account(0,"987654321",client,bank,branch,25000d);
        Account account3 = new Account(0,"987654321",client,bank,branch,25000d);
        accountsRep.insert(account1);
        accountsRep.insert(account2);
        accountsRep.insert(account3);

        //Act
        List<Account> accounts = accountsRep.readAllByClient(client);

        //Assert
        assertNotNull(accounts);
        assertEquals(3,accounts.size());
    }

    @Test
    void readAccountsByClientInBranch() {
        //Arrange
        Account account1 = new Account(0,"987654321",client,bank,branch,25000d);
        Account account2 = new Account(0,"987654321",client,bank,branch,25000d);
        Account account3 = new Account(0,"987654321",client,bank,branch,25000d);
        accountsRep.insert(account1);
        accountsRep.insert(account2);
        accountsRep.insert(account3);

        //Act
        List<Account> accounts1 = accountsRep.readAccountsByClientInBranch(client,branch);
        List<Account> accounts2 = accountsRep.readAccountsByClientInBranch(client,branch);
        List<Account> accounts3 = accountsRep.readAccountsByClientInBranch(client,branch);

        //Assert
        assertNotNull(account1);
        assertNotNull(account2);
        assertNotNull(account3);
        assertEquals(3,accounts1.size());
        assertEquals(3,accounts2.size());
        assertEquals(3,accounts3.size());
    }

    @Test
    void updateTest() {
        //Arrange
        Account account = new Account(0,"987654321",client,bank,branch,25000d);
        accountsRep.insert(account);

        //Act
        account.setAccountNumber("123456789");
        Integer updateId = accountsRep.update(account);

        //Assert
        assertNotNull(updateId);
        assertEquals(accountsRep.read(updateId).getAccountNumber(),account.getAccountNumber());
        assertEquals(accountsRep.read(updateId).getAccountNumber(),"123456789");
    }

    @Test
    void deleteTest() {
        //Arrange
        Account account = new Account(0,"987654321",client,bank,branch,25000d);
        accountsRep.insert(account);

        //Act
        accountsRep.delete(account);

        //Assert
        assertNull(accountsRep.read(account.getId()));
    }

    @AfterEach
    void cleanAccounts() {
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        session.createQuery("delete from Account").executeUpdate();
        transaction.commit();
        session.close();
    }

    @AfterAll
    static void wipeOtherTables() {
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        session.delete(president);
        session.delete(bank);
        session.delete(branch);
        session.delete(clerk);
        session.delete(client);
        transaction.commit();
        session.close();
    }
}