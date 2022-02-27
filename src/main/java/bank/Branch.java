package bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String branch_name;
    @OneToOne
    private Bank bank;
    @OneToOne
    private President president;
    @OneToMany
    private List<Clerk> clerks;
    @OneToMany
    private List<Account> accounts;

    public Branch(int branchId) {
        this.id = branchId;
    }

    public Branch(int branch_id, String branch_name) {
        this.branch_name = branch_name;
        this.id = branch_id;
    }

    public President getPresident() {
        return president;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank_id(Bank bank) {
        this.bank = bank;
    }

    public President president() {
        return president;
    }

    public void setPresident(President president) {
        this.president = president;
    }

    public List<Clerk> getClerks() {
        return clerks;
    }

    public void setClerks(List<Clerk> clerks) {
        this.clerks = clerks;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String bank_name) {
        this.branch_name = bank_name;
    }
}
