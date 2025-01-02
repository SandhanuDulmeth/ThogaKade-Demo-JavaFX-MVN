package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerController implements CustomerService {
    public static CustomerController insance;

    private CustomerController() {
    }

    public static CustomerController getInstance() {
        return insance == null ? insance = new CustomerController() : insance;

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
            preparedStatement.setDouble(5, customer.getSalary());
            preparedStatement.setString(6, customer.getCustAddress());
            preparedStatement.setString(7, customer.getCity());
            preparedStatement.setString(8, customer.getProvince());
            preparedStatement.setString(9, customer.getPostalCode());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deleteCustomer(String id) {

        Connection connection = null;
        //int i = 0;
        try {
            connection = DBConnection.getINSTANCE().getConnection();

            PreparedStatement stm = connection.prepareStatement("DELETE FROM customer WHERE CustID = ?");
            stm.setObject(1, id);
            //  i = stm.executeUpdate();
            return stm.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public ObservableList<Customer> getAll() {

        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = DBConnection.getINSTANCE()
                    .getConnection().
                    createStatement()
                    .executeQuery("SELECT * FROM customer");

            while (resultSet.next()) {

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
        Connection connection = null;
        try {
            connection = DBConnection.getINSTANCE().getConnection();

            PreparedStatement stm = connection.prepareStatement("UPDATE customer SET CustTitle = ?, CustName = ?, DOB = ?, salary = ?, CustAddress = ?,City = ?,Province = ?,PostalCode=? WHERE CustID = ?");
            stm.setObject(9, customer.getCustID());
            stm.setObject(1, customer.getCustTitle());
            stm.setObject(2, customer.getCustName());
            stm.setObject(3, customer.getDOB());
            stm.setObject(4, customer.getSalary());
            stm.setObject(5, customer.getCustAddress());
            stm.setObject(6, customer.getCity());
            stm.setObject(7, customer.getProvince());
            stm.setObject(8, customer.getPostalCode());
            //  int i = stm.executeUpdate();

            System.out.println("u");
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer searchCustomer(String CusID) {
        Connection connection = null;
        try {
            connection = DBConnection.getINSTANCE().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer WHERE CustID=? ");
            preparedStatement.setString(1, CusID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer(CusID,
                        resultSet.getString(2),
                        resultSet.getString(3),
                        (Date) resultSet.getObject(4),
                        Double.valueOf(resultSet.getString(5)),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                );
//
                return customer;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
