package com.jpets.controller;

import java.util.HashMap;
import java.util.Map;

import com.jpets.controller.dtos.response.PetResponse;
import com.jpets.service.PetService;
import com.jpets.service.abstract_service.IPetService;
import com.jpets.utils.ViewUtil;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;

public class PetController {
    
    private IPetService petService;
    private ViewUtil viewUtil;
    
    @Getter
    private Node petNode;

    @FXML
    public Pane petPane;

    @FXML
    public TableView<PetResponse> tableData;

    public PetController(){        
        this.petService = new PetService();
        this.viewUtil = new ViewUtil();
    }

    public PetController(IPetService petService){
        this.petService = petService;
    }

    @FXML
    public void initialize(){
        this.initializeTable();
    }

    @FXML
    public void showFormCreate(){
        Stage formStage = new Stage();

        Scene scene = viewUtil.getScene("/layouts/FormLayout.fxml", new FormController(this.petService));

        ViewUtil.centerStageAndSetSize(formStage, 40, 70);
        formStage.initModality(Modality.APPLICATION_MODAL);
        formStage.setTitle("Create new pet");
        formStage.setScene(scene);
        formStage.showAndWait();
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
