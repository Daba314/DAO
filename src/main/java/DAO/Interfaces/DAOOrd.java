package DAO.Interfaces;

import Dependencies.Customer;
import Dependencies.Order;

import java.sql.SQLException;
import java.util.List;

public interface DAOOrd extends DAO<Order>{
    List<Customer> getClientsById(long id) throws SQLException;
}
