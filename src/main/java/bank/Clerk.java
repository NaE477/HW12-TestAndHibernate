package bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Clerk extends User {
    @OneToOne
    private Branch branch;
    @OneToOne
    private President president;
    private Double salary;

    public String toString() {
        return "Clerk ID: " + super.getUserId() +
                " First Name: " + super.getFirstname() +
                " Last Name: " + super.getLastname() +
                " Branch ID: " + branch.getId() +
                " President ID: " + president.getUserId() +
                " Salary=" + salary;
    }
}
