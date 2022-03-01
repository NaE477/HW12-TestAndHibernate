package onlineShop;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomersRepositoryTest {
    private static SessionFactory sessionFactory;
    private static CustomersRepository customersRepository;

    @BeforeAll
    static void initialize() {
        sessionFactory = SessionFactorySingleton.getInstance();
        customersRepository = new CustomersRepository(sessionFactory);
    }

    @Test
    void connectionTest() {
        assertDoesNotThrow(() -> sessionFactory = SessionFactorySingleton.getInstance());
    }

    @Test
    void insTest() {
        //Arrange
        Customer customer = new Customer(0, "naeim", "rahimzade", "nae", "rae", "nae@rae.com", "anzali,mala,iman 3,misagh,unit 7", 20000d);

        //Act
        Integer newCustomerId = customersRepository.insert(customer);

        //Assert
        assertNotEquals(0, customer.getId());
        assertNotNull(newCustomerId);
    }

    @Test
    void readIdTest() {
        //Arrange
        Customer customer = new Customer(0, "naeim", "rahimzade", "nae", "rae", "nae@rae.com", "anzali,mala,iman 3,misagh,unit 7", 20000d);
        Integer newCustomerId = customersRepository.insert(customer);

        //Act
        Customer customerToFind = customersRepository.read(newCustomerId);

        //Assert
        assertNotNull(customerToFind);
        assertNotNull(newCustomerId);
        assertEquals(newCustomerId, customer.getId());
        assertEquals(newCustomerId, customerToFind.getId());
    }

    @Test
    void readByUsernameTest() {
        //Arrange
        Customer customer = new Customer(0, "naeim", "rahimzade", "nae", "rae", "nae@rae.com", "anzali,mala,iman 3,misagh,unit 7", 20000d);
        Integer newCustomerId = customersRepository.insert(customer);

        //Act
        Customer customerToFind = customersRepository.read("nae");

        //Assert
        assertNotNull(customerToFind);
        assertEquals(newCustomerId, customerToFind.getId());
        assertEquals(newCustomerId, customerToFind.getId());
    }

    @Test
    void readByEmailTest() {
        //Arrange
        Customer customer = new Customer(0, "naeim", "rahimzade", "nae", "rae", "nae@rae.com", "anzali,mala,iman 3,misagh,unit 7", 20000d);
        Integer newCustomerId = customersRepository.insert(customer);

        //Act
        Customer customerToFind = customersRepository.readByEmail(customer.getEmailAddress());

        //Assert
        assertNotNull(customerToFind);
        assertEquals(newCustomerId, customerToFind.getId());
        assertEquals(newCustomerId, customer.getId());
    }

    @Test
    void readAllTest() {
        //Arrange
        Customer customer1 = new Customer(0, "naeim", "rahimzade", "nae", "rae", "nae@rae.com", "anzali,mala,iman 3,misagh,unit 7", 20000d);
        Customer customer2 = new Customer(0, "ali", "hoseini", "ali", "aa123", "ali@ali.com", "anzali,ghazian,takhti", 25000d);
        Customer customer3 = new Customer(0, "arad", "mohammadi", "arad", "arad123", "arad@arad.com", "anzali,anzali,seyaran", 40000d);
        customersRepository.insert(customer1);
        customersRepository.insert(customer2);
        customersRepository.insert(customer3);

        //Act
        List<Customer> customers = customersRepository.readAll();

        //Assert
        assertEquals(3, customers.size());
    }

    @Test
    void updateTest() {
        //Arrange
        Customer customer = new Customer(0, "naeim", "rahimzade", "nae", "rae", "nae@rae.com", "anzali,mala,iman 3,misagh,unit 7", 20000d);
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
        assertEquals("editedFirstname", customersRepository.read(updateId).getFirstName());
        assertEquals("editedLastname", customersRepository.read(updateId).getLastName());
        assertEquals("editedUsername", customersRepository.read(updateId).getUsername());
        assertEquals("editedPassword", customersRepository.read(updateId).getPassword());
        assertEquals("editedAddress", customersRepository.read(updateId).getAddress());
        assertEquals("editedEmail", customersRepository.read(updateId).getEmailAddress());
    }

    @Test
    void deleteByIdTest() {
        //Arrange
        Customer customer = new Customer(0, "naeim", "rahimzade", "nae", "rae", "nae@rae.com", "anzali,mala,iman 3,misagh,unit 7", 20000d);
        Integer customerId = customersRepository.insert(customer);

        //Act
        Integer customerToDelete = customersRepository.delete(customerId);

        //Assert
        assertNotNull(customerToDelete);
        assertEquals(0, customersRepository.readAll().size());
    }

    @Test
    void plainDelete() {
        //Arrange
        Customer customer = new Customer(0, "naeim", "rahimzade", "nae", "rae", "nae@rae.com", "anzali,mala,iman 3,misagh,unit 7", 20000d);
        Integer customerId = customersRepository.insert(customer);

        //Act
        Integer customerToDelete = customersRepository.delete(customer);

        //Assert
        assertNotNull(customerToDelete);
        assertEquals(0, customersRepository.readAll().size());
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