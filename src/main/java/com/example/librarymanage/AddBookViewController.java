package com.example.librarymanage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class AddBookViewController {
    @FXML
    private TextField txtAddPublisher;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnAddBookExit;

    @FXML
    private Button btnEditBookMng;

    @FXML
    private TextField txtAddAmount;

    @FXML
    private TextField txtAddBookID;

    @FXML
    private TextField txtAddAuthor;

    @FXML
    private TextField txtAddBookName;

    @FXML
    void AddBookExit(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("book-mng-view.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.centerOnScreen();
        stage.setScene(scene);
    }
    @FXML
    void AddNewBook(ActionEvent event) throws SQLException {
        if(txtAddBookID.getText().length() <= 0 || txtAddAmount.getText().length() <= 0 ||
        txtAddPublisher.getText().length() <= 0 || txtAddAuthor.getText().length() <= 0 ||
        txtAddBookName.getText().length() <= 0) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText("Fill all content");
            al.show();
            return;
        }
        String query = "select bookID from book where bookID ='" +txtAddBookID.getText()+"';";
        ResultSet res = DBConnect.executeQuery(query);
        if(res != null && res.next()) {
//            Notifications.create()
//                    .text("BookID is existed!")
//                    .showError();
//            return;
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText("BookID is existed!");
            al.show();
            return;
        }

        query = "INSERT INTO `book`(`bookID`, `bookName`, `author`, `publisher`, `amount`) VALUES ('"
                + txtAddBookID.getText()+" ','"+ txtAddBookName.getText() +"','" + txtAddAuthor.getText() + "','"
                +txtAddPublisher.getText() + "','" + txtAddAmount.getText() + "')";
        DBConnect.executeUpdate(query);
//        Notifications.create()
//                .text("Add success!")
//                .showInformation();
        Alert al = new Alert(Alert.AlertType.ERROR);
        al.setHeaderText("Add success!");
        al.show();
        return;
    }
    @FXML
    void GotoEditMng(ActionEvent event) throws IOException, SQLException {
        String query = "select bookID from book where bookID ='" +txtAddBookID.getText()+"';";
        ResultSet res = DBConnect.executeQuery(query);
        if(res != null && res.next()) {
            String query2 = "UPDATE `book` SET `bookID` = '" + txtAddBookID.getText()+
                    "', `bookName` = '" + txtAddBookName.getText() + "', `author` = '"+
                    txtAddAuthor.getText() + "', `publisher` = '" + txtAddPublisher.getText() +
                    "', `amount` = '" + txtAddAmount.getText() + "' WHERE `book`.`bookID` = '" + txtAddBookID.getText() +"';";
            DBConnect.executeUpdate(query2);
//            Notifications.create()
//                    .text("Edit success!")
//                    .showInformation();
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText("Edit success!");
            al.show();
            return;
        }

    }

}
