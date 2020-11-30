package com.mycompany.mavenproject1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import tdas.Paciente;

/**
 * JavaFX App
 */
public class App extends Application {
    public static String pathArchivos = "src/main/java/archivos/";
    public static String pathVideos = "src/main/java/archivos/videos.txt";
    public static String pathCss = "src/main/java/css/";
    public static String v = "videos";
    public static PriorityQueue<Paciente> listaPacientes = new PriorityQueue<>((Paciente p1, Paciente p2)->(p1.getPrioridad()-p2.getPrioridad()));
    
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        List<Paciente> paciCargados = Paciente.verPacientes("src/main/java/archivos/pacientes.txt");    
        listaPacientes.addAll(paciCargados);
        
        scene = new Scene(loadFXML("primary"), 640, 480);
//        scene.getStylesheets().clear();
//file would be set by an file chosser
//           File file = new File("C:/test.css");
//           scene.getStylesheets().add(file.getAbsolutePath());
//        scene.getStylesheets().add(this.getClass().getResource(pathCss).toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
//        List<Paciente> pacientes = (List<Paciente>) Paciente.verPacientes(pathArchivos+"pacientes.txt");
//        System.out.println(pacientes);
//        
//        pacientes.sort((Paciente p1,Paciente p2)->p1.getEdad()-p2.getEdad());
//        System.out.println(pacientes);
//        
//        Queue<Paciente> cola= new LinkedList<Paciente>();
//        cola.addAll(pacientes);
        
        launch();
        
        
    }
   

}