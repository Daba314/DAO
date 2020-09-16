package Dependencies;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String address;
    private int orderID;

    public Customer(int id, String firstName, String lastName, long phoneNumber, String address, int orderID) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderID = orderID;
    }
}
