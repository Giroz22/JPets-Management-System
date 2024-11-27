package com.jpets;

import com.jpets.home.HomeView;
import com.jpets.utils.ViewUtil;

import javafx.application.Application;
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

        HomeView homeView = new HomeView();
        Scene scene = homeView.getMyScene();
        
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
        stage.setScene(scene);
        stage.show();
    }
}