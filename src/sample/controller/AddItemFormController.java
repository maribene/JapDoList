package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.DatabaseHandler;
import sample.model.Task;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by mariabenetgarcia on 22.01.2018.
 */


public class AddItemFormController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField taskField;

    @FXML
    private JFXTextField descriptionField;

    @FXML
    private JFXButton saveTaskButton;


    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();

        saveTaskButton.setOnAction(event -> {
            createTask();
            addItemScreen();
        });
    }
    private void createTask() {

        String task = taskField.getText();
        String description = descriptionField.getText();
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String datecreated = sdf.format(dt);

        Task newtask = new Task(datecreated, description, task);

        databaseHandler.taskintoDatabase(newtask);

    }

    private void addItemScreen(){

        saveTaskButton.getScene().getWindow().hide();
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

    }

}

