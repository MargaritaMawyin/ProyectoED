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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tdas.Medico;
import tdas.Puesto;

/**
 *
 * @author Rafael
 */
public class ConfiguracionController {
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnMedico;
    @FXML
    private Button btnEliminar;
    @FXML
    private FlowPane pConfiguracion;
    @FXML
    private FlowPane pConfiguracion2;
    
    private Label inicio = new Label();
    
    private Button op1 = new Button();
    private Button op2 = new Button();
    private Button op3 = new Button();
    private ArrayList<Puesto> puestos;
    private ArrayList<Medico> medicos;
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
    private void cargarArchivos(ArrayList<Puesto> lista) throws IOException{
        try{
            BufferedReader b =new BufferedReader (new FileReader(App.pathArchivos+"puestos.txt"));
            String line;
            while((line =b.readLine()) !=null){
                String dividir[]=line.split(";");
                Puesto p = new Puesto(Integer.parseInt(dividir[0]));
                p.setMedico(dividir[1]);
                lista.add(p);
            }
            b.close();
        }
        catch(FileNotFoundException f){
            System.out.println(f.getMessage());
        }
    }
    @FXML
    private void mostrarMedicos() throws IOException {
        pConfiguracion.getChildren().clear();
        pConfiguracion2.getChildren().clear();
        
        puestos = new ArrayList<>();
        cargarArchivos(puestos);
        
        HBox cont = new HBox(10);
        inicio.setText("  Opciones: ");
        op1.setText("Asignar");
        // se agregan las acciones a los botones creados
        op1.setOnAction((ActionEvent event) -> {
            mostrarPuestoOp1();
        });
        op2.setText("Desasignar");
        op2.setOnAction((ActionEvent event) -> {
            mostrarPuestoOp2();
        });
        Label espacio = new Label ("   ");
        Label espacio2 = new Label ("   ");
        
        cont.getChildren().addAll(inicio, op1, espacio, op2);//agregar los componentes al hbox
        pConfiguracion.getChildren().add(cont);
        HBox cont2 = new HBox(10);
        Label escoge = new Label("   Escoge una opción");
        cont2.getChildren().addAll(escoge);
        VBox cont3 = new VBox(10);
        cont3.getChildren().addAll(espacio,new Label("Puestos Registrados"));
        
        for(Puesto p: puestos){
            Label puesto = new Label(p.toString());
            cont3.getChildren().add(puesto);
        }
        pConfiguracion2.getChildren().addAll(cont2,cont3);
    }

