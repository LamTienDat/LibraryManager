package com.example.librarymanage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BorrowerViewController implements Initializable {

    public TableView<TableModel> table;
    public TableColumn<TableModel, String> cardID;
    public TableColumn<TableModel, String> customerID;
    public TableColumn<TableModel, String> bookID;
    public TableColumn<TableModel, String> borrowDate;
    public TableColumn<TableModel, String> returnDate;
    @FXML
    private Button btnEditBorrower;

    @FXML
    private TextField textSearchBorrowID;

    @FXML
    private Label labelBorrowerName;

    @FXML
    private Label labelAddress;

    @FXML
    private Button btnSearchBorrower;

    @FXML
    private Label labelPhoneNumber;

    @FXML
    private Button btnBorrowerExit;
    @FXML
    private Button btnDeleteBorrower;
    @FXML
    void ExitBorrower(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("menu-view.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.centerOnScreen();
        stage.setScene(scene);
    }


    @FXML
    void EditBorrower(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("edit-borrower-view.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.centerOnScreen();
        stage.setScene(scene);
    }

    @FXML
    void GotoSearchBorrower(ActionEvent event) {
        try {
            customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            bookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
            borrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
            returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

            ResultSet res = DBConnect.executeQuery("select * from borrowlist where customerID like '%" + textSearchBorrowID.getText() + "%'");
            table.getItems().clear();
            while (res.next()) {
                list.add(new TableModel(res.getString(1), res.getString(2),
                        res.getString(3), res.getString(4)));
            }
            table.setItems(list);

            ResultSet res2 = DBConnect.executeQuery("select * from customers where customerID like '%"
                    + textSearchBorrowID.getText() +"%'");
            while(res2.next()) {
                labelBorrowerName.setText(res2.getString(2));
                labelAddress.setText(res2.getString(3));
                labelPhoneNumber.setText(res2.getString(4));
            }
            if(textSearchBorrowID.getText().length() <= 0) {
                labelBorrowerName.setText("");
                labelAddress.setText("");
                labelPhoneNumber.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void GotoEditBorrower(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("edit-borrower-view.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.centerOnScreen();
        stage.setScene(scene);
    }

    @FXML
    void GotoDeleteBorrower(ActionEvent event) {
        String customerid = table.getSelectionModel().getSelectedItem().getCustomerID();
        String deleteQuery1 = "DELETE FROM customers WHERE customers.customerID = " + customerid + ";";
        String deleteQuery2 = "DELETE FROM borrowlist WHERE borrowlist.customerID = " + customerid + ";";
        DBConnect.executeUpdate(deleteQuery1);
        DBConnect.executeUpdate(deleteQuery2);
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
    }
    ObservableList<TableModel> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            bookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
            borrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
            returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

            ResultSet res = DBConnect.executeQuery("select * from borrowlist");
            while (res.next()) {
                list.add(new TableModel(res.getString(1), res.getString(2),
                        res.getString(3), res.getString(4)));
            }
            table.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SearchIDkeytype(KeyEvent keyEvent) {
        try {
            customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            bookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
            borrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
            returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

            ResultSet res = DBConnect.executeQuery("select * from borrowlist where customerID like '%" + textSearchBorrowID.getText() + "%'");
            table.getItems().clear();
            while (res.next()) {
                list.add(new TableModel(res.getString(1), res.getString(2),
                        res.getString(3), res.getString(4)));
            }
            table.setItems(list);

            ResultSet res2 = DBConnect.executeQuery("select * from customers where customerID like '%"
                    + textSearchBorrowID.getText() +"%'");
            while(res2.next()) {
                labelBorrowerName.setText(res2.getString(2));
                labelAddress.setText(res2.getString(3));
                labelPhoneNumber.setText(res2.getString(4));
            }
            if(textSearchBorrowID.getText().length() <= 0) {
                labelBorrowerName.setText("");
                labelAddress.setText("");
                labelPhoneNumber.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
