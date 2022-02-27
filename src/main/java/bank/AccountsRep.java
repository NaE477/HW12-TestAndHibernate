package bank;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountsRep implements ThingCRUD<Account> {
    private final SessionFactory sessionFactory;
    public AccountsRep(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer insert(Account account) {
        var session = sessionFactory.openSession();

        var transaction = session.beginTransaction();

        try {
            session.save(account);
            transaction.commit();
            session.close();
            return account.getId();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
        /*String insertStmt = "INSERT INTO accounts (" +
                "accountnumber, balance, bank_id, branch_id, client_id" +
                ") " +
                "VALUES (?,?,?,?,?)" +
                "RETURNING id;";
        try {
            PreparedStatement ps = connection.prepareStatement(insertStmt);
            ps.setString(1,account.getAccountNumber());
            ps.setDouble(2,account.getBalance());
            ps.setInt(3,account.getBank().getId());
            ps.setInt(4,account.getBranch().getId());
            ps.setInt(5,account.getClient().getUserId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/
    }

    @Override
    public Account read(Integer accountId) {
        var session = sessionFactory.openSession();

        var transaction = session.beginTransaction();

        try {
            return session.get(Account.class,accountId);
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
        /*String selectStmt = "SELECT * FROM accounts " +
                " INNER JOIN clients c on c.id = accounts.client_id " +
                "INNER JOIN banks b on b.id = accounts.bank_id " +
                "INNER JOIN branches b2 on b.id = b2.bank_id " +
                "WHERE accounts.id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(selectStmt);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(
                        rs.getInt("id"),
                        rs.getString("accountnumber"),
                        new Client(
                                rs.getInt(7),
                                rs.getString("username"),
                                rs.getString("password")
                        ),
                        new Bank(
                                rs.getInt(12),
                                rs.getString("bank_name")
                        ),
                        new Branch(rs.getInt(14)),
                        rs.getDouble("balance")
                );
            }
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/
    }

    @Override
    public List<Account> readAll() {
        var session = sessionFactory.openSession();

        var transaction = session.beginTransaction();

        try {
            Query<Account> query = session.createQuery("from Account",Account.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
        /*List<Account> accounts = new ArrayList<>();
        String selectStmt = "SELECT * FROM accounts " +
                " INNER JOIN clients c on c.id = accounts.client_id " +
                "INNER JOIN banks b on b.id = accounts.bank_id " +
                "INNER JOIN branches b2 on b.id = b2.bank_id;";
        try {
            PreparedStatement ps = connection.prepareStatement(selectStmt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accounts.add(new Account(
                                rs.getInt("id"),
                                rs.getString("accountnumber"),
                                new Client(
                                        rs.getInt(7),
                                        rs.getString("username"),
                                        rs.getString("password")
                                ),
                                new Bank(
                                        rs.getInt(12),
                                        rs.getString("bank_name")
                                ),
                                new Branch(rs.getInt(14)),
                                rs.getDouble("balance")
                        )
                );
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/
    }

    public List<Account> readAllByBranch(Account account){
        var session = sessionFactory.openSession();

        var transaction = session.beginTransaction();

        try {
            Query<Account> query = session.createQuery("");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
        /*List<Account> accounts = new ArrayList<>();
        String selectStmt = "SELECT * FROM accounts " +
                "INNER JOIN clients c on c.id = accounts.client_id " +
                "INNER JOIN banks b on b.id = accounts.bank_id " +
                "INNER JOIN branches b2 on b.id = b2.bank_id " +
                " WHERE branch_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(selectStmt);
            ps.setInt(1,branchId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accounts.add(new Account(
                                rs.getInt("id"),
                                rs.getString("accountnumber"),
                                new Client(
                                        rs.getInt("client_id"),
                                        rs.getString("username"),
                                        rs.getString("password")
                                ),
                                new Bank(
                                        rs.getInt("bank_id"),
                                        rs.getString("bank_name")
                                ),
                                new Branch(rs.getInt("branch_id"),
                                        rs.getString("branch_name")),
                                rs.getDouble("balance")
                        )
                );
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/
    }
    public List<Account> readAllByClient(Integer clientId){
        return null;
        /*List<Account> accounts = new ArrayList<>();
        String selectStmt = "SELECT * FROM accounts" +
                " INNER JOIN clients c on c.id = accounts.client_id " +
                " Inner Join banks b on b.id = accounts.bank_id " +
                " INNER JOIN branches b2 on b.id = b2.bank_id" +
                " WHERE client_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(selectStmt);
            ps.setInt(1,clientId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                accounts.add(new Account(
                                rs.getInt(1),
                                rs.getString("accountnumber"),
                                new Client(
                                        rs.getInt(7),
                                        rs.getString("username"),
                                        rs.getString("password")
                                ),
                                new Bank(
                                        rs.getInt(12),
                                        rs.getString("bank_name")
                                ),
                                new Branch(rs.getInt(14),rs.getString("branch_name")),
                                rs.getDouble("balance")
                        )
                );
            }
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/
    }
    /*public Account readAccountByClientAndBranch(Integer clientId,Integer branchId){
        String selectStmt = "SELECT * FROM accounts " +
                "INNER JOIN clients c on c.id = accounts.client_id " +
                "INNER JOIN banks b on b.id = accounts.bank_id " +
                "INNER JOIN branches b2 on b.id = b2.bank_id" +
                " WHERE branch_id = ? AND client_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(selectStmt);
            ps.setInt(1,branchId);
            ps.setInt(2,clientId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Account(
                        rs.getInt(1),
                        rs.getString("accountnumber"),
                        new Client(rs.getInt(6),rs.getString(10),rs.getString(11)),
                        new Bank(rs.getInt(12),rs.getString(13)),
                        new Branch(rs.getInt(14)),
                        rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    @Override
    public Integer update(Account account) {
        var session = sessionFactory.openSession();

        var transaction = session.beginTransaction();

        try {
            session.update(account);
            return account.getId();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
        /*String updateStmt = "UPDATE accounts SET balance = ?" +
                "WHERE id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(updateStmt);
            ps.setDouble(1,account.getBalance());
            ps.setInt(2,account.getId());
            ps.executeUpdate();
            return account.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/
    }

    @Override
    public void delete(Account account) {
        var session = sessionFactory.openSession();

        var transaction = session.beginTransaction();

        try {
            session.delete(account);
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        /*String delStmt = "DELETE FROM accounts WHERE id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(delStmt);
            ps.setInt(1, account.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }
}
