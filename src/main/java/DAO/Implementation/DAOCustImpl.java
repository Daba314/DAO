package DAO.Implementation;

import DAO.Interfaces.DAO;
import DAO.Interfaces.DAOCust;
import DB.Customers;
import DB.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCustImpl implements DAOCust {
    private Connection conn;
    private PreparedStatement st;
    private ResultSet rs;

    public DAOCustImpl() throws SQLException, ClassNotFoundException, LoginToPostgresException {
        conn = new PostgresConnectionFactory().createConnection();
    }
    @Override
    public int insert(Customers customer) throws SQLException, ClassNotFoundException,LoginToPostgresException {
        st = conn.prepareStatement("INSERT INTO customers(FIRSTNAME,LASTNAME,PHONENUMBER,ADDRESS,ORDERID)VALUES(?,?,?,?,?);");
        st.setString(1, customer.getFirstName());
        st.setString(2, customer.getLastName());
        st.setLong(3, customer.getPhoneNumber());
        st.setString(4,customer.getAddress());
        st.setInt(5,customer.getOrderID());
        int res = st.executeUpdate();
        DAO.closing(st, conn);
        System.out.println("Customer " + customer + " successfully inserted");
        return res;
    }

    @Override
    public Customers get(int id) throws SQLException {
        Customers customer = null;
        st = conn.prepareStatement("SELECT * FROM OnlineShop.customers WHERE CUSTOMERID= ?;");
        st.setInt(1, id);
        rs = st.executeQuery();
        while (rs.next()) {
            int customer_id = rs.getInt("CUSTOMERID");
            String firstName = rs.getString("FIRSTNAME");
            String lastName = rs.getString("LASTNAME");
            long phoneNumber = rs.getLong("PHONENUMBER");
            String address = rs.getString("ADDRESS");
            int order_id = rs.getInt("ORDERID");

            customer = new Customers(customer_id,firstName,lastName,phoneNumber,address,order_id);
        }
        DAO.closing(rs, st, conn);
        if (customer == null) {
            System.out.println("Something wrong with getting customer:  - " + id);
        } else {
            System.out.println("Customer:" + id + " successfully retrieved");
        }
        return customer;
    }

    @Override
    public List<Customers> getAll() throws SQLException {
        List<Customers> customers = new ArrayList<>();
        st = conn.prepareStatement("SELECT * FROM OnlineShop.customers;");
        rs = st.executeQuery();
        while (rs.next()) {
            int customer_id = rs.getInt("CUSTOMERID");
            String firstName = rs.getString("FIRSTNAME");
            String lastName = rs.getString("LASTNAME");
            long phoneNumber = rs.getLong("PHONENUMBER");
            String address = rs.getString("ADDRESS");
            int order_id = rs.getInt("ORDERID");
            customers.add(new Customers(customer_id,firstName,lastName,phoneNumber,address,order_id));
        }
        DAO.closing(rs, st, conn);
        return customers;
    }

    @Override
    public int update(Customers customer) throws SQLException {
        st = conn.prepareStatement("UPDATE OnlineShop.customers " +
                "SET FIRSTNAME = ?, LASTNAME = ?, PHONENUMBER  = ?, ADDRESS  = ?, ORDERID = ? WHERE CUSTOMERID = ?;");
        st.setString(1, customer.getFirstName());
        st.setString(2, customer.getLastName());
        st.setLong(3, customer.getPhoneNumber());
        st.setString(4,customer.getAddress());
        st.setInt(5,customer.getOrderID());
        st.setInt(6,customer.getId());
        int res = st.executeUpdate();
        DAO.closing(st, conn);
        if (res == 0) {
            System.out.println("Something wrong with updating customer - " + customer);
        } else {
            System.out.println("Customer with the id = " + customer.getId() + " successfully updated");
        }
        return res;
    }

    @Override
    public int delete(int id) throws SQLException {
        st = conn.prepareStatement("DELETE FROM OnlineShop.customers WHERE CUSTOMERID = ?;");
        st.setInt(1, id);
        int res = st.executeUpdate();
        DAO.closing(st, conn);
        if (res == 0) {
            System.out.println("Something wrong" + id);
        } else {
            System.out.println("Customer:" + id + " successfully deleted ");
        }
        return res;
    }

    @Override
    public Customers getByLastName(String lastName) throws SQLException {
        Customers customer = null;
        st = conn.prepareStatement("SELECT * FROM OnlineShop.customers WHERE LASTNAME = ?;");
        st.setString(1, lastName);
        rs = st.executeQuery();
        while (rs.next()) {
            int customer_id = rs.getInt("CUSTOMERID");
            String firstName = rs.getString("FIRSTNAME");
            String lastNameZ = rs.getString("LASTNAME");
            long phoneNumber = rs.getLong("PHONENUMBER");
            String address = rs.getString("ADDRESS");
            int order_id = rs.getInt("ORDERID");

            customer = new Customers(customer_id,firstName,lastNameZ,phoneNumber,address,order_id);
        }
        DAO.closing(rs, st, conn);
        if (customer == null) {
            System.out.println("Something wrong with getting customer by last name - " + lastName);
        } else {
            System.out.println("Customer with the last name = " + lastName + " successfully retrieved");
        }
        return customer;
    }

    @Override
    public int deleteCustomerByLastName(String lastName) throws SQLException {
        st = conn.prepareStatement("DELETE FROM OnlineShop.customers WHERE LASTNAME = ?;");
        st.setString(1, lastName);
        int res = st.executeUpdate();
        DAO.closing(st, conn);
        if (res == 0) {
            System.out.println(" u r not able to delete customer with last name " +lastName);
        } else {
            System.out.println("customer with the last name = " + lastName + " successfully deleted");
        }
        return res;
    }

    @Override
    public Orders getOrderById(int id) throws SQLException {
        st = conn.prepareStatement("SELECT * FROM OnlineShop.orders WHERE ORDERID IN (SELECT ORDERID FROM OnlineShop.customers WHERE CUSTOMERID = ?);");
        st.setInt(1,id);
        rs = st.executeQuery();
        Orders order = null;
        while (rs.next()) {
            int order_id = rs.getInt("ORDERID");
            String status = rs.getString("STATUS");
            String shippingDestination = rs.getString("DESTINATION");
            order = new Orders(order_id,status,shippingDestination);
        }
        DAO.closing(st, conn);
        if (order == null) {
            System.out.println("Something wrong with getting Order with customer id - " + id);
        } else {
            System.out.println("Order with the customer id = " + id + " successfully retrieved");
        }
        return order;
    }
}
