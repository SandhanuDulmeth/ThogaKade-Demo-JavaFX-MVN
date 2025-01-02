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


    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    @FXML
    private JFXTextField TxtAddress;

    @FXML
    private JFXTextField TxtId;

    @FXML
    private JFXTextField TxtName;

    @FXML
    private JFXTextField TxtSalary;

    @FXML
    private TableView<Item> tblCustomer;

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {


        if (ItemController.getInstance().saveCustomer(new Item(
                TxtId.getText(),
                TxtName.getText(),
                TxtAddress.getText(),
                Double.parseDouble(TxtSalary.getText())
        ))
        ) {
            new Alert(Alert.AlertType.INFORMATION, "Added " + TxtId.getText()).show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Not Added " + TxtId.getText()).show();
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

        Item item = ItemController.getInstance().searchCustomer(TxtId.getText());
        if (!(item ==null)){

            TxtName.setText(item.getName());
            TxtAddress.setText(item.getAddress());
            TxtSalary.setText(String.valueOf(item.getSalary()));
        }else{
            TxtName.setText(null);
            TxtAddress.setText(null);
            TxtSalary.setText(null);
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


        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        loadTable();
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, olddValue, newValue) -> {
            if(newValue!=null){

                setTextToValues(newValue);
            }


        });


    }

    private void  setTextToValues(Item item){
        TxtId.setText(item.getId());
        TxtName.setText(item.getName());
        TxtAddress.setText(item.getAddress());
        TxtSalary.setText(item.getSalary()+"");

    }
}
