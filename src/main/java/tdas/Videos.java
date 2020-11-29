
package tdas;

import com.mycompany.mavenproject1.App;
import com.mycompany.mavenproject1.CircularLinkedList;
import com.mycompany.mavenproject1.List;
import java.io.File;
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
    
    private List<Videos> path;
        @FXML
    private MediaView zonaVideo;
    
    private MediaPlayer mediaPlayer;  
//    
//    public void handle(Event e){
//        zonaVideo.setOnError(eh->{
//            verVideo();
//        });
//    }
    
//    public void verVideo(){
//        mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(pathVideo).toExternalForm()));
//        mediaPlayer.setAutoPlay(true);
//        zonaVideo.setMediaPlayer(mediaPlayer);
//        
//               
//    }
    public void leerVideos(){
        CircularLinkedList<String> videosAReproducir =  new CircularLinkedList<String>() {};
        try(Scanner sc= new Scanner(new File(App.pathVideos))){
        
        String line = sc.nextLine();
        videosAReproducir.addFirst(line);
        
        mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(line).toExternalForm()));
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> { 
        });
        zonaVideo.setMediaPlayer(mediaPlayer);

        }
        
        catch(Exception e){
            System.out.println("Archivo no  se pudo abrir");
            
        }
        
    }
    
}
