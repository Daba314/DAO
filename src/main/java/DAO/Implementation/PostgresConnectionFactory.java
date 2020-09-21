package DAO.Implementation;


import DAO.Interfaces.ConnectionFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionFactory implements ConnectionFactory {
    private static final String url = "jdbc:postgresql://localhost:5432/OnlineShop";
    private static String user;
    private static String password;



    @Override
    public Connection createConnection() throws SQLException, LoginToPostgresException {
        try{
            System.out.println("Write down user and password information");
            getLoginException();

        }
        catch(IOException e){
            System.out.println("got an error"+e);
            throw new LoginToPostgresException("Got a issue with password and user info", e);

        }
        return DriverManager.getConnection(url,user,password);
    }
    public static void getLoginException() throws IOException {
        File file = new File("userInfo");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        user = reader.readLine();
        password = reader.readLine();
        reader.close();
    }
}
