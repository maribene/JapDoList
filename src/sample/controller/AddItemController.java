package sample.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.database.DatabaseHandler;
import sample.model.Task;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by mariabenetgarcia on 19.01.2018.
 */
public class AddItemController {

    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView flor;

    @FXML
    private TableView<Task> tableview;

    @FXML
    private TableColumn<Task, String> taskColumn;

    @FXML
    private TableColumn<Task, String> descriptionColumn;

    @FXML
    private TableColumn<Task, String> dateColumn;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton addButton;

    private ObservableList<Task> list;
    private DatabaseHandler databaseHandler;

    @FXML
    void initialize() {

        databaseHandler = new DatabaseHandler();
        loadDataFromDatabase();

        deleteButton.setOnAction(event -> {
            deleteData();
        });
        addButton.setOnAction(event -> {

            try {
                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/view/addItemForm.fxml"));

                rootAnchorPane.getChildren().setAll(formPane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void loadDataFromDatabase() {
        try {
            Connection conn = databaseHandler.getDbConnection();
            list = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM todo.tasks");

            while (rs.next()) {
                list.add(
                        new Task(rs.getString("datecreated"),
                                rs.getString("description"),
                                rs.getString("task")
                        )
                );

            }

            taskColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("task"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<Task, String>("datecreated"));
            tableview.setItems(null);
            tableview.setItems(list);

        } catch (SQLException e) {
            System.err.println("Error");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void deleteData() {

        ObservableList<Task> selectedTasks, allTasks;
        allTasks = tableview.getItems();
        selectedTasks = tableview.getSelectionModel().getSelectedItems();

        if(selectedTasks.isEmpty()){
            System.out.println("No tasks selected");
        }else{
            Task task = selectedTasks.get(0);

            System.out.println("Task som slettes: " + task.getTask());
            databaseHandler.removeTaskFromDatabase(task);

            selectedTasks.forEach(allTasks::remove);
        }


    }
}