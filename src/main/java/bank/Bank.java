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
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String bank_name;
    @OneToMany
    private List<Branch> branches;
    @OneToMany
    private List<Account> accounts;

    @Override
    public String toString() {
        return "ID: " + id +
                " " + bank_name;
    }
}
