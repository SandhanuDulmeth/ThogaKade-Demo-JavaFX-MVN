package controller.item;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import model.Item;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ItemFormController  implements Initializable {


    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public JFXTextField TxtUnitPrice;
    @FXML
    private JFXTextField TxtDescription;

    @FXML
    private JFXTextField TxtItemCode;

    @FXML
    private JFXTextField TxtPackSize;

    @FXML
    private JFXTextField TxtQtyOnHand;

    @FXML
    private TableView<Item> tblCustomer;

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {


        if (ItemController.getInstance().saveCustomer(new Item(
                TxtItemCode.getText(),
                TxtDescription.getText(),
                TxtPackSize.getText(),
                Double.parseDouble(TxtUnitPrice.getText()),
               Integer.parseInt(TxtQtyOnHand.getText())

        ))
        ) {
            new Alert(Alert.AlertType.INFORMATION, "Added " + TxtItemCode.getText()).show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Not Added " + TxtItemCode.getText()).show();
        }
        loadTable();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {


        if( ItemController.getInstance().dateleCustomer(TxtItemCode.getText())){
            new Alert(Alert.AlertType.INFORMATION, "Removed " + TxtItemCode.getText()).show();
       TxtDescription.setText(null);
        TxtPackSize.setText(null);
        TxtUnitPrice.setText(null);
        TxtUnitPrice.setText(null);
       TxtQtyOnHand.setText(null);

        loadTable();

        }else{

            new Alert(Alert.AlertType.INFORMATION, "Not Removed ").show();
        }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

        Item item = ItemController.getInstance().searchCustomer(TxtItemCode.getText());
        if (!(item ==null)){
            TxtDescription.setText(item.getDescription());
            TxtPackSize.setText(item.getPackSize());
            TxtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
            TxtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
        }else{
            TxtPackSize.setText(null);
            TxtDescription.setText(null);
            TxtQtyOnHand.setText(null);
            TxtUnitPrice.setText(null);
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
//        stm.setObject(5, TxtItemCode.getText());
//        stm.setObject(1, TxtDescription.getText());
//        stm.setObject(2, TxtPackSize.getText());
//        stm.setObject(3, TxtUnitPrice.getText());
//        stm.setObject(4, TxtQtyOnHand.getText());



        if ( ItemController.getInstance().updateCustomer(
                new Item( TxtItemCode.getText(),
                        TxtDescription.getText(),
                        TxtPackSize.getText(),
                        Double.parseDouble(TxtUnitPrice.getText()) ,
                        Integer.parseInt(TxtQtyOnHand.getText())
                ))) {
            new Alert(Alert.AlertType.INFORMATION, "Updated " ).show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Not Updated " ).show();
        }


        loadTable();

    }


    private void loadTable(){
        tblCustomer.getItems().clear();

        List<Item> all = ItemController.getInstance().getAll();

        ObservableList<Item> customerObservableList = FXCollections.observableArrayList();
        customerObservableList.addAll(all);

        tblCustomer.setItems(customerObservableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        loadTable();
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, olddValue, newValue) -> {
            if(newValue!=null){

                setTextToValues(newValue);
            }


        });


    }

    private void  setTextToValues(Item item){
        TxtItemCode.setText(item.getItemCode());
        TxtPackSize.setText(item.getPackSize());
        TxtDescription.setText(item.getDescription());
        TxtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        TxtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));

    }
}
