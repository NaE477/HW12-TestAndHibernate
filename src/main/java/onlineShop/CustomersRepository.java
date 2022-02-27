package onlineShop;

import org.hibernate.SessionFactory;
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
            Integer customerId = (Integer) session.save(customer);
            transaction.commit();
            session.close();
            return customerId;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
        /*String insStmt = "INSERT INTO customers (first_name, last_name, username, password, email, address, balance) " +
                "VALUES (?,?,?,?,?,?,?) RETURNING customer_id;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(insStmt);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getUsername());
            ps.setString(4, customer.getPassword());
            ps.setString(5, customer.getEmailAddress());
            ps.setString(6, customer.getAddress());
            ps.setDouble(7, customer.getBalance());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("customer_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/
    }

    @Override
    public Customer read(Integer id) {
        var session = super.getSessionFactory().openSession();

        var transaction = session.beginTransaction();

        try {
            return session.get(Customer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
        /*String readStmt = "SELECT * FROM customers WHERE customer_id = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(readStmt);
            ps.setInt(1,id);
            return mapTo(ps.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/
    }

    public Customer read(String username){
        var session = super.getSessionFactory().openSession();

        var transaction = session.beginTransaction();

        try {
            return session.get(Customer.class, username);
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
        /*String readStmt = "SELECT * FROM customers WHERE username = ?;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(readStmt);
            ps.setString(1,username);
            return mapTo(ps.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/
    }
    public Customer readByEmail(String email){
        var session = super.getSessionFactory().openSession();

        var transaction = session.beginTransaction();

        try {
            return session.get(Customer.class, email);
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
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
        /*String readAllStmt = "SELECT * FROM customers";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(readAllStmt);
            return mapToList(ps.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/
    }

    @Override
    public Integer update(Customer customer) {
        var session = super.getSessionFactory().openSession();

        var transaction = session.beginTransaction();

        try {
            session.update(customer);
            return customer.getId();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
        /*String updateStmt = "UPDATE customers" +
                " SET password = ?," +
                "email = ?," +
                "address = ?," +
                "balance = ? " +
                "WHERE username = ? " +
                "RETURNING customer_id;";
        try {
            PreparedStatement ps = super.getConnection().prepareStatement(updateStmt);
            ps.setString(1,customer.getPassword());
            ps.setString(2,customer.getEmailAddress());
            ps.setString(3,customer.getAddress());
            ps.setDouble(4,customer.getBalance());
            ps.setString(5, customer.getUsername());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("customer_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/
    }

    @Override
    public Integer delete(Customer customer) {
        var session = super.getSessionFactory().openSession();

        var transaction = session.beginTransaction();

        try {
            session.delete(customer);
            return customer.getId();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
        /*try{
            String delStmt = "DELETE FROM customers WHERE customer_id = ? OR username = ? RETURNING customer_id;";
            PreparedStatement ps = super.getConnection().prepareStatement(delStmt);
            ps.setInt(1,customer.getId());
            ps.setString(2,customer.getUsername());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt("customer_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/
    }

    @Override
    public Integer delete(Integer id) {
        /*try{
            String delStmt = "DELETE FROM customers WHERE customer_id = ?;";
            PreparedStatement ps = super.getConnection().prepareStatement(delStmt);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return null;
    }
}
