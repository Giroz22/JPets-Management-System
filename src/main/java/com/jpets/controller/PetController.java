package com.jpets.controller;

import com.jpets.service.PetService;
import com.jpets.service.abstract_service.IPetService;
import com.jpets.utils.ViewUtil;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class PetController {
    
    private IPetService petService;

    @Getter
    private Node petNode;

    @FXML
    public Pane petPane;

    public PetController(){
        this.petService = new PetService();
    }

    public PetController(IPetService petService){
        this.petService = petService;
    }

    @FXML
    public void initialize(){
        Label label = new Label("Hola desde petController");
        petPane.getChildren().addAll(label);
    }
}
