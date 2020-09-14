package DAO.Interfaces;


import DB.Customers;
import DB.Orders;

import java.sql.SQLException;

public interface DAOCust extends DAO<Customers> {
    Customers getByLastName(String LastName) throws SQLException;
    int deleteCustomerByLastName(String LastName) throws SQLException;
    Orders getOrderById(int id) throws SQLException;
}
