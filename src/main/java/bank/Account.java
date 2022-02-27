package bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String accountNumber;
    @OneToOne
    private Client client;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    @ManyToOne
    private Branch branch;
    private Double balance;

    @Override
    public String toString() {
        return "ID: " + id +
                " Account Number: "  + accountNumber +
                " Owner: " + client.getUsername() +
                " Bank: " + bank.getName() +
                " Branch: " + branch.getBranch_name() +
                " Balance: " + balance.toString();
    }
}
