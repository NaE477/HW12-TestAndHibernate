package bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class President extends User {
    private Integer branch_id;
    @OneToMany
    private List<Clerk> clerks;
    private Double salary;


    public President(int userId, String firstname, String lastname,
                     String username, String password,
                     Integer branch_id,Double salary){
        super(userId, firstname, lastname, username, password);
        this.branch_id = branch_id;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "President ID: " + super.getUserId() +
                " Full Name: " + super.getFirstname()  + " " + super.getLastname() +
                " Username: " + super.getPassword() +
                " Branch ID: " + branch_id +
                " Salary: " + salary.intValue();
    }
}
