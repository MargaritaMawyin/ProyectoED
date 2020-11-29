package com.mycompany.mavenproject1;

import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import tdas.Medico;
import tdas.Paciente;
import tdas.Videos;

public class SecondaryController {

    @FXML
    private MediaView zonaVideo;
    @FXML
    private Label turno;
    @FXML
    private Label puesto;

    private MediaPlayer mediaPlayer;
    private Videos videos = new Videos();;

    @FXML
    public void handle(Event e) {
        zonaVideo.setOnError(eh -> {
            cargarVideos();
        });
    }

    @FXML
    public void cargarVideos(){
        System.out.println("ENTRA A CARGAR LOS VIDEOSSSS!!! \n");
        videos.verVideos();
        setVideos();
    }
    
    public void setVideos(){
        this.zonaVideo = videos.getZonaVideo();
        this.mediaPlayer = zonaVideo.getMediaPlayer();
    }
    /*public void verVideo() {
        CircularLinkedList<String> videosAReproducir = new CircularLinkedList<>();
        try (Scanner sc = new Scanner(new File("videos.txt"))) {

            String line = sc.nextLine();
            videosAReproducir.addFirst(line);

            mediaPlayer = new MediaPlayer(new Media(new File(line).toURI().toString()));
            mediaPlayer.setAutoPlay(true);
//            mediaPlayer.setOnEndOfMedia(() -> {
//            mediaPlayer = new MediaPlayer(new Media(new File("videos/2.mp4").toURI().toString())); 
//            mediaPlayer.setAutoPlay(true);
//            zonaVideo.setMediaPlayer(mediaPlayer);
//        });
            zonaVideo.setMediaPlayer(mediaPlayer);

        } catch (Exception e) {
            System.out.println("Archivo no  se pudo abrir");

        }
    }*/

    /*public void cola(Queue<Paciente> colaPacientes, List<Medico> medicos) {
        PriorityQueue<Paciente> colaVip = new PriorityQueue<>((Paciente p1, Paciente p2) -> p1.getSintoma() - p2.getSintoma());
        colaVip.addAll(colaPacientes);

        while (!colaVip.isEmpty()) {
            Paciente p = colaPacientes.poll();

        }

    }*/

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");

    }

}
