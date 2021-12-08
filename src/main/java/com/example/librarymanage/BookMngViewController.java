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
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookMngViewController implements Initializable {

    public TableView<TableModel3> table;
    public TableColumn<TableModel3, String> bookID;
    public TableColumn<TableModel3, String> bookName;
    public TableColumn<TableModel3, String> author;
    public TableColumn<TableModel3, String> publisher;
    public TableColumn<TableModel3, Integer> amount;

    @FXML
    private Button btnBookGo;

    @FXML
    private Button btnAddBook;
    @FXML
    private Button btnBookExit;
    @FXML
    private TextField txtBookSearch;
    @FXML
    private Button btnDeleteBook;
    @FXML
    void DeleteBook(ActionEvent event) throws IOException {
        String bookid = table.getSelectionModel().getSelectedItem().getBookID();
        String deleteQuery = "DELETE FROM book WHERE bookID = " + bookid + ";";
        DBConnect.executeUpdate(deleteQuery);
        table.getItems().remove(table.getSelectionModel().getSelectedItem());
    }

    @FXML
    void BookExit(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("menu-view.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.centerOnScreen();
        stage.setScene(scene);
    }
    @FXML
    void GotoSearch(ActionEvent event) throws IOException {
        try {
            bookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
            bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            author.setCellValueFactory(new PropertyValueFactory<>("author"));
            publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

            ResultSet res = DBConnect.executeQuery("select * from book where bookID like '%" + txtBookSearch.getText() + "%'");
            table.getItems().clear();
            while (res.next()) {
                    list.add(new TableModel3(res.getString(1), res.getString(2),
                            res.getString(3), res.getString(4), res.getInt(5)));
            }
            table.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void AddBook(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("add-book-view.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.centerOnScreen();
        stage.setScene(scene);
    }

    ObservableList<TableModel3> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            bookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
            bookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
            author.setCellValueFactory(new PropertyValueFactory<>("author"));
            publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));
            amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

            ResultSet res = DBConnect.executeQuery("select * from book");
            while (res.next()) {
                list.add(new TableModel3(res.getString(1), res.getString(2),
                        res.getString(3), res.getString(4), res.getInt(5)));
            }
            table.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
