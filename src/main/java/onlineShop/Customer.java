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
public class Customer extends OnlineShopUser {
    private String address;
    private Double balance;

    public Customer(Integer id,String firstname,String lastname,String username,String password,String emailAddress,String address,Double balance){
        super(id,firstname,lastname,username,password,emailAddress);
        this.address = address;
        this.balance = balance;
    }
    @Override
    public String toString() {
        return "ID: " + super.getId() +
                "\nFull Name: " + super.getFirstName() + " " + super.getLastName() +
                "\nEmail: " + super.getEmailAddress() + " " +
                "\nAddress: " + getAddress() +
                "\nBalance: " + getBalance().toString();
    }
}
