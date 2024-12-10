package com.jpets.controller;

import com.jpets.models.StoreEntity;
import com.jpets.repository.StoreRepository;
import com.jpets.service.StoreService;
import com.jpets.utils.ViewUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class HomeController {

    private StoreService storeService;

    private ViewUtil viewUtil;

    public HomeController(){
        this.storeService = new StoreService(new StoreRepository());  
        this.viewUtil = new ViewUtil();
    }

    @FXML
    TabPane tabPane;

    @FXML
    public void initialize() {
        tabPane.getSelectionModel().select(0); 
        Tab selectedTab = tabPane.getSelectionModel().getSelectedItem();

        StoreEntity store = storeService.getStore();

        //Cargar Inicio
        VBox myvbox = new VBox();
        myvbox.getChildren().addAll(
            new Label("Bienvenido a " + store.getName() + ", desde la" + selectedTab.getText())
        );
        selectedTab.setContent(myvbox);
        
        //En base al tab se muestra una vista u otra
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if(newTab != null){     
                switch (newTab.getId()) {
                    case "petsTab":                        
                        newTab.setContent(
                            viewUtil.getNodeFXML("/views/pets/Pets.fxml", new PetController())
                        );                        
                        break;

                    case "storeTab":
                        newTab.setContent(
                            new Label("Estamos en tienda")
                        );

                        break;
                
                    default:
                        newTab.setContent(new Label("Sin datos"));
                        break;
                }
            }
        });
    }
    
}
