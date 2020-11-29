package tdas;

import com.mycompany.mavenproject1.App;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author margo
 */
public class Videos {

    private circularDoublyLinkedList<Video> listVideos;
    @FXML
    private MediaView zonaVideo;

    private MediaPlayer mediaPlayer;
    
    @FXML
    public void handle(Event e){
        zonaVideo.setOnError(eh->{
            verVideos();           
        });
    }

//    public void verVideo(){
//        mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(pathVideo).toExternalForm()));
//        mediaPlayer.setAutoPlay(true);
//        zonaVideo.setMediaPlayer(mediaPlayer);
//        
//               
//    }
    public void leerVideos() {
        try (BufferedReader inputStream = new BufferedReader(new FileReader(App.pathVideos))) {
            String line = null;
            while(inputStream.readLine()!=null){
                listVideos.addFirst(new Video(line.trim()));
            }
        } catch (Exception e) {
            System.out.println("Archivo no  se pudo abrir "+e.getMessage());
        }
    }
    
    public void verVideos(){
        leerVideos();
        Iterator<Video> it = listVideos.iterator();
        Video iVideo = it.next();
        zonaVideo = iVideo.mostrarVideo();
        mediaPlayer = zonaVideo.getMediaPlayer();
        mediaPlayer.setOnEndOfMedia(()->{
            System.out.println("SE ACABO UN VIDEO");
        });
    }
    private void verVideos(Iterator<Video> it){
        Video iVideo = it.next();
        zonaVideo = iVideo.mostrarVideo();
        mediaPlayer = zonaVideo.getMediaPlayer();
        mediaPlayer.setOnEndOfMedia(()->{
            verVideos(it);
        });
    }

    public MediaView getZonaVideo() {
        return zonaVideo;
    }

    public void setZonaVideo(MediaView zonaVideo) {
        this.zonaVideo = zonaVideo;
    }
    
    
}
