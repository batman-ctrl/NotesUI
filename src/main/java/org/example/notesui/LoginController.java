package org.example.notesui;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void handleLogin(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println("Hello, logging in");
    }
}
