package DAO.Interfaces;

import Entities.Customer;
import Entities.Order;

import java.sql.SQLException;
import java.util.List;

public interface DAOOrd extends DAO<Order>{
    List<Customer> getClientsById(int id) throws SQLException;
    Order getByStatus(String status) throws SQLException;
}
