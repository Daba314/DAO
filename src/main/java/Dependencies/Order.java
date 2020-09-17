package Dependencies;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Order {
    private int id;
    private String status;
    private String ShippingDestination;

    public Order(int id, String status, String shippingDestination) {
        this.id = id;
        this.status = status;
        ShippingDestination = shippingDestination;
    }


}
