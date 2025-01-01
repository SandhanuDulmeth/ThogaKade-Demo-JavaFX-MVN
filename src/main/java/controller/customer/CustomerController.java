package controller.customer;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        return false;
    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }

    @Override
    public ObservableList<Customer> getAll() {
        //List<Customer> customerList=new ArrayList<>();
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = DBConnection.getINSTANCE()
                    .getConnection().
                    createStatement()
                    .executeQuery("SELECT * FROM customer");

            while (resultSet.next()){
               // String id=resultSet.getString(1);
               // String name=resultSet.getString(2);
               // String address=resultSet.getString(3);
               // Double salary= resultSet.getDouble(4);

                customerObservableList.add(new Customer(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4)));

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
