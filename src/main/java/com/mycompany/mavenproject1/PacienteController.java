/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 *
 * @author margo
 */
public class PacienteController {
    @FXML
    private TextField nombreP;
    @FXML
    private TextField apellidoP;
    @FXML
    private TextField edadP;
    @FXML
    private TextField generoP;
    @FXML
    private TextField sintomaP;
    @FXML
    private Button guardarP;
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    
    public void handle(Event e){
        guardarP.setOnMouseClicked(eh->{
            try {
                guardarPacientes();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
             
     public void guardarPacientes() throws IOException{
     try ( BufferedWriter bw = new BufferedWriter(new FileWriter (App.pathArchivos + "pacientes.txt",true))){
        String nombre = nombreP.getText();
        String apellido = apellidoP.getText();
        String edad = edadP.getText();
        String genero = generoP.getText();
        String sintoma = sintomaP.getText();
        Paciente p = new Paciente(nombre, apellido, Integer.parseInt(edad), genero, sintoma);
        bw.write(p.toAchive());
        bw.newLine();
     }
        
     catch(Exception e){
                 
                 }
     nombreP.clear();
     apellidoP.clear();
     edadP.clear();
     generoP.clear();
     sintomaP.clear();
     
         App.setRoot("secondary");
         
     }    
     }
   
