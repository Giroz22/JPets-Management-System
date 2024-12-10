package com.jpets.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ViewUtil {
    public Scene getScene(String pathFXML, Object controller) {
        Scene scene;

        try {
            //Se carga la vista 
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathFXML));

            //Se le asigna el controlador
            loader.setController(controller);
            
            Parent root = loader.load();
            scene = new Scene(root);      

        } catch (Exception e) {
            VBox vbox = new VBox(new Label("No se encontro la vista en la ruta: " + pathFXML));
            scene = new Scene(vbox);
        }

        return scene;
    };

    public static Map<String, Double> getCenterScreen(double viewWidth, double viewHeight){
        Map<String, Double> position = new HashMap<>();

        //Obtenemos el tamaño de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight =  screenSize.getHeight();

        position.put("x", (screenWidth - viewWidth) / 2);
        position.put("y", (screenHeight - viewHeight) / 2);

        return position;
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
}
