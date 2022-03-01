package bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class President extends BankUser {
    @OneToOne
    Branch branch;
    @OneToMany
    private List<Clerk> clerks;
    private Double salary;


    public President(int userId, String firstname, String lastname,
                     String username, String password,
                     Branch branch,Double salary){
        super(userId, firstname, lastname, username, password);
        this.branch = branch;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "President ID: " + super.getUserId() +
                " Full Name: " + super.getFirstname()  + " " + super.getLastname() +
                " Username: " + super.getPassword() +
                " Branch ID: " + branch.getId() +
                " Salary: " + salary.intValue();
    }
}
