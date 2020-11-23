package com.mycompany.mavenproject1;

import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class SecondaryController {
    @FXML
    private MediaView zonaVideo;
    
    private MediaPlayer mediaPlayer;
    private static final String pathVideo = "1.mp4";    
    
    public void handle(Event e){
        zonaVideo.setOnError(eh->{
            verVideo();
        });
    }
    
    public void verVideo(){
        mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(pathVideo).toExternalForm()));
        mediaPlayer.setAutoPlay(true);
        zonaVideo.setMediaPlayer(mediaPlayer);
        
               
    }
     @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
        
    }
    
    
    
    
}