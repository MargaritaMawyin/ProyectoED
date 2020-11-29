/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import tdas.Paciente;


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
    private ComboBox generoP;
    @FXML
    private ComboBox sintomaP;
    @FXML
    private Button guardarP;
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    public void initialize() throws IOException{
        ArrayList<String> lista = new ArrayList<>();
        lista.add("masculino");
        lista.add("femenino");
        generoP.getItems().clear();
        sintomaP.getItems().clear();
        for (String s:lista){
            generoP.getItems().add(s);
        }
        lista.clear();
        try{
            BufferedReader b =new BufferedReader (new FileReader(App.pathArchivos+"sintomas.txt"));
            String line;
            while((line =b.readLine()) !=null){
                String dividir[]=line.split(";");
                sintomaP.getItems().add(line);
            }
            b.close();
        }
        catch(FileNotFoundException f){
            System.out.println(f.getMessage());
        }
        
    }
    public void handle(Event e){
        guardarP.setOnMouseClicked(eh->{
            guardarPacientes(); 
        });
    }
             
    public void guardarPacientes(){
        String nombre = nombreP.getText();
        String apellido = apellidoP.getText();
        if ((nombre.equals("")) || (apellido.equals(""))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Falta de Información");
            alert.setTitle("Información Incorrecta");
            alert.setContentText("Debe ingresar toda la información solicitada");
            alert.showAndWait();
        } else {
            try ( BufferedWriter bw = new BufferedWriter(new FileWriter (App.pathArchivos + "pacientes.txt",true))){
            
            String edad = edadP.getText();
            String genero = generoP.getValue().toString();
            String sintoma[] = sintomaP.getValue().toString().split(";");
            Paciente p = new Paciente(nombre, apellido, Integer.parseInt(edad), genero, Integer.parseInt(sintoma[1]));
            bw.write(p.toAchive());
            bw.newLine();
            bw.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Registro Exitoso");
            alert.setTitle("Información");
            alert.setContentText("Creación de paciente exitoso");
            alert.showAndWait();
            App.setRoot("secondary");
        } 
        catch (NumberFormatException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Falta de Información");
                alert.setTitle("Información Incorrecta");
                alert.setContentText("Debe ingresar un número en edad");
                alert.showAndWait();
        }
        catch (IOException es){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Falta de Información");
            alert.setTitle("Información Incorrecta");
            alert.setContentText("Debe ingresar información correcta");
            alert.showAndWait();
        }
        catch (NullPointerException er){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Falta de Información");
            alert.setTitle("Información Incorrecta");
            alert.setContentText("Debe ingresar información correcta");
            alert.showAndWait();
        }
        nombreP.clear();
        apellidoP.clear();
        edadP.clear();     
        }
    }    
}

   
