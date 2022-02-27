package onlineShop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Customer extends User {
    private String address;
    private Double balance;

    @Override
    public String toString() {
        return "ID: " + super.getId() +
                "\nFull Name: " + super.getFirstName() + " " + super.getLastName() +
                "\nEmail: " + super.getEmailAddress() + " " +
                "\nAddress: " + getAddress() +
                "\nBalance: " + getBalance().toString();
    }
}
