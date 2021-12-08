package com.example.librarymanage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditCustomerViewController  {
    @FXML
    private TextField txtCusPhoneNum;

    @FXML
    private Button btnAddDesCus;

    @FXML
    private TextField txtCusName;

    @FXML
    private TextField txtCusNumber;

    @FXML
    private Button btnExitAddCus;

    @FXML
    private TextField txtCusAddress;

    @FXML
    void GotoAddDesCus(ActionEvent event) throws IOException, SQLException {
        if(txtCusNumber.getText().length() <= 0 || txtCusName.getText().length() <= 0 ||
                txtCusAddress.getText().length() <= 0 || txtCusPhoneNum.getText().length() <= 0) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText("Fill all content");
            al.show();
            return;
        }
        String query = "select customerID from customers where customerID ='" +txtCusNumber.getText()+"';";
        ResultSet res = DBConnect.executeQuery(query);
        if(res != null && res.next()) {
//            Notifications.create()
//                    .text("BookID is existed!")
//                    .showError();
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText("BookID is existed!");
            al.show();
            return;
        }

        query = "INSERT INTO `customers`(`customerID`, `Name`, `Address`, `PhoneNumber`) VALUES ('"
                + txtCusNumber.getText()+" ','"+ txtCusName.getText() +"','" + txtCusAddress.getText() + "','"
                +txtCusPhoneNum.getText() +"')";
        DBConnect.executeUpdate(query);
//        Notifications.create()
//                .text("Add success!")
//                .showInformation();
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setHeaderText("Add success!");
        al.show();
    }

    @FXML
    void GotoExitAddCus(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("customer-view.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.centerOnScreen();
        stage.setScene(scene);
    }

}
