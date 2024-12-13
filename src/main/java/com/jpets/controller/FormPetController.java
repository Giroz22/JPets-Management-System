package com.jpets.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.swing.filechooser.FileSystemView;

import com.jpets.controller.dtos.request.PetRequests;
import com.jpets.controller.dtos.response.PetResponse;
import com.jpets.service.abstract_service.IPetService;
import com.jpets.utils.ViewUtil;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FormPetController {
    
    //Properties
    private PetRequests petRequests;
    private IPetService petService;
    private ViewUtil viewUtil;

    //Nodes
    Stage formStage;

    File selectedFile;

    @FXML
    VBox contentInputs;

    @FXML
    ButtonBar btnBar;

    @FXML
    TextField textNamePet;

    @FXML
    TextField textNameOwner;

    @FXML
    Button btnSelectImgPet;

    @FXML
    ImageView imagePet;

    //Constructors
    public FormPetController(IPetService petService){
        this.petService = petService;
        this.viewUtil = new ViewUtil();
    }

    //Actions of nodes

    //Life Cicle FX
    @FXML
    private void initialize(){
    }

    @FXML
    public void selectImgPet(){
        // Crear el FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );

        // Abrir el diálogo para seleccionar un archivo
        Stage stage = (Stage) btnSelectImgPet.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);                    
        
        // Cargar y mostrar la imagen seleccionada
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imagePet.setImage(image);
        }
    }

    //Funtions    
    private void createStagePet(){
        this.formStage = new Stage();

        Scene scene = viewUtil.getScene(
            "/views/pets/FormPet.fxml",
            this
        );

        ViewUtil.centerStageAndSetSize(formStage, 30, 70);
        formStage.initModality(Modality.APPLICATION_MODAL);
        formStage.setScene(scene);        
    }

    public void showCreatePet(){   
        this.createStagePet();

        //Asignar el boton crear
        this.btnBar.getButtons().clear();

        Button btnCreate = new Button("Agregar mascota");
        btnCreate.setOnAction((event)->{
            this.createPet();
        });
        this.btnBar.getButtons().add(btnCreate);

        formStage.setTitle("Agregar una nueva mascota");
        formStage.showAndWait();
    }

    public void showInfoPet(String idPet){
        //Se busca la mascota
        PetResponse pet = this.petService.getById(idPet);

        //Se crea el stagePet
        this.createStagePet();                

        //Se asignan los datos a los textfield
        this.textNamePet.setText(pet.getName());
        this.textNameOwner.setText(pet.getOwnerName());

        // Cargar y mostrar la imagen seleccionada si exite url
        String urlImg = pet.getPictureUrl();
        if (urlImg != null || urlImg != "") {
            Image image = new Image("file://" + pet.getPictureUrl());
            imagePet.setImage(image);
        }

        //Se limpia la barra de botones 
        this.btnBar.getButtons().clear();

        //Asignar funcionalidad al actualizar
        Button btnUpdate = new Button("Actualizar");
        btnUpdate.setOnAction((event)->{            
            ViewUtil.showAlert(
                "Alerta",
                null,
                "Actualizar",
                AlertType.INFORMATION
            );
        });

        //Asignar funcionalidad al eliminar
        Button btnDelete = new Button("Eliminar");
        btnDelete.setOnAction((event)->{
            ViewUtil.showAlert(
                "Alerta",
                null,
                "Eliminar",
                AlertType.CONFIRMATION
            );            
        });

        //Se asignan los botones a la barra de botones
        this.btnBar.getButtons().addAll(btnUpdate, btnDelete);

        //Deshabilitar los inputs
        for(var node : contentInputs.getChildren()){
            if (node instanceof TextField || node instanceof Button) {
                node.setDisable(true);
            }
        }

        formStage.setTitle("Informacion de " + pet.getName());
        formStage.showAndWait();
    }

    public void createPet() {

        this.petRequests = PetRequests.builder()
        .name(textNamePet.getText())
        .ownerName(textNameOwner.getText())
        .pictureUrl(saveImg(textNamePet.getText()+"-foto"))
        .build();

        this.petService.create(petRequests);

        ViewUtil.showAlert(
            "Alerta",
            null,
            "La mascota se guardo correctamente!!!",
            AlertType.INFORMATION
        );
        this.formStage.close();

    }  

    public String saveImg(String nameFile) {
        try {
            //Si no se selecciono una imagen no es necesario guardar
            if(selectedFile == null) return "";

            // Obtener la carpeta "Documentos" del usuario de forma segura
            FileSystemView fileSystemView = FileSystemView.getFileSystemView();
            File documentsFolder = fileSystemView.getDefaultDirectory();

            // Crear la carpeta "/Jpets/Mascotas" dentro de "Documentos" si no existe
            Path destinationFolder = Path.of(documentsFolder.getAbsolutePath(), "/Jpets/Mascotas");
            if (!Files.exists(destinationFolder)) {
                Files.createDirectories(destinationFolder);
            }

            // Ruta completa del archivo destino
            Path destinationPath = destinationFolder.resolve(nameFile);
            Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);  
            return destinationPath.toString();    
        } catch (Exception e) {
            ViewUtil.showErrorAlert("Error al guardar la imagen", e.getMessage());
            return null;
        }
    }
}
