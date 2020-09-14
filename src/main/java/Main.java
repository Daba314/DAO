import DAO.Implementation.DAOCustImpl;
import DAO.Implementation.LoginToPostgresException;
import DAO.Interfaces.DAOCust;
import DB.Customers;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws LoginToPostgresException, SQLException, ClassNotFoundException {
        DAOCust custDAO = new DAOCustImpl();
        Customers customer = new Customers(1,"DAba","Dashiev", 7164302,"85 Clapperton St",1);
        custDAO.insert(customer);

    }
}
