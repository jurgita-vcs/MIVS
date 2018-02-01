package vcs.lt.fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import vcs.lt.model.User;
import vcs.lt.service.UserService;

import java.io.IOException;


public class LoginController {
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;

    public UserService userService = new UserService();

    public void login(ActionEvent actionEvent) throws IOException {
        userService.findAllUsers();
        User user = userService.login();

        if (userName.getText().isEmpty()) {
            //showAlert(Alert.AlertType.ERROR,  Pane.getScene().getWindow(), "Form Error!", "Please enter your name");
        }

        userName.getText();
        password.getText();

        if (user == null) {
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin.fxml"));

        Stage stage = new Stage();
        stage.setTitle("Administration panel");
        stage.setScene(new Scene(fxmlLoader.load()));

        AdminController adminController = fxmlLoader.getController();
        adminController.init(user);

        stage.show();

        Stage currentScene = (Stage) loginButton.getScene().getWindow();
        currentScene.close();
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
