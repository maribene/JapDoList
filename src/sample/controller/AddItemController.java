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

    //@FXML
    //  private ImageView addButton;

    // @FXML
    // private Label addJatask;

    @FXML
    private ImageView flor;

    // @FXML
    // private JFXButton showButton;

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
        //showButton.setOnAction(event -> {
        loadDataFromDatabase();
        //});
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
        /*

        addButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Shaker buttonShaker = new Shaker(addButton);
            buttonShaker.shake();

            FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), addButton);
            FadeTransition labelTransition = new FadeTransition(Duration.millis(2000), addJatask);
            FadeTransition florTransition = new FadeTransition(Duration.millis(2000), flor);

            System.out.println("Added Clicked!");

            fadeTransition.setFromValue(1f);
            fadeTransition.setToValue(0f);
            fadeTransition.setCycleCount(1);
            fadeTransition.setAutoReverse(false);
            fadeTransition.play();

            labelTransition.setFromValue(1f);
            labelTransition.setToValue(0f);
            labelTransition.setCycleCount(1);
            labelTransition.setAutoReverse(false);
            labelTransition.play();

            florTransition.setFromValue(1f);
            florTransition.setToValue(0f);
            florTransition.setCycleCount(1);
            florTransition.setAutoReverse(false);
            florTransition.play();

            try {
                AnchorPane formPane = FXMLLoader.load(getClass().getResource("/sample/view/addItemForm.fxml"));

                rootAnchorPane.getChildren().setAll(formPane);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    */

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

        ObservableList<Task> taskSelected, allTasks;
        allTasks = tableview.getItems();
        taskSelected = tableview.getSelectionModel().getSelectedItems();

        Task task = taskSelected.get(0);

        System.out.println("Task som slettes: " + task.getTask());
        databaseHandler.removeTaskFromDatabase(task);

        taskSelected.forEach(allTasks::remove);

    }
}