package Service;

import DAO.Implementation.DAOOrdImpl;
import DAO.Implementation.LoginToPostgresException;
import Entities.Order;

import java.sql.SQLException;

public class OrderVerification {
    private DAOOrdImpl orderDAO;

    public OrderVerification() {
    }

    public int addNewOrder( String shippingDestination) {
        int res = -1;
        if (!shippingDestination.equals("faraway")) {
            Order order = new Order(shippingDestination);

            try {
                this.orderDAO = new DAOOrdImpl();
                this.orderDAO.insert(order);
                res = 1;
                return res;
            } catch (SQLException var8) {
                var8.printStackTrace();
                return res;
            } catch (LoginToPostgresException e) {
                e.printStackTrace();
                return res;
            }
        } else {
            return res;
        }
    }
}
