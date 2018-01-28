package sample.database;

import sample.model.Task;

import java.sql.*;

/**
 * Created by mariabenetgarcia on 19.01.2018.
 */
public class DatabaseHandler  extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://"+dbHost+":"
                + dbPort + "/"
                + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString+ "?useSSL=false", dbUser,dbPass);

        return dbConnection;
    }


    public void taskintoDatabase(Task task){

        String insert = "INSERT INTO " +Const.TASKS_TABLE + "("+ Const.TASKS_TASK
                +","+Const.TASKS_DATE + "," +Const.TASKS_DESCRIPTION+")" + "VALUES(?,?,?)";

        try {
            PreparedStatement statement =  getDbConnection().prepareStatement(insert);
            statement.setString( 1,task.getTask());
            statement.setString( 2, task.getDatecreated());
            statement.setString( 3, task.getDescription());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public void removeTaskFromDatabase(Task task) {
        try {
            Connection conn = getDbConnection();
            conn.createStatement().executeUpdate("DELETE FROM "+ Const.TASKS_TABLE+ " WHERE task = "+ "\"" + task.getTask() + "\"");

        } catch(SQLException e) {
            System.err.println("Error " + e);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
