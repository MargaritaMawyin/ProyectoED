package com.mycompany.mavenproject1;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {
    

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
         @FXML
    private void switchToMedico() throws IOException {
        App.setRoot("registroMedico");
    }
         @FXML
    private void switchToPaciente() throws IOException {
        App.setRoot("registroPaciente");
    }
    @FXML
    private void switchToPuesto() throws IOException {
        App.setRoot("configuracion");
    }
    @FXML
    private void switchToPuestoV2()throws IOException {
        App.setRoot("puesto");
    }
    
}
