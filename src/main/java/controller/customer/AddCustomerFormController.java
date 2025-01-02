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
import javafx.scene.control.TableColumn;
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
    public TableColumn colCustID;
    public TableColumn colCustTitle;
    public TableColumn colCustName;
    public TableColumn colDOB;
    public TableColumn colsalary;
    public TableColumn colCustAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostalCode;
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
    public void btnAddOnAction(ActionEvent event) {


        if (ComboBoxTitle.getValue() != null) {
            if (CustomerController.getInstance().addCustomer(new Customer(TxtId.getText(),
                    (String) ComboBoxTitle.getValue(),
                    TxtName.getText(),
                    Date.valueOf(DatePickerDOB.getValue()),
                    Double.parseDouble(TxtSalary.getText()),
                    TxtAddress.getText(), TxtCity.getText(),
                    TxtProvince.getText(),
                    TxtPostalCode.getText())))
            {
                new Alert(Alert.AlertType.INFORMATION, "Added").show();
                clearAddForm();
                loadTable();
            } else {
                new Alert(Alert.AlertType.ERROR, "Not Added").show();
            }

        } else {
            new Alert(Alert.AlertType.ERROR, "Please Select a Title").show();
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
        TxtName.setText(null);
        TxtAddress.setText(null);
        DatePickerDOB.setValue(null);
        TxtSalary.setText(null);
        TxtAddress.setText(null);
        TxtCity.setText(null);
        TxtProvince.setText(null);
        TxtPostalCode.setText(null);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustID.setCellValueFactory(new PropertyValueFactory<>("CustID"));
        colCustTitle.setCellValueFactory(new PropertyValueFactory<>("CustTitle"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("CustName"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        colsalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory<>("CustAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("City"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("Province"));
        colPostalCode.setCellValueFactory(new PropertyValueFactory<>("PostalCode"));
        loadTable();
        clearAddForm();
        ComboBoxTitle.setItems(FXCollections.observableArrayList("Mr.", "Mrs.", "Miss", "Ms"));

    }

    public void btnSearchRemoveOnAction(ActionEvent actionEvent) {


        if ( CustomerController.getInstance().deleteCustomer(TxtId.getText())   ) new Alert(Alert.AlertType.INFORMATION, "Removed " + TxtId1.getText()).show();
        else new Alert(Alert.AlertType.INFORMATION, "Not Removed " + TxtId1.getText()).show();

//        Connection connection = null;
//        int i = 0;
//        try {
//            connection = DBConnection.getINSTANCE().getConnection();
//
//            PreparedStatement stm = connection.prepareStatement("DELETE FROM customer WHERE CustID = ?");
//            stm.setObject(1, TxtId1.getText());
//            i = stm.executeUpdate();
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        if (i > 0) new Alert(Alert.AlertType.INFORMATION, "Removed " + TxtId1.getText()).show();
//        else new Alert(Alert.AlertType.INFORMATION, "Not Removed " + TxtId1.getText()).show();

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
        loadTable();


    }

    public void OnSreachKeyReleased(KeyEvent keyEvent) {

        CustomerController.getInstance().searchCustomer(TxtId.getText());


    }

    private void loadTable() {
        tblCustomer.getItems().clear();

        tblCustomer.setItems(CustomerController.getInstance().getAll());
    }
}
