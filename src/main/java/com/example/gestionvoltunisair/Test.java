package com.example.gestionvoltunisair;


import DAO.Connexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Connexion c=new Connexion();
        c.setUser("root");
        c.setPassWord("");
        c.seConnecter();
        Parent root = FXMLLoader.load(getClass().getResource("gestion-avions.fxml"));
        primaryStage.setTitle("gestion des avions Tunisair");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
