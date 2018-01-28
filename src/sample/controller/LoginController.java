package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.animations.Shaker;
import sample.database.DatabaseHandler;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton loginLoginButton;

    @FXML
    private JFXButton loginSignupButton;

    @FXML
    private JFXTextField loginUsernameTextfield;

    @FXML
    private JFXPasswordField loginPasswordfield;

    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();

        loginLoginButton.setOnAction(event -> {

            String loginText = loginUsernameTextfield.getText().trim();
            String loginPwd = loginPasswordfield.getText().trim();

            User user = new User();
            user.setUsername(loginText);
            user.setPassword(loginPwd);

            ResultSet userRow = databaseHandler.getUser(user);
            int counter = 0;

            try {
                while (userRow.next()) {
                    counter++;
                    String name = userRow.getString("firstname");
                    System.out.println("Welcome! "+ name);

                }
                if(counter == 1) {

                    showAddItemScreen();

                }else{
                    Shaker usernameshaker = new Shaker(loginUsernameTextfield);
                    Shaker pwdshaker = new Shaker(loginPasswordfield);
                    usernameshaker.shake();
                    pwdshaker.shake();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });

        loginSignupButton.setOnAction(event -> {

            loginSignupButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signup.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

        });

    }

    private void showAddItemScreen(){

       // loginLoginButton.setOnAction(event -> {

            loginLoginButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/addItem.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

       // });
    }

}


