package com.jpets;

import com.jpets.config.SQLiteConnection;
import com.jpets.utils.HibernateUtil;
import com.jpets.utils.ViewUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {  
        try{

            //Se conecta a la DB
            SQLiteConnection.connect();

            Parent root = FXMLLoader.load(getClass().getResource("/views/home/Home.fxml"));

            //Obtenemos los valores que debe tener la pantalla para estar centrada
            ViewUtil.centerStageAndSetSize(stage, 80, 80); 
            
            stage.setTitle("JPets");
            stage.setScene(new Scene(root));
            stage.show();
        
        } catch (Exception e) {
            ViewUtil.showErrorAlert("Error al ejecutar la aplicacion", e.getMessage());
        }   
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Finalizando la aplicación...");
        HibernateUtil.closeSession();
    }
}