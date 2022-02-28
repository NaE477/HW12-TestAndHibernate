package onlineShop;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomersRepositoryTest {
    private static SessionFactory sessionFactory;
    private static CustomersRepository customersRepository;

    @BeforeAll
    private static void initialize() {
        var registry = new StandardServiceRegistryBuilder()
                .configure("hibernate-onlineshop-test.cfg.xml") //goes and fetches configurations from hibernate.cfg.xml
                .build();

        //registry is useful for creating session factory
        sessionFactory = new MetadataSources(registry)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(OnlineShopUser.class)
                .buildMetadata()
                .buildSessionFactory();

        customersRepository = new CustomersRepository(sessionFactory);
    }

    @Test
    void insTest() {
        //Arrange
        Customer customer = new Customer(0,"naeim","rahimzade","nae","rae","nae@rae.com","anzali,mala,iman 3,misagh,unit 7",20000d);

        //Act
        Integer newCustomerId = customersRepository.insert(customer);

        //Assert
        assertNotEquals(0,customer.getId());
        assertNotNull(newCustomerId);
    }

    @Test
    void readIdTest() {
        //Arrange
        Customer customer = new Customer(0,"naeim","rahimzade","nae","rae","nae@rae.com","anzali,mala,iman 3,misagh,unit 7",20000d);
        Integer newCustomerId = customersRepository.insert(customer);

        //Act
        Customer customerToFind = customersRepository.read(newCustomerId);

        //Assert
        assertNotNull(customerToFind);
        assertNotNull(newCustomerId);
        assertEquals(newCustomerId,customer.getId());
        assertEquals(newCustomerId,customerToFind.getId());
    }

    @Test
    void readByUsernameTest() {
        //Arrange
        Customer customer = new Customer(0,"naeim","rahimzade","nae","rae","nae@rae.com","anzali,mala,iman 3,misagh,unit 7",20000d);
        Integer newCustomerId = customersRepository.insert(customer);

        //Act
        Customer customerToFind = customersRepository.read(customer.getUsername());

        //Assert
        assertNotNull(customerToFind);
        assertEquals(newCustomerId,customerToFind.getId());
        assertEquals(newCustomerId,customer.getId());
    }

    @Test
    void readByEmailTest() {
        //Arrange
        Customer customer = new Customer(0,"naeim","rahimzade","nae","rae","nae@rae.com","anzali,mala,iman 3,misagh,unit 7",20000d);
        Integer newCustomerId = customersRepository.insert(customer);

        //Act
        Customer customerToFind = customersRepository.readByEmail(customer.getEmailAddress());

        //Assert
        assertNotNull(customerToFind);
        assertEquals(newCustomerId,customerToFind.getId());
        assertEquals(newCustomerId,customer.getId());
    }

    @Test
    void readAllTest() {
        //Arrange
        Customer customer1 = new Customer(0,"naeim","rahimzade","nae","rae","nae@rae.com","anzali,mala,iman 3,misagh,unit 7",20000d);
        Customer customer2 = new Customer(0,"ali","hoseini","ali","aa123","ali@ali.com","anzali,ghazian,takhti",25000d);
        Customer customer3 = new Customer(0,"arad","mohammadi","arad","arad123","arad@arad.com","anzali,anzali,seyaran",40000d);
        customersRepository.insert(customer1);
        customersRepository.insert(customer2);
        customersRepository.insert(customer3);

        //Act
        List<Customer> customers = customersRepository.readAll();

        //Assert
        assertEquals(3,customers.size());
    }

    @Test
    void updateTest() {
        //Arrange
        Customer customer = new Customer(0,"naeim","rahimzade","nae","rae","nae@rae.com","anzali,mala,iman 3,misagh,unit 7",20000d);
        customersRepository.insert(customer);
        customer.setFirstName("editedFirstname");
        customer.setLastName("editedLastname");
        customer.setUsername("editedUsername");
        customer.setPassword("editedPassword");
        customer.setAddress("editedAddress");
        customer.setEmailAddress("editedEmail");
        customer.setBalance(1000000000d);

        //Act
        Integer updateId = customersRepository.update(customer);

        //Assert
        assertNotNull(updateId);
        assertEquals("editedFirstname",customer.getFirstName());
        assertEquals("editedLastname",customer.getLastName());
        assertEquals("editedUsername",customer.getUsername());
        assertEquals("editedPassword",customer.getPassword());
        assertEquals("editedAddress",customer.getAddress());
        assertEquals("editedEmail",customer.getEmailAddress());
    }

    @Test
    void deleteByIdTest() {
        //Arrange
        Customer customer = new Customer(0,"naeim","rahimzade","nae","rae","nae@rae.com","anzali,mala,iman 3,misagh,unit 7",20000d);
        Integer customerId = customersRepository.insert(customer);

        //Act
        Integer customerToDelete = customersRepository.delete(customerId);

        //Assert
        assertNotNull(customerToDelete);
        assertEquals(0,customersRepository.readAll().size());
    }

    @Test
    void plainDelete() {
        //Arrange
        Customer customer = new Customer(0,"naeim","rahimzade","nae","rae","nae@rae.com","anzali,mala,iman 3,misagh,unit 7",20000d);
        Integer customerId = customersRepository.delete(customer);

        //Act
        Integer customerToDelete = customersRepository.delete(customerId);

        //Assert
        assertNotNull(customerToDelete);
        assertEquals(0,customersRepository.readAll().size());
    }

    @AfterEach
    void wipe() {
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        session.createQuery("delete from OnlineShopUser").executeUpdate();
        transaction.commit();
        session.close();
    }

}