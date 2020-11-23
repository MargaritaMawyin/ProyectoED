package com.mycompany.mavenproject1;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

public class SecondaryController {
    
    
    
     @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
        
    }
    
    
    
    
}