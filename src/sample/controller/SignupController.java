package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mariabenetgarcia on 19.01.2018.
 */
public class SignupController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXPasswordField signupPassword;

    @FXML
    private JFXButton signupButton;

    @FXML
    private JFXTextField signupFirstName;

    @FXML
    private JFXTextField signupLastName;

    @FXML
    private JFXTextField signupUsername;

    @FXML
    private JFXTextField signupCountry;

    @FXML
    private JFXCheckBox signupMale;

    @FXML
    private JFXCheckBox signupFemale;
    @FXML
    private JFXButton backButton;


    @FXML
    void initialize() {

        signupButton.setOnAction(event ->{
                    createUser();
                });

        backButton.setOnAction(event->{
            backButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/login.fxml"));
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
    private void createUser(){

        DatabaseHandler databaseHandler = new DatabaseHandler();

        String name = signupFirstName.getText();
        String lastname = signupLastName.getText();
        String username = signupUsername.getText();
        String password = signupPassword.getText();
        String country = signupCountry.getText();

        String gender = "";
        if(signupFemale.isSelected()){
            gender = "Female";
        }else gender = "Male";

        User newuser = new User(name, lastname, username, password,country, gender);

        databaseHandler.signUpUser(newuser);


    }
}
