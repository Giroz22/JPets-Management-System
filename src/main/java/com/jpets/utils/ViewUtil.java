package com.jpets.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ViewUtil {
    public Scene getScene(String pathFXML, Object controller) {
        Scene scene = null;

        try {
            //Se carga la vista 
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathFXML));

            //Se le asigna el controlador
            loader.setController(controller);
            
            Parent root = loader.load();
            scene = new Scene(root);      

        }
        // catch(IOException e){
        //     VBox vbox = new VBox(new Label("No se encontro la vista en la ruta: " + pathFXML));
        //     scene = new Scene(vbox);
        //     e.getStackTrace();

        // }
        catch (Exception e) {
            e.printStackTrace();
        }

        return scene;
    };

    public static void centerStageAndSetSize(Stage stage, double stageWidthPercentage, double stageHeightPercentage){
        //Obtenemos el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight =  screenSize.getHeight();

        //Calculamos el tamaño que tendra la ventana
        double stageWidth = screenSize.getWidth() * (stageWidthPercentage / 100);
        double stageHeight = screenSize.getHeight() * (stageHeightPercentage / 100);

        //Calculamos la posicion central de la pantalla
        double positionX = ((screenWidth - stageWidth) / 2);
        double positionY = ((screenHeight - stageHeight) / 2);

        //Asignamos el tamaño y la posicion que tendra el stage
        stage.setWidth(stageWidth);
        stage.setHeight(stageHeight);
        stage.setX(positionX);
        stage.setY(positionY);
    }

    public Node getNodeFXML(String urlFXML, Object controller){        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(urlFXML));

            loader.setController(controller);        

            return loader.load();        

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //Continuar y ponerle a los repositorios
    public static void showAlert(String title, String headerText, String msj, AlertType alertType){
        // Crear y mostrar una alerta de tipo INFORMACION
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);  // Cabecera opcional
        alert.setContentText(msj);
        alert.showAndWait(); 
    }

    public static void showErrorAlert(String headerText, String msj){
        showAlert("Error", headerText, msj, AlertType.ERROR);
    }
}
