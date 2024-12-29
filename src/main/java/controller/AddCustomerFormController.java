package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    @FXML
    private JFXComboBox ComboBoxTitle;

    @FXML
    private DatePicker DatePickerDOB;

    @FXML
    private JFXTextField TxtAddress;

    @FXML
    private JFXTextField TxtCity;

    @FXML
    private JFXTextField TxtId;

    @FXML
    private JFXTextField TxtName;

    @FXML
    private JFXTextField TxtPostalCode;

    @FXML
    private JFXTextField TxtProvince;

    @FXML
    private JFXTextField TxtSalary;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ComboBoxTitle.setItems(FXCollections.observableArrayList("Mr.","Mrs.","Miss","Ms"));
    }
}
