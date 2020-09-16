package DAO.Interfaces;


import Dependencies.Customer;
import Dependencies.Order;

import java.sql.SQLException;

public interface DAOCust extends DAO<Customer> {
    Customer getByLastName(String LastName) throws SQLException;
    int deleteCustomerByLastName(String LastName) throws SQLException;
    Order getOrderById(int id) throws SQLException;
}