    private void mostrarPuestoOp1() {
        pConfiguracion2.getChildren().clear();
        HBox cont = new HBox(10);
        inicio.setText("   ");
        ComboBox cb = new ComboBox();
        cb.setPromptText("Médicos");
        Button selecTer = new Button("Seleccionar");
        try{
            BufferedReader file = new BufferedReader(new FileReader(App.pathArchivos + "medicos.txt"));
            String line;
            ArrayList <String> listaMed = new ArrayList<>();
            while ((line = file.readLine()) != null){
                String dividir[] = line.split(",");
                if(!(listaMed.contains(dividir[0]+" "+dividir[1])) && (dividir[3].equals("false"))){
                    listaMed.add(dividir[0] + " " + dividir[1]);
                }
            }
            for (String s : listaMed){
                cb.getItems().add(s);
            }
        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        selecTer.setOnAction((ActionEvent event) ->{
            puestoMedico(cb.getValue().toString(), cont);
        });
        Label espacio = new Label ("   ");
        
        
        cont.getChildren().addAll(inicio, cb,selecTer, espacio);//agregar los componentes al vbox
        pConfiguracion2.getChildren().add(cont);
    }

    private void mostrarPuestoOp2() {
        pConfiguracion2.getChildren().clear();
        HBox cont = new HBox(10);
        inicio.setText("   ");
        ComboBox cb = new ComboBox();
        cb.setPromptText("Puestos");
        Button selecTer = new Button("Seleccionar");
        try{
            BufferedReader file = new BufferedReader(new FileReader(App.pathArchivos + "puestos.txt"));
            String line;
            ArrayList <String> listaPuesto = new ArrayList<>();
            while ((line = file.readLine()) != null){
                String dividir[] = line.split(";");
                if(!dividir[1].equals("NA")){
                    listaPuesto.add(dividir[0]);
                }
            }
            for (String s : listaPuesto){
                cb.getItems().add(s);
            }
        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        selecTer.setOnAction((ActionEvent event) ->{
            eliminarMedicoPuesto(cb.getValue().toString());
        });
        cont.getChildren().addAll(inicio, cb,selecTer);//agregar los componentes al vbox
        pConfiguracion2.getChildren().add(cont);
        
    }
    private void eliminarMedicoPuesto(String puesto) {
        String medico = null;
        try{
            BufferedReader file = new BufferedReader(new FileReader(App.pathArchivos + "puestos.txt"));
            String line;
            ArrayList <String> listaPuesto = new ArrayList<>();
            while ((line = file.readLine()) != null){
                String dividir[] = line.split(";");
                if(dividir[0].equals(puesto)){
                    listaPuesto.add(dividir[0]+";NA");
                    medico = dividir[1];
                }else{
                    listaPuesto.add(line);
                }
                
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(App.pathArchivos + "puestos.txt"));
            for (String l: listaPuesto){
                bw.write(l);
                bw.newLine();   
            }
            bw.close();
            listaPuesto.clear();
                
            file =new BufferedReader (new FileReader(App.pathArchivos+"medicos.txt"));
            String line2;
            while((line=file.readLine())  !=null){
                String dividir[]=line.split(",");
                if ((medico.equals(dividir[0]+","+dividir[1]))){
                    listaPuesto.add(dividir[0]+","+dividir[1]+","+dividir[2]+",false");
                }else{
                    listaPuesto.add(line);
                }
            }
            file.close();
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(App.pathArchivos + "medicos.txt"));
            for (String l: listaPuesto){
                bw2.write(l);
                bw2.newLine();   
            }
            bw2.close();   
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Registro Exitoso");
            alert.setTitle("Información");
            alert.setContentText("Desasignación de médico exitosa");
            alert.showAndWait();
            App.setRoot("secondary");
            
        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    private void puestoMedico(String medico, HBox cont) {
        ComboBox cb2 = new ComboBox();
        cb2.setPromptText("Puesto");
        Button selecPuesto = new Button("Seleccionar");
        try{
            BufferedReader file = new BufferedReader(new FileReader(App.pathArchivos + "puestos.txt"));
            String line;
            ArrayList <String> listaPuesto = new ArrayList<>();
            while ((line = file.readLine()) != null){
                String dividir[] = line.split(";");
                if(dividir[1].equals("NA")){
                    listaPuesto.add(dividir[0]);
                }
            }
            for (String s : listaPuesto){
                cb2.getItems().add(s);
            }
        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        selecPuesto.setOnAction((ActionEvent event) ->{
            seleccionarPuesto(cb2.getValue().toString(),medico);
        });
        cont.getChildren().addAll(cb2,selecPuesto);
    }

    private void seleccionarPuesto(String puesto, String medico) {
        puestos = new ArrayList<>();
        medicos = new ArrayList<>();
        BufferedReader b = null;
        
        try{
            String divide[] = medico.split(" ");
            b =new BufferedReader (new FileReader(App.pathArchivos+"puestos.txt"));
            String line;
            
            while((line=b.readLine())  !=null){
                String dividir[]=line.split(";");
                if ((dividir[0].equals(puesto) && dividir[1].equals("NA"))){
                    
                    
                    puestos.add(new Puesto (divide[0]+","+divide[1], Integer.parseInt(puesto)));
                }else{
                    puestos.add(new Puesto (dividir[1], Integer.parseInt(dividir[0])));
                }
            }
            b.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(App.pathArchivos + "puestos.txt"));
                for (Puesto l: puestos){
                    
                    bw.write(l.toArchivo());
                    bw.newLine();   
                }
                bw.close();
                
            b =new BufferedReader (new FileReader(App.pathArchivos+"medicos.txt"));
            String line2;
            
            while((line=b.readLine())  !=null){
                String dividir[]=line.split(",");
                if ((divide[0].equals(dividir[0]) && divide[1].equals(dividir[1]))){
                    medicos.add(new Medico (divide[0],divide[1],dividir[2],true));
                }else{
                    medicos.add(new Medico (dividir[0],dividir[1],dividir[2],dividir[3].equals("true")));
                }
            }
            b.close();
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(App.pathArchivos + "medicos.txt"));
            for (Medico l: medicos){
                bw2.write(l.toArchivo());
                bw2.newLine();   
            }
            bw2.close();   
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Registro Exitoso");
            alert.setTitle("Información");
            alert.setContentText("Asignación de médico exitosa");
            alert.showAndWait();
            App.setRoot("secondary");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void eliminarPuesto(){
        pConfiguracion.getChildren().clear();
        pConfiguracion2.getChildren().clear();
        HBox cont = new HBox(10);
        inicio.setText("   ");
        ComboBox cb = new ComboBox();
        cb.setPromptText("Puestos");
        Button selecTer = new Button("Seleccionar");
        try{
            BufferedReader file = new BufferedReader(new FileReader(App.pathArchivos + "puestos.txt"));
            String line;
            ArrayList <String> listaPuesto = new ArrayList<>();
            while ((line = file.readLine()) != null){
                String dividir[] = line.split(";");
                if(dividir[1].equals("NA")){
                    listaPuesto.add(dividir[0]);
                }
            }
            for (String s : listaPuesto){
                cb.getItems().add(s);
            }
        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        selecTer.setOnAction((ActionEvent event) ->{
            aceptarEliminacion(cb.getValue().toString());
        });
        cont.getChildren().addAll(inicio, cb,selecTer);//agregar los componentes al vbox
        pConfiguracion2.getChildren().add(cont);
    }
    private void aceptarEliminacion(String puesto){
        ArrayList <String> listaPuesto = new ArrayList<>();
        try{
            BufferedReader b =new BufferedReader (new FileReader(App.pathArchivos+"puestos.txt"));
            String line;
            while((line=b.readLine())  !=null){
                String dividir[]=line.split(";");
                if (!dividir[0].equals(puesto)){
                    listaPuesto.add(line);
                }
            }
            b.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(App.pathArchivos + "puestos.txt"));
                for (String l: listaPuesto){
                    
                    bw.write(l);
                    bw.newLine();   
                }
            bw.close();
                
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Registro Exitoso");
            alert.setTitle("Información");
            alert.setContentText("Eliminación de Puesto exitosa");
            alert.showAndWait();
            App.setRoot("secondary");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    private void agregarPuesto(){
        int i = 0;
        try{
            BufferedReader b =new BufferedReader (new FileReader(App.pathArchivos+"puestos.txt"));
            String line;
            while((line=b.readLine())  !=null){
                i++;
            }
            b.close();
            BufferedWriter bw = new BufferedWriter(new FileWriter (App.pathArchivos + "puestos.txt",true));
            Puesto m = new Puesto(i+1);
            bw.write(m.toArchivo());
            bw.newLine();
            bw.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Registro Exitoso");
            alert.setTitle("Información");
            alert.setContentText("Creación de Médico exitoso");
            alert.showAndWait();
            App.setRoot("secondary");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
