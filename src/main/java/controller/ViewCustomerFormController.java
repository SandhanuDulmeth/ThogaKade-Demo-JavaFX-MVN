package controller;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Customer;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewCustomerFormController implements Initializable {


    public JFXTextField txtid;


    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<Customer> tblCus;
     List<Customer> customerList= DBConnection.getINSTANCE().getConnection();
    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loardTable();

    }
public void loardTable(){
    colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
    colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
    colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
    colSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));

    ObservableList<Customer> objects = FXCollections.observableArrayList();
    customerList.forEach(obj->objects.add(obj));
    tblCus.setItems(objects);

}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblCus.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->{

            setValues(newValue);
        } );

    }
    private void setValues(Customer customer){

        txtid.setText(customer.getId());

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtid.getText();
        customerList.forEach(customer -> {
            if (customer.getId().equals(id)){
                customer.setId(id);
            }
        });
        loardTable();
    }
}
