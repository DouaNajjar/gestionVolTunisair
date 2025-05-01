package Controller;


import DAO.DAOUser;
import Classes.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController extends MainController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleLogin() {
        String email = usernameField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Erreur", "Veuillez remplir tous les champs", javafx.scene.control.Alert.AlertType.ERROR);
            return;
        }

        User user = DAOUser.authenticate(email, password);

        if (user != null) {
            currentUser = user;
            //loadView("/tn/tunisair/views/dashboard.fxml", "Tableau de bord");
            // Fermer la fenêtre de login
            ((Stage) usernameField.getScene().getWindow()).close();
        } else {
            showAlert("Échec", "Identifiants incorrects", javafx.scene.control.Alert.AlertType.ERROR);
        }
    }
}
