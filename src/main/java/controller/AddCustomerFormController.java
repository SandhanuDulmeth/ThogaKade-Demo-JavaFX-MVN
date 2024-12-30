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
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    @FXML
    private JFXComboBox ComboBoxTitle;

    @FXML
    private JFXComboBox ComboBoxTitle1;

    @FXML
    private DatePicker DatePickerDOB;

    @FXML
    private DatePicker DatePickerDOB1;

    @FXML
    private JFXTextField TxtAddress;

    @FXML
    private JFXTextField TxtAddress1;

    @FXML
    private JFXTextField TxtCity;

    @FXML
    private JFXTextField TxtCity1;

    @FXML
    private JFXTextField TxtId;

    @FXML
    private JFXTextField TxtId1;

    @FXML
    private JFXTextField TxtName;

    @FXML
    private JFXTextField TxtName1;

    @FXML
    private JFXTextField TxtPostalCode;

    @FXML
    private JFXTextField TxtPostalCode1;

    @FXML
    private JFXTextField TxtProvince;

    @FXML
    private JFXTextField TxtProvince1;

    @FXML
    private JFXTextField TxtSalary;

    @FXML
    private JFXTextField TxtSalary1;


    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {
        System.out.println("l");
        if (ComboBoxTitle.getValue() != null) {
            Connection connection = DBConnection.getINSTANCE().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, TxtId.getText());
            preparedStatement.setString(2, (String) ComboBoxTitle.getValue());
            preparedStatement.setString(3, TxtName.getText());
            preparedStatement.setDate(4, Date.valueOf(DatePickerDOB.getValue()));
            preparedStatement.setDouble(5, Double.parseDouble(TxtSalary.getText()));
            preparedStatement.setString(6, TxtAddress.getText());
            preparedStatement.setString(7, TxtCity.getText());
            preparedStatement.setString(8, TxtProvince.getText());
            preparedStatement.setString(9, TxtPostalCode.getText());
//           int i = preparedStatement.executeUpdate();
//            if (i > 0) {
//                new Alert(Alert.AlertType.INFORMATION, "Added").show();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Added Fail").show();
//            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please Select a Title").show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearAddForm();
    }

    public void clearAddForm() {
        Connection connection = null;
        try {
            connection = DBConnection.getINSTANCE().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT CustID FROM customer ORDER BY CustID DESC LIMIT 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String nextIndex = String.format("C%03d", Integer.parseInt(resultSet.getString(1).split("[C]")[1]) + 1);
            TxtId.setText(nextIndex);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ComboBoxTitle.setValue(null);
        TxtName.setText(null);TxtAddress.setText(null);DatePickerDOB.setValue(null);TxtSalary.setText(null);TxtAddress.setText(null);TxtCity.setText(null);TxtProvince.setText(null);TxtPostalCode.setText(null);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clearAddForm();
        ComboBoxTitle.setItems(FXCollections.observableArrayList("Mr.", "Mrs.", "Miss", "Ms"));

    }

    public void btnSearchRemoveOnAction(ActionEvent actionEvent) {





    }

    public void OnSreachKeyReleased(KeyEvent keyEvent) {
        Connection connection = null;
        try {
            connection = DBConnection.getINSTANCE().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer WHERE CustID=? ");
            preparedStatement.setString(1,TxtId1.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
               ComboBoxTitle1.setValue(resultSet.getString(2));
               TxtName1.setText(resultSet.getString(3));
              DatePickerDOB.setValue(LocalDate.parse(resultSet.getString(4)));
               TxtSalary1.setText(resultSet.getString(5));
               TxtAddress1.setText(resultSet.getString(6));
               TxtCity1.setText(resultSet.getString(7));
               TxtProvince1.setText(resultSet.getString(8));
               TxtPostalCode1.setText(resultSet.getString(9));

            }




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
