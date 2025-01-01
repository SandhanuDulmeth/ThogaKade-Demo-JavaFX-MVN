package controller.customer;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.Customer;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddCustomerFormController implements Initializable {

    public JFXTextField TxtTitle1;

    public JFXTextField TxtDate1;
    public TableView tblCustomer;
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
    public void btnAddOnAction(ActionEvent event) throws SQLException {
        int i=0;

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
             i = preparedStatement.executeUpdate();

        } else {
            new Alert(Alert.AlertType.ERROR, "Please Select a Title").show();
        }
        if(i>0){
            new Alert(Alert.AlertType.INFORMATION, "Added").show();
            clearAddForm();
        }else{
            new Alert(Alert.AlertType.ERROR, "Not Added").show();
        }
    }

    @FXML
    public void btnClearOnAction(ActionEvent event) {
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
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        loadTable();
        clearAddForm();
        ComboBoxTitle.setItems(FXCollections.observableArrayList("Mr.", "Mrs.", "Miss", "Ms"));

    }

    public void btnSearchRemoveOnAction(ActionEvent actionEvent) {
        Connection connection = null;
        int i=0;
        try {
            connection = DBConnection.getINSTANCE().getConnection();

            PreparedStatement stm = connection.prepareStatement("DELETE FROM customer WHERE CustID = ?");
            stm.setObject(1, TxtId1.getText());
             i = stm.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (i>0) new Alert(Alert.AlertType.INFORMATION, "Removed " + TxtId1.getText()).show();
        else new Alert(Alert.AlertType.INFORMATION, "Not Removed " + TxtId1.getText()).show();

        TxtId1.setText(null);
        TxtTitle1.setText(null);
        TxtName1.setText(null);
       TxtDate1.setText(null);
       TxtSalary1.setText(null);
       TxtAddress1.setText(null);
       TxtCity1.setText(null);
       TxtPostalCode1.setText(null);
       TxtProvince1.setText(null);

        clearAddForm();



    }

    public void OnSreachKeyReleased(KeyEvent keyEvent) {
        Connection connection = null;
        try {
            connection = DBConnection.getINSTANCE().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer WHERE CustID=? ");
            preparedStatement.setString(1,TxtId1.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
               TxtTitle1.setText(resultSet.getString(2));
               TxtName1.setText(resultSet.getString(3));
             TxtDate1.setText(String.valueOf(LocalDate.parse(resultSet.getString(4))));
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
    private void loadTable(){
        tblCustomer.getItems().clear();

        tblCustomer.setItems(CustomerController.getInstance().getAll());
    }
}
