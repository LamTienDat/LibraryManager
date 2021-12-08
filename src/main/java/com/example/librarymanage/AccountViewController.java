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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AccountViewController implements Initializable {
    public TableView<TableModel2> acc_table;
    public TableColumn<TableModel2, String> admin_id;
    public TableColumn<TableModel2, String> password;
    public TableColumn<TableModel2, String> admin_name;
    public TableColumn<TableModel2, String> admin_phone_num;

    @FXML
    private Button btnExit_Acc;


    @FXML
    void Acc_Exit(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("menu-view.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        stage.centerOnScreen();
        stage.setScene(scene);
    }
    ObservableList<TableModel2> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            admin_id.setCellValueFactory(new PropertyValueFactory<>("admin_id"));
            password.setCellValueFactory(new PropertyValueFactory<>("password"));
            admin_name.setCellValueFactory(new PropertyValueFactory<>("admin_name"));
            admin_phone_num.setCellValueFactory(new PropertyValueFactory<>("admin_phone_num"));

            ResultSet res = DBConnect.executeQuery("select * from manage");
            while (res.next()) {
                list.add(new TableModel2(res.getString(1), res.getString(2),
                        res.getString(3), res.getString(4)));
            }
            acc_table.setItems(list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
