package controller.placeOrder;

import controller.item.ItemController;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaceOrderController implements PlaceOrderService{
    public static PlaceOrderController insance;

    private PlaceOrderController(){
    }

    public static PlaceOrderController getInstance()  {
        return  insance == null ? insance = new PlaceOrderController(): insance;

    }

    @Override
    public List<Item> getAllItem() {
        List<Item> customerList=new ArrayList<>();

        try {
            ResultSet resultSet = DBConnection.getINSTANCE()
                    .getConnection().
                    createStatement()
                    .executeQuery("SELECT * FROM item");

            while (resultSet.next()){

                customerList.add(new Item(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5)
                ));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return customerList;
    }

    @Override
    public ObservableList<Customer> getAllCustomer() {

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







}
