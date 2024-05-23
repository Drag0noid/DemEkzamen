package application;

import DAO.ClientDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Client;

public class Login {
    @FXML
    private TextField loginField;
    @FXML
    private TextField passwordField;

    @FXML
    private void handleSubmitButtonAction() {
        String login = loginField.getText();
        String password = passwordField.getText();

        Client client = new Client(login, password);
        ClientDAO clientDAO = new ClientDAO();
        try {
            clientDAO.registerUser(client);
            System.out.println("Client added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
