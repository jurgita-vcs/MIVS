package vcs.lt.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import vcs.lt.model.User;

public class AdminController {
    @FXML
    private TextField userName;

    private User user;

    public void init(User user) {
        this.user = user;
        this.userName.setText(user.getUserName());
    }
}
