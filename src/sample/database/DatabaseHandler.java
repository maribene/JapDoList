package sample.database;

import sample.model.Task;
import sample.model.User;

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
    public void signUpUser(User user){

        String insert = "INSERT INTO " +Const.USERS_TABLE + "(" +Const.USERS_FIRSTNAME
                +","+Const.USERS_LASTNAME+","+Const.USERS_USERNAME+","
                +Const.USERS_PASSWORD+","+Const.USERS_COUNTRY+","
                +Const.USERS_GENDER+")" + "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement =  getDbConnection().prepareStatement(insert);
            preparedStatement.setString( 1, user.getFirstname());
            preparedStatement.setString( 2, user.getLastname());
            preparedStatement.setString( 3, user.getUsername());
            preparedStatement.setString( 4, user.getPassword());
            preparedStatement.setString( 5, user.getCountry());
            preparedStatement.setString( 6, user.getGender());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    public ResultSet getUser(User user){
        ResultSet resultSet = null;

        if(!user.getUsername().equals("")||!user.getPassword().equals("")){
            String query = "SELECT * FROM "+Const.USERS_TABLE + " WHERE "
                    + Const.USERS_USERNAME + "=?" + " AND " + Const.USERS_PASSWORD
                    + "=?";
            try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());

            resultSet = preparedStatement.executeQuery();

            }catch(SQLException e){
                e.printStackTrace();
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }

        }else{
            System.out.println("Please enter your credentials");
        }
        return resultSet;
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

}
