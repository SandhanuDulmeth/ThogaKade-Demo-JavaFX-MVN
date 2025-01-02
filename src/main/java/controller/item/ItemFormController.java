package controller.item;

import com.jfoenix.controls.JFXTextField;
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
//        Connection connection = null;
//        try {
//            connection = DBConnection.getInstance().getConnection();
//
//            PreparedStatement stm = connection.prepareStatement("DELETE FROM customer WHERE id = ?");
//            stm.setObject(1, txtId1.getText());
//            stm.executeUpdate();
//
//
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
//        }
//        new Alert(Alert.AlertType.INFORMATION, "Removed " + txtId.getText()).show();
//        txtId1.setText(null);
//        txtTitle1.setText(null);
//        txtAuthor1.setText(null);
//        txtpublishedYear1.setText(null);
//        txtGenre1.setText(null);
//        txtPrice1.setText(null);
//        loadTable();
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
