package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.sql.*;
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
    void btnAddOnAction(ActionEvent event) throws SQLException {

        if (ComboBoxTitle.getValue()!=null) {
            Connection connection = DBConnection.getINSTANCE().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, TxtId.getText());
            preparedStatement.setString(2, (String) ComboBoxTitle.getValue());
            preparedStatement.setString(3, TxtName.getText());
            preparedStatement.setDate(4, Date.valueOf(DatePickerDOB.getValue()));
            preparedStatement.setInt(5, Integer.parseInt(TxtSalary.getText()));
            preparedStatement.setString(6, TxtAddress.getText());
            preparedStatement.setString(7, TxtCity.getText());
            preparedStatement.setString(8, TxtProvince.getText());
            preparedStatement.setString(9, TxtPostalCode.getText());
        }else{
            new Alert(Alert.AlertType.ERROR,"Please Select a Title").show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection connection = null;
        try {
            connection = DBConnection.getINSTANCE().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT CustID FROM customer ORDER BY CustID DESC LIMIT 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String nextIndex = String.format("C%03d", Integer.parseInt(resultSet.getString(1).split("[C]")[1])+1);
            TxtId.setText(nextIndex);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




        ComboBoxTitle.setItems(FXCollections.observableArrayList("Mr.","Mrs.","Miss","Ms"));
    }
}
