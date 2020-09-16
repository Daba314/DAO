package DAO.Implementation;

import DAO.Interfaces.DAO;
import Dependencies.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOOrdImpl implements DAO<Order> {
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    public DAOOrdImpl() throws SQLException, ClassNotFoundException, LoginToPostgresException {
        conn = new PostgresConnectionFactory().createConnection();
    }

    @Override
    public int insert(Order order) throws SQLException, ClassNotFoundException, LoginToPostgresException {
        st = conn.prepareStatement("INSERT INTO OnlineShop.orders(STATUS,DESTINATION)VALUES(?,?);");
        st.setString(1, order.getStatus());
        st.setString(2, order.getShippingDestination());
        int res = st.executeUpdate();
        DAO.closing(st, conn);
        System.out.println("Order " + order + " successfully inserted");
        return res;
    }

    @Override
    public Order get(int id) throws SQLException {
        Order order = null;
        st = conn.prepareStatement("SELECT * FROM OnlineShop.order WHERE ORDERID= ?;");
        st.setInt(1, id);
        rs = st.executeQuery();
        while (rs.next()) {
            int order_id = rs.getInt("ORDERID");
            String status = rs.getString("STATUS");
            String destination = rs.getString("DESTINATION");

            order = new Order(order_id,status,destination);
        }
        DAO.closing(rs, st, conn);
        if (order == null) {
            System.out.println("Something wrong with getting order:  - " + id);
        } else {
            System.out.println("Order:" + id + " successfully retrieved");
        }
        return order;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        st = conn.prepareStatement("SELECT * FROM OnlineShop.orders;");
        rs = st.executeQuery();

        while (rs.next()) {
            int order_id = rs.getInt("ORDERID");
            String status = rs.getString("STATUS");
            String destination = rs.getString("DESTINATION");
            orders.add(new Order(order_id,status,destination));
        }

        DAO.closing(rs, st, conn);
        return orders;
    }

    @Override
    public int update(Order order) throws SQLException {
        st = conn.prepareStatement("UPDATE OnlineShop.orders " +
                "SET STATUS = ?, DESTINATION = ? WHERE ORDERID = ?;");
        st.setString(1, order.getStatus());
        st.setString(2, order.getShippingDestination());
        st.setInt(3,order.getId());
        int res = st.executeUpdate();
        DAO.closing(st, conn);
        if (res == 0) {
            System.out.println("Something wrong with updating order - " + order);
        } else {
            System.out.println("Order with the id = " + order.getId() + " successfully updated");
        }
        return res;
    }

    @Override
    public int delete(int id) throws SQLException {
        st = conn.prepareStatement("DELETE FROM OnlineShop.orders WHERE ORDERID = ?;");
        st.setInt(1, id);
        int res = st.executeUpdate();
        DAO.closing(st, conn);
        if (res == 0) {
            System.out.println("Something wrong" + id);
        } else {
            System.out.println("Order:" + id + " successfully deleted ");
        }
        return res;
    }

}
