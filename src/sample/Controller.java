package sample;

import database.DataSource;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button login;
    @FXML
    private Button signUp;
    @FXML
    private TextArea username;
    @FXML
    private PasswordField password;

    public void checkValidUser(){
        String user = username.getText();
        String password1 = password.getText();
        if(DataSource.getInstance().checkLoginQueryByUserName(user) && DataSource.getInstance().checkLoginQueryByPassword(password1)){
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Success");
            confirmation.setHeaderText("Logged In Successfully");

            confirmation.getButtonTypes().add(ButtonType.OK);
            Optional<ButtonType> result = confirmation.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                confirmation.close();
            }
        }else{
            Alert rejection = new Alert(Alert.AlertType.WARNING);
            rejection.setTitle("Failed");
            rejection.setHeaderText("Failed To Login");
            rejection.setContentText("Check Your UserName and Password. Or Signup New Account");

            rejection.getButtonTypes().add(ButtonType.OK);
            Optional<ButtonType> result = rejection.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                rejection.close();
            }
        }
    }

    public void openSignupPage(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(borderPane.getScene().getWindow());
        dialog.setTitle("Sign Up Form");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("signup.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            SignupController controller = fxmlLoader.getController();
            User user = controller.addUser();
            if(DataSource.getInstance().insertNewUser(user)){
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setTitle("Success");
                confirmation.setHeaderText("Signed Up Successfully");

                confirmation.getButtonTypes().add(ButtonType.OK);
                Optional<ButtonType> result1 = confirmation.showAndWait();
                if(result1.isPresent() && result1.get() == ButtonType.OK){
                    confirmation.close();
                }
            }else{
                Alert rejection = new Alert(Alert.AlertType.WARNING);
                rejection.setTitle("Failed");
                rejection.setHeaderText("Failed To Sign Up");
                rejection.setContentText("Error Occurred");

                rejection.getButtonTypes().add(ButtonType.OK);
                Optional<ButtonType> result1 = rejection.showAndWait();
                if(result1.isPresent() && result1.get() == ButtonType.OK){
                    rejection.close();
                }
            }
        }

    }
}
