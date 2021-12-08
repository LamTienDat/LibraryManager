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
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField txtID;

    @FXML
    private TextField txtPass;

    @FXML
    private Button btnLog;



    public void Login(ActionEvent event) throws IOException, SQLException {
        if(login(txtID.getText(), txtPass.getText())) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("menu-view.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            stage.centerOnScreen();
            stage.setScene(scene);
        }
    }

    public static boolean login(String user, String password){
        String query = "select * from manage";
        ResultSet rs = DBConnect.executeQuery(query);
        try {

            while(rs.next()){
                if(rs.getString(1).equals(user)) {
                    if(rs.getString(2).equals(password)) {
//                        Notifications.create()
//                                .text("Login success")
//                                .showInformation();
                        Alert al = new Alert(Alert.AlertType.ERROR);
                        al.setHeaderText("Login success!");
                        al.show();
                        return true;
                    }
                    else {
//                        Notifications.create()
//                                .text("Error password")
//                                .showInformation();
                        Alert al = new Alert(Alert.AlertType.ERROR);
                        al.setHeaderText("Login failed!");
                        al.show();
                        return false;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBConnect.load();
    }
}
