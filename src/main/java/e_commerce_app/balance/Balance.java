package e_commerce_app.balance;
import java.util.UUID;
public abstract class Balance {

    private UUID customerId;
    private Double balance;

    public Balance(UUID customerId, Double balance) {
        this.customerId = customerId;
        this.balance = balance;
    } // why i need to constructor, because using this one I will create data, I need a Constructor

    // whatever you are creating a concrete class (first concrete class) which is extending Abstract class which is balance,
    // then that class responsibility to implement this abstract method, Please know this structure very well

    // let me create my getter because I will retrieve the customerId to able to access my balance,
    // I will get my balance to able to add something
    public UUID getCustomerId() {
        return customerId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    } // I need to setBalance() in child class, so I putted here setter method

    public abstract Double addBalance(Double additionalBalance);
}
