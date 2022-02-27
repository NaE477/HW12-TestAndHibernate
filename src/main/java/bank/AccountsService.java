package bank;

import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.util.List;

public class AccountsService {
    SessionFactory sessionFactory;
    AccountsRep ar;

    public AccountsService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        ar = new AccountsRep(this.sessionFactory);
    }

    public Integer open(Account account) {
        return ar.insert(account);
    }

    public Boolean exists(Integer accountId) {
        return ar.read(accountId) != null;
    }

    /*public Boolean clientExistsInBranch(Integer clientId, Integer branchId) {
        return findByClientInBranch(clientId,branchId) != null;
    }*/

    public Boolean existsForClient(Integer clientId, Integer accountId) {
        List<Account> accounts = findAllByClient(clientId);
        for (Account account : accounts) {
            if (account.getId() == accountId) {
                return true;
            }
        }
        return false;
    }

    /*public Boolean existsInBranch(Integer branchId, Integer accountId) {
        List<Account> accounts = ar.readAllByBranch(branchId);
        for (Account account : accounts) {
            if (account.getId() == accountId) return true;
        }
        return false;
    }*/

    public void closeAccount(Account account) {
        ar.delete(account);
    }
/*

    public List<Account> findAllByBranch(Integer branchId) {
        return ar.readAllByBranch(branchId);
    }
*/


    public List<Account> findAllByClient(Integer clientId) {
        return ar.readAllByClient(clientId);
    }


    public Account findById(Integer id) {
        return ar.read(id);
    }

    public List<Account> findAll() {
        return ar.readAll();
    }
    /*public Account findByClientInBranch(Integer clientId,Integer branchId){
        return ar.readAccountByClientAndBranch(clientId,branchId);
    }*/

    public Integer doTransaction(Account account) {
        return ar.update(account);
    }

}
