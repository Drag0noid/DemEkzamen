package application;

import java.io.IOException;
import java.sql.SQLException;

import DAO.ClientDAO;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import models.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField regUsernameField;
    @FXML
    private PasswordField regPasswordField;

    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        Client client = new Client(username, password);
        ClientDAO clientDAO = new ClientDAO();
        try {
            if (clientDAO.authenticateUser(client)) {
                showAlert(AlertType.INFORMATION, "Вы успешная залогинились", "Приветствую, " + username + "!");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
                BorderPane root = (BorderPane) loader.load();
                Stage stage = (Stage) usernameField.getScene().getWindow();
                stage.setScene(new Scene(root, 600, 400));
            } else {
                showAlert(AlertType.ERROR, "Ошибка", "Неверный логин или пароль.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRegisterButtonAction() {
        String username = regUsernameField.getText();
        String password = regPasswordField.getText();
        Client client = new Client(username, password);

        ClientDAO clientDAO = new ClientDAO();
        try {
            clientDAO.registerUser(client);
            showAlert(AlertType.INFORMATION, "Успешная регистрация", "Теперь вы можете залогиниться под этим аккаунтом");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Sample.fxml"));
            BorderPane root = (BorderPane) loader.load();
            Stage stage = (Stage) regUsernameField.getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                showAlert(AlertType.ERROR, "Ошибка", "Пользователь уже существует");
            } else {
                e.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
