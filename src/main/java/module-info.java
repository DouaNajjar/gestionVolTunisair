module com.example.gestionvoltunisair {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mariadb.jdbc;
    requires java.sql;


    exports com.example.gestionvoltunisair;



    // Open packages for FXMLLoader reflection
    opens Controller to javafx.fxml;
    opens Classes to javafx.base,javafx.graphics,javafx.fxml;
    opens com.example.gestionvoltunisair to javafx.fxml, javafx.graphics;
}