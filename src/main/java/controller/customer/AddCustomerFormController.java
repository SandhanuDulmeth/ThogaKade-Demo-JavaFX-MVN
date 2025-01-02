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
    public JFXTextField TxtId11;
    public JFXTextField TxtName11;
    public JFXTextField TxtAddress11;
    public JFXTextField TxtSalary11;
    public JFXTextField TxtCity11;
    public JFXTextField TxtProvince11;
    public JFXTextField TxtPostalCode11;
    public JFXTextField TxtTitle11;
    public JFXTextField TxtDate11;
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
                    TxtPostalCode.getText()))) {
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


        if (CustomerController.getInstance().deleteCustomer(TxtId1.getText()))
            new Alert(Alert.AlertType.INFORMATION, "Removed " + TxtId1.getText()).show();
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
        loadTable();


    }

    public void OnSreachKeyReleased(KeyEvent keyEvent) {

        Customer customer = CustomerController.getInstance().searchCustomer(TxtId1.getText());

        if (null != customer) {
            TxtTitle1.setText(customer.getCustTitle());
            TxtName1.setText(customer.getCustName());
            TxtDate1.setText(String.valueOf(customer.getDOB()));
            TxtSalary1.setText(String.valueOf(customer.getSalary()));
            TxtAddress1.setText(customer.getCustAddress());
            TxtCity1.setText(customer.getCity());
            TxtProvince1.setText(customer.getProvince());
            TxtPostalCode1.setText(customer.getPostalCode());
        }else{
            TxtTitle1.setText(null);
            TxtName1.setText(null);
            TxtDate1.setText(null);
            TxtSalary1.setText(null);
            TxtAddress1.setText(null);
            TxtCity1.setText(null);
            TxtProvince1.setText(null);
            TxtPostalCode1.setText(null);

        }
    }

    private void loadTable() {
        tblCustomer.getItems().clear();

        tblCustomer.setItems(CustomerController.getInstance().getAll());
    }

    public void btnSearchUpdateOnAction(ActionEvent actionEvent) {

        if (CustomerController.getInstance().UpdateCustomer(new Customer(
                TxtId.getText(),
                (String) ComboBoxTitle.getValue(),
                TxtName.getText(),
                Date.valueOf(DatePickerDOB.getValue()),
                Double.parseDouble(TxtSalary.getText()),
                TxtAddress.getText(), TxtCity.getText(),
                TxtProvince.getText(),
                TxtPostalCode.getText()
        ))) {
            new Alert(Alert.AlertType.INFORMATION, "Updated " ).show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Not Updated " ).show();
        }
//        Connection connection = null;
//        try {
//            connection = DBConnection.getINSTANCE().getConnection();
//
//            PreparedStatement stm = connection.prepareStatement("UPDATE book SET Title = ?, Author = ?, PublishedYear = ?, Genre = ?, Price = ? WHERE BookID = ?");
//            stm.setObject(6, txtId2.getText());
//            stm.setObject(1, txtTitle2.getText());
//            stm.setObject(2, txtAuthor2.getText());
//            stm.setObject(3, txtpublishedYear2.getText());
//            stm.setObject(4, txtGenre2.getText());
//            stm.setObject(5, txtPrice2.getText());
//            int i = stm.executeUpdate();
//            if (i > 0) {
//                new Alert(Alert.AlertType.INFORMATION, "Updated " ).show();
//            } else {
//                new Alert(Alert.AlertType.ERROR, "Not Updated " ).show();
//            }
//
//
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
//        }


//        txtId2.setText(null);
//        txtTitle2.setText(null);
//        txtAuthor2.setText(null);
//        txtpublishedYear2.setText(null);
//        txtGenre2.setText(null);
//        txtPrice2.setText(null);
        loadTable();
    }

    public void OnSreachUpdateKeyReleased(KeyEvent keyEvent) {
        Customer customer = CustomerController.getInstance().searchCustomer(TxtId11.getText());

        if (null != customer) {
            TxtTitle11.setText(customer.getCustTitle());
            TxtName11.setText(customer.getCustName());
            TxtDate11.setText(String.valueOf(customer.getDOB()));
            TxtSalary11.setText(String.valueOf(customer.getSalary()));
            TxtAddress11.setText(customer.getCustAddress());
            TxtCity11.setText(customer.getCity());
            TxtProvince11.setText(customer.getProvince());
            TxtPostalCode11.setText(customer.getPostalCode());
        }else{
            TxtTitle11.setText(null);
            TxtName11.setText(null);
            TxtDate11.setText(null);
            TxtSalary11.setText(null);
            TxtAddress11.setText(null);
            TxtCity11.setText(null);
            TxtProvince11.setText(null);
            TxtPostalCode11.setText(null);

        }
    }
}
