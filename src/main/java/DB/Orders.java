package DB;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Orders {
    private int id;
    private String status;
    private String ShippingDestination;

    public Orders(int id, String status, String shippingDestination) {
        this.id = id;
        this.status = status;
        ShippingDestination = shippingDestination;
    }


}
