/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


/**
 *
 * @author margo
 */
public class MedicoController {
    
    @FXML
    TextField nombreM;
    @FXML
    TextField apellidoM;
    @FXML
    TextField especialidadM;
    
    
    
    
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
        
        
    }
    
    
    
}
