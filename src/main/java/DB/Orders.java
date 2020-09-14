package DB;

public class Orders {
    private int id;
    private String status;
    private String ShippingDestination;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orders(int id, String status, String shippingDestination) {
        this.id = id;
        this.status = status;
        ShippingDestination = shippingDestination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShippingDestination() {
        return ShippingDestination;
    }

    public void setShippingDestination(String shippingDestination) {
        ShippingDestination = shippingDestination;
    }

}
