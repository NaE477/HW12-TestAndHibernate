package bank;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
    }

    public List<Account> readAllByClient(Client client){
        var session = sessionFactory.openSession();

        var transaction = session.beginTransaction();

        try {
            Query<Account> query = session.createQuery("from Account where client.id = :clientId");
            query.setParameter("clientId",client.getUserId());
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }
    public List<Account> readAccountsByClientInBranch(Client client, Branch branch){
        var session = sessionFactory.openSession();

        var transaction = session.beginTransaction();

        try {
            Query<Account> query = session.createQuery("from Account where client.id = :clientId and branch.id = :branchId");
            query.setParameter("clientId",client.getUserId());
            query.setParameter("branchId",branch.getId());
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public Integer update(Account account) {
        var session = sessionFactory.openSession();

        var transaction = session.beginTransaction();

        try {
            session.update(account);
            transaction.commit();
            session.close();
            return account.getId();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void delete(Account account) {
        var session = sessionFactory.openSession();

        var transaction = session.beginTransaction();

        try {
            session.delete(account);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
}
