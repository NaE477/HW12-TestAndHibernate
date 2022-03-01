package onlineShop;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import java.util.List;

public class CustomersRepository extends Repository<Customer>{
        public CustomersRepository(SessionFactory sessionFactory) {
            super(sessionFactory);
        }

    @Override
    public Integer insert(Customer customer) {
        var session = super.getSessionFactory().openSession();

        var transaction = session.beginTransaction();

        try {
            session.save(customer);
            transaction.commit();
            session.close();
            return customer.getId();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public Customer read(Integer id) {
        var session = super.getSessionFactory().openSession();

        var transaction = session.beginTransaction();

        try {
            Customer customer = session.get(Customer.class, id);
            session.close();
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    public Customer read(String username){
        var session = super.getSessionFactory().openSession();

        var transaction = session.beginTransaction();

        try {
            Criteria criteria = session.createCriteria(Customer.class);
            Customer customer = (Customer) criteria.add(Restrictions.eq("username",username)).uniqueResult();
            session.close();
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }
    public Customer readByEmail(String email){
        var session = super.getSessionFactory().openSession();

        var transaction = session.beginTransaction();

        try {
            Criteria criteria = session.createCriteria(Customer.class);
            Customer customer = (Customer) criteria.add(Restrictions.eq("emailAddress",email)).uniqueResult();
            session.close();
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public List<Customer> readAll() {
        var session = super.getSessionFactory().openSession();

        var transaction = session.beginTransaction();

        try {
            Query<Customer> query = session.createQuery("from Customer",Customer.class);
            List<Customer> customers = query.list();
            session.close();
            return customers;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public Integer update(Customer customer) {
        var session = super.getSessionFactory().openSession();

        var transaction = session.beginTransaction();

        try {
            session.saveOrUpdate(customer);
            transaction.commit();
            session.close();
            return customer.getId();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public Integer delete(Customer customer) {
        var session = super.getSessionFactory().openSession();

        var transaction = session.beginTransaction();

        try {
            session.delete(customer);
            transaction.commit();
            session.close();
            return customer.getId();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public Integer delete(Integer id) {
        var session = super.getSessionFactory().openSession();

        var transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("delete from Customer where id = :id");
            query.setParameter("id",id);
            query.executeUpdate();
            session.close();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }
}
