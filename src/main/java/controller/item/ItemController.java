package controller.item;

import db.DBConnection;
import javafx.scene.control.Alert;

import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements  ItemService{
    public static ItemController insance;

    private ItemController(){
    }

    public static ItemController getInstance()  {
        return  insance == null ? insance = new ItemController(): insance;

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

    @Override
    public boolean saveCustomer(Item item) {
        try {
            Connection connection = DBConnection.getINSTANCE().getConnection();
            PreparedStatement stm = connection.prepareStatement("INSERT INTO item VALUES (?, ?, ?, ?, ?)");
            stm.setObject(1, item.getItemCode());
            stm.setObject(2, item.getDescription());
            stm.setObject(3, item.getPackSize());
            stm.setObject(4, item.getUnitPrice());
            stm.setObject(5, item.getQtyOnHand());

            return  stm.executeUpdate()>0;





        } catch (SQLIntegrityConstraintViolationException e) {

            new Alert(Alert.AlertType.ERROR, "Duplicate ID: " + item.getItemCode()).show();
        } catch (SQLException e) {

            new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).show();
        }
        return false;
    }

    @Override
    public boolean updateCustomer(Item item) {
        Connection connection = null;
        try {
            connection = DBConnection.getINSTANCE().getConnection();

            PreparedStatement stm = connection.prepareStatement("UPDATE item SET Description = ?, PackSize = ?, UnitPrice = ?, QtyOnHand = ? WHERE ItemCode = ?");
            stm.setObject(5, item.getItemCode());
            stm.setObject(1, item.getDescription());
            stm.setObject(2, item.getPackSize());
            stm.setObject(3, item.getUnitPrice());
            stm.setObject(4, item.getQtyOnHand());

           // int i = stm.executeUpdate();
            return stm.executeUpdate()>0;



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean dateleCustomer(String Id) {
        Connection connection = null;
        try {
            connection = DBConnection.getINSTANCE().getConnection();

            PreparedStatement stm = connection.prepareStatement("DELETE FROM item WHERE ItemCode = ?");
            stm.setObject(1,Id);
           return stm.executeUpdate()>0;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Item searchCustomer(String Id) {

        Connection connection = null;
        try {
            connection = DBConnection.getINSTANCE().getConnection();

            PreparedStatement stm = connection.prepareStatement("SELECT * FROM item WHERE ItemCode = ?");
            stm.setObject(1, Id);
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                Item item = new Item(Id,
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getInt(5)
                );

                return item;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
