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
public class Client extends BankUser {
    @OneToOne
    private Account account;

    public Client(int userId, String firstname, String lastname,
                  String username, String password){
        super(userId, firstname, lastname, username, password);
    }

    @Override
    public String toString() {
        return "Client ID: " + getUserId() +
                " Full Name: "+ getFirstname() + " " + getLastname();
    }
}
