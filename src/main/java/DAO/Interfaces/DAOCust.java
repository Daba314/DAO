package DAO.Interfaces;


import Entities.Customer;
import Entities.Order;

import java.sql.SQLException;

public interface DAOCust extends DAO<Customer> {
    Customer getByLastName(String LastName) throws SQLException;
    int deleteCustomerByLastName(String LastName) throws SQLException;
    Order getOrderById(int id) throws SQLException;
}
