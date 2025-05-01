package Controller;

import Classes.Avion;
import DAO.Connexion;
import DAO.DAOAvion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AvionController implements Initializable {

    // TableView et colonnes
    @FXML private TableView<Avion> aircraftTable;
    @FXML private TableColumn<Avion, String> registrationColumn;
    @FXML private TableColumn<Avion, String> marqueColumn;
    @FXML private TableColumn<Avion, String> modelColumn;
    @FXML private TableColumn<Avion, Integer> capacityColumn;
    // Champs de formulaire
    @FXML private TextField registrationField;
    @FXML private TextField modelField;
    @FXML private TextField marqueField;
    @FXML private TextField capacityField;
    @FXML private CheckBox disponibleCheckBox;

    // Avion sélectionné
    private Avion avionSelectionne = null;


    // Filtrage et recherche
    @FXML private ComboBox<String> filterComboBox;
    @FXML private TextField searchField;

    // Données
    private ObservableList<Avion> avionList = FXCollections.observableArrayList();
    private Connection connection = Connexion.seConnecter();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTableColumns();
        setupFilterOptions();
        loadAvionData();
        setupTableSelectionListener(); // Nouvelle méthode
    }

    @FXML
    private TableColumn<Avion, Boolean> typeColumn; // Boolean au lieu de String

    // 2. Modifiez la configuration des colonnes
    private void setupTableColumns() {
        registrationColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        marqueColumn.setCellValueFactory(new PropertyValueFactory<>("marque"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("modele"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacite"));


        // Configuration corrigée pour la colonne disponibilité
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("disponible"));
        typeColumn.setCellFactory(col -> new TableCell<Avion, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item ? "Disponible" : "Indisponible");
                }
            }
        });
    }

    private void setupFilterOptions() {
        filterComboBox.getItems().addAll("Tous", "Disponibles", "Indisponibles");
        filterComboBox.setValue("Tous");
    }
    private void loadAvionData() {
        System.out.println("Chargement des données...");
        avionList.clear();
        ArrayList<Avion> avions = DAOAvion.lister();
        System.out.println("Nombre d'avions chargés: " + avions.size());
        avionList.addAll(avions);
        aircraftTable.setItems(avionList);
    }

    @FXML
    private void handleLogout() {
        // Implémentez la logique de déconnexion
        System.out.println("Déconnexion demandée");
    }

    @FXML
    private void handleDashboard() {
        // Implémentez la navigation vers le dashboard
        System.out.println("Navigation vers le dashboard");
    }

    @FXML
    private void handleFlights() {
        // Implémentez la navigation vers la gestion des vols
        System.out.println("Navigation vers la gestion des vols");
    }

    @FXML
    private void handleAircraft() {
        // Implémentez la navigation vers la gestion des avions
        System.out.println("Navigation vers la gestion des avions");
    }

    @FXML
    private void handleCrew() {
        // Implémentez la navigation vers la gestion des équipages
        System.out.println("Navigation vers la gestion des équipages");
    }

    @FXML
    private void handleScheduling() {
        // Implémentez la navigation vers la programmation des vols
        System.out.println("Navigation vers la programmation des vols");
    }



    @FXML
    private void handleEdit() {
        // Implémentez l'édition
        System.out.println("Édition demandée");
    }


    @FXML
    private void handleSearch() {
        String keyword = searchField.getText();
        String filter = filterComboBox.getValue();

        ArrayList<Avion> resultats = DAOAvion.chercherAvion(keyword);
        ObservableList<Avion> filteredList = FXCollections.observableArrayList();

        for (Avion avion : resultats) {
            boolean matchesFilter = filter.equals("Tous") ||
                    (filter.equals("Disponibles") && avion.isDisponible()) ||
                    (filter.equals("Indisponibles") && !avion.isDisponible());

            if (matchesFilter) {
                filteredList.add(avion);
            }
        }

        aircraftTable.setItems(filteredList);
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void setupTableSelectionListener() {
        aircraftTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    avionSelectionne = newSelection;
                    if (newSelection != null) {
                        fillFormWithSelectedAvion();
                    }
                });
    }

    private void fillFormWithSelectedAvion() {
        if (avionSelectionne != null) {
            registrationField.setText(avionSelectionne.getMatricule());
            marqueField.setText(avionSelectionne.getMarque());
            modelField.setText(avionSelectionne.getModele());
            capacityField.setText(String.valueOf(avionSelectionne.getCapacite()));
            disponibleCheckBox.setSelected(avionSelectionne.isDisponible());
        }
    }

    private boolean validateForm() {
        // Vérifiez d'abord que tous les champs sont remplis
        if (registrationField.getText().isEmpty() ||
                marqueField.getText().isEmpty() ||
                modelField.getText().isEmpty() ||
                capacityField.getText().isEmpty()) {

            showAlert("Erreur", "Veuillez remplir tous les champs", Alert.AlertType.ERROR);
            return false;
        }

        // Validez spécifiquement le champ capacité
        try {
            String capaciteText = capacityField.getText().trim();
            // Vérifiez que le champ ne contient pas le label par erreur
            if (capaciteText.equalsIgnoreCase("Capacité")) {
                showAlert("Erreur", "Veuillez entrer une valeur numérique pour la capacité", Alert.AlertType.ERROR);
                return false;
            }

            Integer.parseInt(capaciteText);
        } catch (NumberFormatException e) {
            showAlert("Erreur", "La capacité doit être un nombre valide", Alert.AlertType.ERROR);
            return false;
        }

        return true;
    }

    private void clearForm() {
        if (registrationField != null) registrationField.clear();
        if(marqueField != null) marqueField.clear();
        if (modelField != null) modelField.clear();
        if (capacityField != null) capacityField.clear();
        if (disponibleCheckBox != null) disponibleCheckBox.setSelected(false);

        avionSelectionne = null;
        if (aircraftTable != null) {
            aircraftTable.getSelectionModel().clearSelection();
        }
    }
    @FXML
    private void handleSave() {
        if (!validateForm()) return;

        try {
            String matricule = registrationField.getText();

            // Vérification existence matricule seulement pour nouvel avion
            if (avionSelectionne == null && DAOAvion.existeMatricule(matricule)) {
                showAlert("Erreur", "La matricule existe déjà ! Impossible d'ajouter.", Alert.AlertType.ERROR);
                return;
            }

            Avion avion = new Avion(
                    matricule,
                    marqueField.getText(),
                    modelField.getText(),
                    Integer.parseInt(capacityField.getText()),
                    disponibleCheckBox.isSelected()
            );

            if (avionSelectionne != null) {
                if (DAOAvion.modifier(avion)) {
                    showAlert("Succès", "Avion modifié avec succès", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Erreur", "Échec de la modification", Alert.AlertType.ERROR);
                }
            }
            // Sinon -> AJOUT
            else {
                if (DAOAvion.ajouter(avion)) {
                    showAlert("Succès", "Avion ajouté avec succès", Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Erreur", "Échec de l'ajout", Alert.AlertType.ERROR);
                }
            }

            loadAvionData(); // Recharger les données
            clearForm(); // Vider le formulaire

        } catch (NumberFormatException e) {
            showAlert("Erreur", "Capacité invalide", Alert.AlertType.ERROR);
        }
    }

    private void refreshTableData() {
        List<Avion> nouvellesDonnees = DAOAvion.lister();
        avionList.clear();
        avionList.addAll(nouvellesDonnees);
        aircraftTable.refresh();
        System.out.println("Table rafraîchie. Nombre d'éléments: " + avionList.size());
    }
    @FXML
    private void handleDelete() {
        if (avionSelectionne != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation de suppression");
            confirmation.setHeaderText(null);
            confirmation.setContentText("Êtes-vous sûr de vouloir supprimer cet avion ?");

            if (confirmation.showAndWait().get() == ButtonType.OK) {
                if (DAOAvion.supprimer(avionSelectionne)) {
                    showAlert("Succès", "Avion supprimé avec succès", Alert.AlertType.INFORMATION);
                    loadAvionData();
                    clearForm();
                } else {
                    showAlert("Erreur", "Échec de la suppression de l'avion", Alert.AlertType.ERROR);
                }
            }
        } else {
            showAlert("Erreur", "Veuillez sélectionner un avion à supprimer", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void handleClear() {
        clearForm();
    }
}