package controller.placeOrder;

import controller.item.ItemController;
import db.DBConnection;
import model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderController implements PlaceOrderService{
    public static PlaceOrderController insance;

    private PlaceOrderController(){
    }

    public static PlaceOrderController getInstance()  {
        return  insance == null ? insance = new PlaceOrderController(): insance;

    }

    @Override
    public List<Item> getAll() {
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









}
