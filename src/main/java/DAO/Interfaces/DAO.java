package DAO.Interfaces;


import DAO.Implementation.LoginToPostgresException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

    public interface DAO<T> {
        int insert (T entity) throws SQLException, ClassNotFoundException, LoginToPostgresException;
        T get (int id) throws SQLException;
        List<T> getAll() throws SQLException;
        int update(T entity) throws SQLException;
        int delete(int id) throws SQLException;

        static void closing(PreparedStatement st, Connection conn) throws SQLException{
            if(st!=null) st.close();
            if(conn!=null) conn.close();
        }

        static void closing(ResultSet res, PreparedStatement st, Connection conn) throws SQLException{
            if(res!=null) res.close();
            if(st!=null) st.close();
            if(conn!=null) conn.close();
        }


}
