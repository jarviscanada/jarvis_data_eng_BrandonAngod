package ca.jrvs.apps.JDBC;
import ca.jrvs.apps.JDBC.util.CustomerDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {

    public static void main(String[] args) throws ClassNotFoundException {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "hplussport", "postgres", "password");
        try{
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);

            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Doe");
            customer.setEmail("John.Doe@gmail.com");
            customer.setPhone("(123) 456-7890");
            customer.setAddress("123 Seasme Street");
            customer.setCity("Hollywood");
            customer.setState("LA");
            customer.setZipCode("12345");

            Customer dbCustomer = customerDAO.create(customer);
            System.out.println(dbCustomer);
            dbCustomer = customerDAO.findById(dbCustomer.getId());
            System.out.println(dbCustomer);
            dbCustomer.setEmail("JDoe@gmail.com");
            dbCustomer = customerDAO.update(dbCustomer);
            System.out.println(dbCustomer);
            customerDAO.delete(dbCustomer.getId());

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
