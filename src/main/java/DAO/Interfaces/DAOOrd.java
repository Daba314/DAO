package DAO.Interfaces;

import DB.Customers;
import DB.Orders;

import java.sql.SQLException;
import java.util.List;

public interface DAOOrd extends DAO<Orders>{
    List<Customers> getClientsById(long id) throws SQLException;
}
