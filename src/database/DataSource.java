package database;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import sample.User;

import java.sql.*;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: database
 * Project Name: JavaFXLoginSignUp
 * Date: 08-02-2021
 */

public class DataSource {
    private static DataSource datasource;
    private Connection connection;
    private Statement statement;
    private PreparedStatement checkUserNameQuery;
    private PreparedStatement checkPasswordQuery;
    private PreparedStatement insertStatement;
    private String TABLE_NAME = "Records";
    private String LAST_NAME = "Last_Name";
    private String USER_NAME = "User_Name";
    private String PHONE_NUMBER = "Phone_Number";
    private String PASSWORD = "Password";
    private final String QUERY_USERNAME = "SELECT * FROM " + TABLE_NAME + " WHERE " + USER_NAME + " = ?";
    private final String QUERY_PASSWORD = "SELECT * FROM " + TABLE_NAME + " WHERE " + PASSWORD + " = ?";
    private final String INSERT_QUERY = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?,?,?)";

    private DataSource() {
    }

    public static DataSource getInstance() {
        if (datasource == null) {
            return new DataSource();
        }
        return datasource;
    }

    public boolean open() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/records","root","");
            statement = connection.createStatement();
            checkUserNameQuery = connection.prepareStatement(QUERY_USERNAME);
            checkPasswordQuery = connection.prepareStatement(QUERY_PASSWORD);
            insertStatement = connection.prepareStatement(INSERT_QUERY);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if(checkUserNameQuery != null){
                checkUserNameQuery.close();
            }
            if(insertStatement != null){
                insertStatement.close();
            }
            if(checkPasswordQuery != null){
                checkPasswordQuery.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean checkLoginQueryByUserName(String username){
        boolean isValid = false;
        try {
            checkUserNameQuery.setString(1, username);
            System.out.println(checkUserNameQuery.toString());
            ResultSet resultSet = checkUserNameQuery.executeQuery();
            if(resultSet.getFetchSize() > 0){
                isValid = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return isValid;
    }

    public boolean checkLoginQueryByPassword(String password){
        boolean isValid = false;
        try {
            checkPasswordQuery.setString(1, password);
            System.out.println(checkPasswordQuery.toString());
            ResultSet resultSet = checkPasswordQuery.executeQuery();
            if(resultSet.getFetchSize() > 0){
                isValid = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return isValid;
    }

    public boolean insertNewUser(User user){
        boolean inserted = false;
        try {
            insertStatement.setString(1, user.getFirstName());
            insertStatement.setString(2, user.getLastName());
            insertStatement.setString(3, user.getPhoneNumber());
            insertStatement.setString(4, user.getUserName());
            insertStatement.setString(5, user.getPassword());
            insertStatement.executeQuery();
            inserted = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return inserted;
    }

}
