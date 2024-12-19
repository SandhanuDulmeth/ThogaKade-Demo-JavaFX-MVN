package controller;

import db.DBConnection;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewCustomerFormController implements Initializable {

    public Label lblId;
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

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        List<Customer> customerList= DBConnection.getINSTANCE().getConnection();
        ObservableList<Customer> objects = FXCollections.observableArrayList();
        customerList.forEach(obj->
                objects.add(obj));
        tblCus.setItems(objects);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblCus.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->{
            System.out.println(newValue);
            lblId.setText(newValue.getId());
        } );

    }
}
