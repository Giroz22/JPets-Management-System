package com.jpets.app.controllers;

import java.util.HashMap;
import java.util.Map;

import com.jpets.app.dtos.response.PetResponse;
import com.jpets.infrastructure.abstract_service.IPetService;
import com.jpets.infrastructure.service.PetService;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class PetController {
    
    private IPetService petService;
    private FormPetController formPetController;
    
    @Getter
    private Node petNode;

    @FXML
    public Pane petPane;

    @FXML
    public TableView<PetResponse> tableData;

    public PetController(){        
        this.petService = new PetService();
        this.formPetController = new FormPetController(petService);
    }

    public PetController(IPetService petService){
        this.petService = petService;
    }

    @FXML
    public void initialize(){
        this.initializeTable();

        // Handle row selection event
        tableData.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                formPetController.showInfoPet(newValue.getId());            
            }
        });
    }

    @FXML
    public void showFormCreate(){
        formPetController.showCreatePet();
    }

    private void initializeTable(){
        Map<String,String> columns = new HashMap<>();
        columns.put("name", "Nombre");
        columns.put("ownerName", "Propietario");

        new TableDataController<PetResponse>(
            this.tableData, 
            columns,
            this.petService.getAll()
        );
    }
}
