/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tdas.Medico;


/**
 *
 * @author margo
 */
public class MedicoController {
    
    @FXML
    private TextField nombreM;
    @FXML
    private TextField apellidoM;
    @FXML
    private TextField especialidadM;
    @FXML
    private Button guardarM;
    
    
    
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
        
        
    }
    public void handle(Event e){
        guardarM.setOnMouseClicked(eh->{
            guardarMedicos();
        });
    }
             
    public void guardarMedicos(){
        String nombre = nombreM.getText();
        String apellido = apellidoM.getText();
        String especialidad = especialidadM.getText();
        if ((nombre.equals("")) || (apellido.equals("")) || (especialidad.equals(""))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Falta de Información");
            alert.setTitle("Información Incorrecta");
            alert.setContentText("Debe ingresar toda la información solicitada");
            alert.showAndWait();
        } else {
            try ( BufferedWriter bw = new BufferedWriter(new FileWriter (App.pathArchivos + "medicos.txt",true))){
            
                Medico m = new Medico(nombre, apellido, especialidad);
                bw.write(m.toArchivo());
                bw.newLine();
                bw.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Registro Exitoso");
                alert.setTitle("Información");
                alert.setContentText("Creación de Médico exitoso");
                alert.showAndWait();
                App.setRoot("secondary");
            }
            catch (IOException es){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Falta de Información");
                alert.setTitle("Información Incorrecta");
                alert.setContentText("Debe ingresar información correcta");
                alert.showAndWait();
            }
            nombreM.clear();
            apellidoM.clear();
            especialidadM.clear();
        }
    }
}
