package DAO.Interfaces;

import DAO.Implementation.LoginToPostgresException;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {
Connection createConnection() throws SQLException, LoginToPostgresException;
}
