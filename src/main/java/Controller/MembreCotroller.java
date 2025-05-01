
package Controller;

import Classes.Employe;
import Classes.MembreEquipage;
import Classes.Pilote;
import DAO.DAOMembreEquipage;
import DAO.DAOPilote;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MembreCotroller implements Initializable {
    @FXML
    private TableColumn pilotePhoneColumn;
    @FXML
    private TableColumn crewPhoneColumn;
    @FXML
    private TextField crewPhone;
    @FXML
    private TextField pilotePhone;
    @FXML
    private Label userNameLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private Button dashboardButton;
    @FXML
    private Button flightsButton;
    @FXML
    private Button aircraftButton;
    @FXML
    private Button crewButton;
    @FXML
    private Button schedulingButton;
    @FXML
    private TextField pilotIdField;
    @FXML
    private TextField pilotLastNameField;
    @FXML
    private TextField pilotFirstNameField;
    @FXML
    private Button clearPilotButton;
    @FXML
    private Button savePilotButton;
    @FXML
    private ComboBox<String> pilotFilterComboBox;
    @FXML
    private TextField pilotSearchField;
    @FXML
    private Button pilotSearchButton;
    @FXML
    private TableView<Employe> pilotsTable;
    @FXML
    private TableColumn<Employe, String> pilotIdColumn;
    @FXML
    private TableColumn<Employe, String> pilotLastNameColumn;
    @FXML
    private TableColumn<Employe, String> pilotFirstNameColumn;
    @FXML
    private TableColumn<Employe, Boolean> pilotAvailabilityColumn;
    @FXML
    private Button editPilotButton;
    @FXML
    private Button deletePilotButton;
    @FXML
    private TextField crewIdField;
    @FXML
    private TextField crewLastNameField;
    @FXML
    private TextField crewFirstNameField;
    @FXML
    private Button clearCrewButton;
    @FXML
    private Button saveCrewButton;
    @FXML
    private ComboBox<String> crewFilterComboBox;
    @FXML
    private TextField crewSearchField;
    @FXML
    private Button crewSearchButton;
    @FXML
    private Button editCrewButton;
    @FXML
    private Button deleteCrewButton;
    @FXML
    private Tab piloteWindow;
    @FXML
    private TextField PilotEmailField;
    @FXML
    private DatePicker pilotHireDateField;
    @FXML
    private CheckBox PilotAvailibilityField;
    @FXML
    private TableColumn<Employe, String> pilotEmailColumn;
    @FXML
    private TableColumn<Employe, Date> pilotHireDateColumn;
    @FXML
    private Tab crewMemberWindow;
    @FXML
    private DatePicker crewHireDateField;
    @FXML
    private CheckBox crewAvailibilityField;
    @FXML
    private TextField crewEmailField;
    @FXML
    private TableView<Employe> crewTable;
    @FXML
    private TableColumn<Employe, String> crewIdColumn;
    @FXML
    private TableColumn<Employe, String> crewLastNameColumn;
    @FXML
    private TableColumn<Employe, String> crewFirstNameColumn;
    @FXML
    private TableColumn<Employe, String> crewEmailColumn;
    @FXML
    private TableColumn<Employe, Date> crewHireDateColumn;
    @FXML
    private TableColumn<Employe, Boolean> crewAvailabilityColumn;

    private final ObservableList<Employe> piloteData = FXCollections.observableArrayList();
    private final ObservableList<Employe> crewData = FXCollections.observableArrayList();
    private Employe selectedPilot = null;
    private Employe selectedCrewMember = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initializePilotTable();
            initializeCrewTable();
            loadPilots();
            loadCrewMembers();
            setupComboBoxes();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Initialization Error",
                    "Failed to initialize: " + e.getMessage());
        }
    }

    private void setupComboBoxes() {
        ObservableList<String> filterOptions = FXCollections.observableArrayList(
                "ID", "Nom", "Prénom", "Email"
        );
        pilotFilterComboBox.setItems(filterOptions);
        crewFilterComboBox.setItems(filterOptions);
        pilotFilterComboBox.setValue("ID");
        crewFilterComboBox.setValue("ID");
    }

    private void initializePilotTable() {
        setTableColumnProperties(pilotIdColumn, pilotLastNameColumn, pilotFirstNameColumn,
                pilotEmailColumn, pilotHireDateColumn, pilotAvailabilityColumn, pilotePhoneColumn);
        pilotsTable.setItems(piloteData);
        setupPilotTableSelection();
    }

    private void initializeCrewTable() {
        setTableColumnProperties(crewIdColumn, crewLastNameColumn, crewFirstNameColumn,
                crewEmailColumn, crewHireDateColumn, crewAvailabilityColumn, crewPhoneColumn);
        crewTable.setItems(crewData);
        setupCrewTableSelection();
    }

    private void setTableColumnProperties(TableColumn<Employe, String> idCol,
                                          TableColumn<Employe, String> lastNameCol,
                                          TableColumn<Employe, String> firstNameCol,
                                          TableColumn<Employe, String> emailCol,
                                          TableColumn<Employe, Date> hireDateCol,
                                          TableColumn<Employe, Boolean> availabilityCol,
                                          TableColumn<Employe, String> phoneCol) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        hireDateCol.setCellValueFactory(new PropertyValueFactory<>("dateEmbauche"));
        availabilityCol.setCellValueFactory(new PropertyValueFactory<>("disponibilite"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
    }

    private void setupPilotTableSelection() {
        pilotsTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    selectedPilot = newSelection;
                    if (newSelection != null) {
                        showPilotDetails(newSelection);
                    }
                });
    }

    private void setupCrewTableSelection() {
        crewTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    selectedCrewMember = newSelection;
                    if (newSelection != null) {
                        showCrewDetails(newSelection);
                    }
                });
    }

    @FXML
    public void handleSavePilot(ActionEvent event) {
        if (!validatePilotFields()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error",
                    "Please fill all required fields");
            return;
        }

        try {
            Pilote pilote = createPiloteFromFields();
            boolean success = selectedPilot == null ?
                    DAOPilote.ajouter(pilote) :
                    DAOPilote.modifier(pilote);

            if (success) {
                loadPilots();
                clearPilotFields();
                showAlert(Alert.AlertType.INFORMATION, "Success",
                        selectedPilot == null ? "Pilot added" : "Pilot updated");
                selectedPilot = null;
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error",
                    "Error saving pilot: " + e.getMessage());
        }
    }

    @FXML
    public void handleSaveCrew(ActionEvent event) {
        if (!validateCrewFields()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error",
                    "Please fill all required fields");
            return;
        }

        try {
            MembreEquipage membre = createCrewMemberFromFields();
            boolean success = selectedCrewMember == null ?
                    DAOMembreEquipage.ajouter(membre) :
                    DAOMembreEquipage.modifier(membre);

            if (success) {
                loadCrewMembers();
                clearCrewFields();
                showAlert(Alert.AlertType.INFORMATION, "Success",
                        selectedCrewMember == null ? "Crew member added" : "Crew member updated");
                selectedCrewMember = null;
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error",
                    "Error saving crew member: " + e.getMessage());
        }
    }

    private Pilote createPiloteFromFields() {
        return new Pilote(
                pilotIdField.getText(),
                pilotLastNameField.getText(),
                pilotFirstNameField.getText(),
                PilotEmailField.getText(),
                Date.valueOf(pilotHireDateField.getValue()),
                PilotAvailibilityField.isSelected(),
                pilotePhone.getText()
        );
    }

    private MembreEquipage createCrewMemberFromFields() {
        return new MembreEquipage(
                crewIdField.getText(),
                crewLastNameField.getText(),
                crewFirstNameField.getText(),
                crewEmailField.getText(),
                Date.valueOf(crewHireDateField.getValue()),
                crewAvailibilityField.isSelected(),
                crewPhone.getText()
        );
    }

    @FXML
    public void handlePilotSearch(ActionEvent event) {
        String searchText = pilotSearchField.getText();
        piloteData.clear();
        piloteData.addAll(searchText.isEmpty() ?
                DAOPilote.lister() :
                DAOPilote.chercherPilotes(searchText));
    }

    @FXML
    public void handleCrewSearch(ActionEvent event) {
        String searchText = crewSearchField.getText();
        crewData.clear();
        crewData.addAll(searchText.isEmpty() ?
                DAOMembreEquipage.lister() :
                DAOMembreEquipage.chercherMembres(searchText));
    }


    private void loadPilots() {
        piloteData.clear();
        piloteData.addAll(DAOPilote.lister());
    }

    private void loadCrewMembers() {
        crewData.clear();
        crewData.addAll(DAOMembreEquipage.lister());
    }

    @FXML
    public void handleClearPilot(ActionEvent event) {
        clearPilotFields();
        selectedPilot = null;
    }


    private boolean validatePilotFields() {
        return !pilotIdField.getText().isEmpty() &&
                !pilotLastNameField.getText().isEmpty() &&
                !pilotFirstNameField.getText().isEmpty() &&
                !PilotEmailField.getText().isEmpty() &&
                pilotHireDateField.getValue() != null;
    }

    private boolean validateCrewFields() {
        return !crewIdField.getText().isEmpty() &&
                !crewLastNameField.getText().isEmpty() &&
                !crewFirstNameField.getText().isEmpty() &&
                !crewEmailField.getText().isEmpty() &&
                crewHireDateField.getValue() != null;
    }

    @FXML
    public void handleDeletePilot(ActionEvent event) {
        if (selectedPilot == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a pilot to delete");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this pilot?",
                ButtonType.YES, ButtonType.NO);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                if (DAOPilote.supprimer((Pilote) selectedPilot)) {
                    loadPilots();
                    clearPilotFields();
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Pilot deleted successfully");
                }
            }
        });
    }

    @FXML
    public void handleDeleteCrew(ActionEvent event) {
        if (selectedCrewMember == null) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Please select a crew member to delete");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are you sure you want to delete this crew member?",
                ButtonType.YES, ButtonType.NO);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.YES) {
                if (DAOMembreEquipage.supprimer((MembreEquipage) selectedCrewMember)) {
                    loadCrewMembers();
                    clearCrewFields();
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Crew member deleted successfully");
                }
            }
        });
    }

    private void clearPilotFields() {
        pilotIdField.clear();
        pilotLastNameField.clear();
        pilotFirstNameField.clear();
        PilotEmailField.clear();
        pilotHireDateField.setValue(null);
        PilotAvailibilityField.setSelected(false);
        pilotePhone.clear();
    }

    private void clearCrewFields() {
        crewIdField.clear();
        crewLastNameField.clear();
        crewFirstNameField.clear();
        crewEmailField.clear();
        crewHireDateField.setValue(null);
        crewAvailibilityField.setSelected(false);
        crewPhone.clear();
    }

    private void showPilotDetails(Employe pilot) {
        pilotIdField.setText(pilot.getCin());
        pilotLastNameField.setText(pilot.getNom());
        pilotFirstNameField.setText(pilot.getPrenom());
        PilotEmailField.setText(pilot.getEmail());
        pilotHireDateField.setValue(pilot.getDateEmbauche().toLocalDate());
        PilotAvailibilityField.setSelected(pilot.getDisponibilite());
        pilotePhone.setText(pilot.getNum_tel());
    }

    private void showCrewDetails(Employe crewMember) {
        crewIdField.setText(crewMember.getCin());
        crewLastNameField.setText(crewMember.getNom());
        crewFirstNameField.setText(crewMember.getPrenom());
        crewEmailField.setText(crewMember.getEmail());
        crewHireDateField.setValue(crewMember.getDateEmbauche().toLocalDate());
        crewAvailibilityField.setSelected(crewMember.getDisponibilite());
        crewPhone.setText(crewMember.getNum_tel());
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }





    @FXML
    public void switchToPilotWindow(Event event) {
        loadPilots();
    }

    @FXML
    public void switchToCrewMemberWindow(Event event) {
        loadCrewMembers();
    }

    @FXML
    public void handleEditPilot(ActionEvent event) {
        if (selectedPilot != null) {
            showPilotDetails(selectedPilot);
        }
    }

    @FXML
    public void handleEditCrew(ActionEvent event) {
        if (selectedCrewMember != null) {
            showCrewDetails(selectedCrewMember);
        }
    }

    // Navigation methods
    @FXML
    public void handleLogout(ActionEvent event) {
        // Implement logout logic
    }

    @FXML
    public void handleDashboard(ActionEvent event) {
        // Implement navigation to dashboard
    }

    @FXML
    public void handleFlights(ActionEvent event) {
        // Implement navigation to flights
    }

    @FXML
    public void handleAircraft(ActionEvent event) {
        // Implement navigation to aircraft
    }

    @FXML
    public void handleCrew(ActionEvent event) {
        // Currently on crew page
    }

    @FXML
    public void handleScheduling(ActionEvent event) {
        // Implement navigation to scheduling
    }

    public void handleClearCrew(ActionEvent actionEvent) {
    }
}