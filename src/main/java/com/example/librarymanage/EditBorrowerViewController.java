package com.example.librarymanage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;

public class EditBorrowerViewController {

    @FXML
    private TextField txtEditBorrowerPhoneNum;

    @FXML
    private Button btnEditBorrower;
    @FXML
    private Button btnExitBorrower;
    @FXML
    private TextField txtEditBorrowerID;

    @FXML
    private TextField txtEditBorrowerBookID;

    @FXML
    private TextField txtEditBorrowerAddress;

    @FXML
    private TextField txtEditBorrowerName;
    @FXML
    private TextField txtEditBorrowerCardID;
    @FXML
    private DatePicker txtEditBorrowerBorrowDate;

    @FXML
    private DatePicker txtEditBorrowerReturnDate;

    @FXML
    void EditBorrower(ActionEvent event) {
        if(txtEditBorrowerID.getText().length() <= 0 ||txtEditBorrowerBookID.getText().length() <=0 ||
                txtEditBorrowerBorrowDate.getValue().toString().length() <= 0 ||
                txtEditBorrowerReturnDate.getValue().toString().length() <= 0) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText("Fill all content");
            al.show();
            return;
        }

        String query = "INSERT INTO `borrowlist`(`customerID`, `bookID`, `borrowDate`, `returnDate`) " +
                "VALUES ('" +txtEditBorrowerID.getText() + "','" + txtEditBorrowerBookID.getText() + "','" + txtEditBorrowerReturnDate.getValue() +
                "','" + txtEditBorrowerReturnDate.getValue() +"')";
        DBConnect.executeUpdate(query);


//        Notifications.create()
//                .text("Add success!")
//                .showInformation();
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setHeaderText("Add success!");
        al.show();
    }

    @FXML
    void ExitEditBorrower(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("borrower-view.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.centerOnScreen();
        stage.setScene(scene);
    }

}
