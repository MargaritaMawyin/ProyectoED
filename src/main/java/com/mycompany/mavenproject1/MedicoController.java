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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


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
            try {
                guardarMedicos();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
             
     public void guardarMedicos() throws IOException{
     try ( BufferedWriter bw = new BufferedWriter(new FileWriter (App.pathArchivos + "pacientes.txt",true))){
        String nombre = nombreM.getText();
        String apellido = apellidoM.getText();
        String especialidad = especialidadM.getText();
        Medico m = new Medico(nombre, apellido, especialidad);
        bw.write(m.toAchive());
        bw.newLine();
     }
        
     catch(Exception e){
                 
                 }
     nombreM.clear();
     apellidoM.clear();
     especialidadM.clear();
     
         App.setRoot("secondary");
         
     }
    
    
}
