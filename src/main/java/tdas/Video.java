/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdas;

import java.io.File;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author Kevin Chevez pc
 */
public class Video {
    private String path;

    public Video(String path) {
        this.path = path;
    }
    
    public MediaView mostrarVideo(){
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer medPlayer = new MediaPlayer(media);
        MediaView medView = new MediaView();
        medView.setMediaPlayer(medPlayer);
        return medView;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

       
}
