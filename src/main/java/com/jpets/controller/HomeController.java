package com.jpets.controller;

import com.jpets.models.StoreEntity;
import com.jpets.repository.StoreRepository;
import com.jpets.service.StoreService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class HomeController {

    StoreService storeService;

    public HomeController(){
        this.storeService = new StoreService(new StoreRepository());    
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
                VBox vbox = new VBox();
                vbox.getChildren().addAll(
                    new Label("Bienvenido a " + store.getName() + ", desde la" + newTab.getText())
                );
                newTab.setContent(vbox);
            }
        });
    }
    
}
