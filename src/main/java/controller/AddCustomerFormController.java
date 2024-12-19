package controller;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Customer;

import java.util.List;

public class AddCustomerFormController {

    @FXML
    private JFXTextField TxtAddress;

    @FXML
    private JFXTextField TxtId;

    @FXML
    private JFXTextField TxtName;

    @FXML
    private JFXTextField TxtSalary;

    @FXML
    void btnAddOnAction(ActionEvent event) {


        List<Customer> connectionArray = DBConnection.getINSTANCE().getConnection();
        connectionArray.add(new Customer(TxtId.getText(),TxtName.getText(),TxtAddress.getText(),TxtSalary.getText()));

        clearTxt();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearTxt();
    }

    private void clearTxt() {
        TxtId.setText(null);
        TxtName.setText(null);
        TxtAddress.setText(null);
        TxtSalary.setText(null);
    }
}
