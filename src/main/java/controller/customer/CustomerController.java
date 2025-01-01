package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerController implements CustomerService {
    public static CustomerController insance;

    private CustomerController(){
    }

    public static CustomerController getInstance()  {
        return  insance == null ? insance = new CustomerController(): insance;

    }

    @Override
    public boolean addCustomer(Customer customer) {

        Connection connection = null;
        try {
            connection = DBConnection.getINSTANCE().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, customer.getCustID());
            preparedStatement.setString(2, customer.getCustTitle());
            preparedStatement.setString(3, customer.getCustName());
            preparedStatement.setDate(4, (java.sql.Date) customer.getDOB());
            preparedStatement.setDouble(5,customer.getSalary() );
            preparedStatement.setString(6, customer.getCustAddress());
            preparedStatement.setString(7, customer.getCity());
            preparedStatement.setString(8, customer.getProvince());
            preparedStatement.setString(9, customer.getPostalCode());
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }

    @Override
    public ObservableList<Customer> getAll() {

        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = DBConnection.getINSTANCE()
                    .getConnection().
                    createStatement()
                    .executeQuery("SELECT * FROM customer");

            while (resultSet.next()){

                customerObservableList.add(new Customer(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        (Date) resultSet.getObject(4),
                        resultSet.getDouble(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)

                ));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return customerObservableList;
    }

    @Override
    public boolean UpdateCustomer(Customer customer) {
        return false;
    }

    @Override
    public Customer searchCustomer(String name) {
        return null;
    }
}
