package sample;

import java.util.PrimitiveIterator;

/**
 * Created By: Naman Agarwal
 * User ID: naman2807
 * Package Name: sample
 * Project Name: JavaFXLoginSignUp
 * Date: 09-02-2021
 */

public class User {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String userName;
    private String password;

    public User(String firstName, String lastName, String phoneNumber, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
