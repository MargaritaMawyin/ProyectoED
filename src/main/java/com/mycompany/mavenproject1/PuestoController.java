/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.mavenproject1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modelo.Puesto;

/**
 * 
 * @author KevinChevez
 */
public class PuestoController {  
    @FXML
    private TextField puesto;
    
    @FXML
    private ComboBox puestosEliminar;
    
    @FXML
    private Button registrar;
    
    @FXML
    private Button eliminar;
    
    private ObservableList<Puesto> listaCombo = FXCollections.observableArrayList();
    private Puesto seleccionCombo;
    
    @FXML
    public void initialize(){
        listaCombo.addAll(App.puestosVacios);       
    }
    
    private void eliminarPuesto(Puesto puesto){
        Iterator<Puesto> it = App.puestosVacios.iterator();
        while(it.hasNext()){
            Puesto p = it.next();
            if(puesto.equals(p)){
                it.remove();
                System.out.println("Se remueve puesto número: "+p.getNumPuesto());
            }
        }
        listaCombo.addAll(App.puestosVacios); 
        guardarPuestos(App.puestosVacios);
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    
    public void manejadorBtnRegist(Event e){
        registrar.setOnMouseClicked(eh->{            
            guardarPuesto();
            puesto.clear();
        });
    }
    
    public void manejadorBtnEliminar(Event e){
        eliminar.setOnMouseClicked(eh->{
            System.out.println("Se eliminará al ultimo elemnto seleccionado: "+seleccionCombo);            
            eliminarPuesto(seleccionCombo);
            guardarPuestos(App.puestosVacios);
        });
    }
    
    public void manejadorCombo(Event e){
        Puesto seleccion = (Puesto)puestosEliminar.getSelectionModel().getSelectedItem();
        seleccionCombo = seleccion;
        System.out.println("Ultimo puesto seleccionado: "+seleccion);
    }
    
    private void guardarPuesto(){
        String puestoStr = puesto.getText();        
        if ((puestoStr.equals("") || puestoStr.isEmpty() || Integer.parseInt(puestoStr)<=0 || Integer.parseInt(puestoStr)>99)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Falta de Información");
            alert.setTitle("Información Incorrecta");
            alert.setContentText("Debe ingresar toda la información solicitada");
            alert.showAndWait();
        } else {
            int numPuesto = Integer.parseInt(puestoStr);
            System.out.println("Puesto creado con numero: "+numPuesto+" será guardado");
            try(MyObjectOutPutStream fileOut = new MyObjectOutPutStream(new FileOutputStream(App.pathArchivos+"puestosv2.dat", true))){
                Puesto puestoNuevo = new Puesto(numPuesto);
                fileOut.writeUnshared(puestoNuevo);
            }catch(IOException io){
                System.out.println("Error al escribir puesto.dat: "+io.getMessage());
            }
        }
    }
    
    private void guardarPuestos(List<Puesto> puestos){
        System.out.println("Se esta guardando los cambios en archivo");
        try(MyObjectOutPutStream fileOut = new MyObjectOutPutStream(new FileOutputStream(App.pathArchivos+"puestosv2.dat",false))){
            for(Puesto p : puestos){
                fileOut.writeUnshared(p);
            }
        }catch(IOException io){
            System.out.println("Error al escribir puesto.dat: "+io.getMessage());
        }
    }
}
