package controller.placeOrder;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class PlaceOrderFormController {

    @FXML
    private JFXComboBox ComboBoxItemCode;

    @FXML
    private JFXTextField TxtDescription;

    @FXML
    private JFXTextField TxtPacksize;

    @FXML
    private JFXTextField TxtQtyOnHand;

    @FXML
    private JFXTextField TxtUnitPrice;

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colQTY;

    @FXML
    private TableColumn colTotal;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    void btnADDOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {

        Stage stage=new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/add_Customer_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
