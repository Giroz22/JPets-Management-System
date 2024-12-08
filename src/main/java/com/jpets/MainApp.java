package com.jpets;

import com.jpets.config.SQLiteConnection;
import com.jpets.utils.ViewUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;

public class MainApp extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {  
        
        //Se conecta a la DB
        SQLiteConnection.connect();

        Parent root = FXMLLoader.load(getClass().getResource("/views/home/Home.fxml"));
        
        //Obtenemos el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //Calculamos el tamaño que tendra la ventana
        double stageWidth = screenSize.getWidth() * 0.8;
        double stageHeight = screenSize.getHeight() * 0.8;

        //Obtenemos los valores que debe tener la pantalla para estar centrada
        Map<String, Double> centerPosition = ViewUtil.getCenterScreen(stageWidth, stageHeight); 

        //Asignamos valores que tendra la pantalla principal
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
        stage.setX(centerPosition.get("x"));
        stage.setY(centerPosition.get("y"));
        stage.setTitle("JPets");
        stage.setScene(new Scene(root));
        stage.show();
    }
}