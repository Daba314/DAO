package Service;

import DAO.Implementation.DAOCustImpl;
import DAO.Implementation.LoginToPostgresException;
import Entities.Customer;

import java.sql.SQLException;

public class ChangePhoneNumber {
    public ChangePhoneNumber() {
    }
    public int changePhoneNumberByCustomerID(int customerID, long phoneNumber){
        int res = -1;
        Customer customer;
        try{
            customer = new DAOCustImpl().get(customerID);
            if(customer!=null){
                customer.setPhoneNumber(phoneNumber);
                res =  new DAOCustImpl().update(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (LoginToPostgresException e) {
            e.printStackTrace();
        }

        return res;
    }
}
