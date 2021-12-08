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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CustomerViewController implements Initializable {
    @FXML
    private Button btnDeleteCustomer;
    @FXML
    private Button btnExitCus;
    @FXML
    private Button btnEditCustomer;
    @FXML
    public TableView<TableModel4> table;
    public TableColumn<TableModel4, String> Address;
    public TableColumn<TableModel4, Integer> Amount;
    public TableColumn<TableModel4, String> customerID;
    public TableColumn<TableModel4, String> PhoneNumber;
    public TableColumn<TableModel4, String> Name;
    @FXML
    void GotoEditCus(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("edit-customer-view.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.centerOnScreen();
        stage.setScene(scene);
    }

    @FXML
    void GotoDeleteCus(ActionEvent event) {
        String cusid = table.getSelectionModel().getSelectedItem().getCustomerID();
        String deleteQuery = "DELETE FROM customers WHERE customerID = " + cusid + ";";
        DBConnect.executeUpdate(deleteQuery);
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
    }

    @FXML
    void GotoExitCus(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("menu-view.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.centerOnScreen();
        stage.setScene(scene);
    }

    ObservableList<TableModel4> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
            Address.setCellValueFactory(new PropertyValueFactory<>("Address"));
            PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("PhoneNumber"));
            Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
            ResultSet res = DBConnect.executeQuery("SELECT c.customerID, c.Name, c.Address, c.PhoneNumber, COUNT(b.bookID) bookborrowed\n" +
                    "FROM `customers` c\n" +
                    "LEFT JOIN borrowlist b \n" +
                    "ON c.customerID = b.customerID\n" +
                    "GROUP by c.customerID;");
            while (res.next()) {
                list.add(new TableModel4(res.getString(1), res.getString(2),
                        res.getString(3), res.getString(4), res.getInt(5)));
            }
            table.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
