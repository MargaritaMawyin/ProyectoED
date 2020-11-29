package com.mycompany.mavenproject1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import tdas.Video;
import tdas.circularDoublyLinkedList;

public class SecondaryController {
    
    @FXML
    private Label lblTiempo;
    
    @FXML
    private MediaView zonaVideo;
    
    @FXML
    private Label turno;
    
    @FXML
    private Label puesto;
    
    @FXML
    private VBox videoBox;
    
    @FXML
    private GridPane containerCenter;

    private circularDoublyLinkedList<Video> listVideos;
    private Date fecha = new Date();
    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize(){
        System.out.println("ENTRA A CARGAR LOS VIDEOSSSS!!! \n");
        leerVideos();      
        Iterator<Video> it = listVideos.iterator();
        verVideos(it);
        this.lblTiempo.setText(fecha.toString());
    }



    public void leerVideos() {
        try (BufferedReader inputStream = new BufferedReader(new FileReader("src/main/java/archivos/videos.txt"))) {
            String line = null;
            listVideos = new circularDoublyLinkedList<>();
            while ((line = inputStream.readLine()) != null) {
                listVideos.addFirst(new Video(line.trim()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no  se pudo abrir " + e.getMessage());
        } catch (IOException io) {
            System.out.println("Error io: " + io.getMessage());
        }
    }

    private void verVideos(Iterator<Video> it) {        
        MediaView viewVideo = it.next().mostrarVideo();
        //zonaVideo = viewVideo;
        zonaVideo.setMediaPlayer(viewVideo.getMediaPlayer());
        zonaVideo.getMediaPlayer().setAutoPlay(true);
        zonaVideo.getMediaPlayer().setOnEndOfMedia(()->{
            System.out.println("SE ACABO UN VIDEO");
            verVideos(it);
        });
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
        this.zonaVideo.getMediaPlayer().stop();
    }

}
