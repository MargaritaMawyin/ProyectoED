/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.exceptions;

/**
 *
 * @author JOVEN EJEMPLAR
 */
public class ValorNuloException extends Exception{
    private String mensaje = null;
    
    public ValorNuloException(String message){
        super();
        mensaje = message;
    }
    
    @Override
    public String getMessage(){
        return mensaje;
    }
}
