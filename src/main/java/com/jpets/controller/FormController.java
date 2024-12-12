package com.jpets.controller;

import com.jpets.controller.dtos.request.PetRequests;
import com.jpets.service.abstract_service.IPetService;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FormController {
    
    private PetRequests petRequests;
    private IPetService petService;

    @FXML
    Button btnCreatePet;

    @FXML
    TextField textNamePet;

    @FXML
    TextField textNameOwner;

    public FormController(IPetService petService){
        this.petService = petService;
    }

    public void createPet(){
        this.petRequests = PetRequests.builder()
        .name(textNamePet.getText())
        .ownerName(textNameOwner.getText())
        .build();

        this.petService.create(petRequests);
        System.out.println(petRequests.getName());
        System.out.println(petRequests.getOwnerName());
    }    
}
