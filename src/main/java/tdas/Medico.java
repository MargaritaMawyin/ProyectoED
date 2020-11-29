/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author margo
 */
public class Medico {
    private String nombre;
    private String apellido;
    private String especialidad;
    private boolean asignado;
    

    public Medico(String nombre, String apellido, String especialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.asignado = false;
    }

    public Medico(String nombre, String apellido, String especialidad, boolean asignado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.asignado = asignado;
    }
    

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isAsignado() {
        return asignado;
    }

    public void setAsignado(boolean asignado) {
        this.asignado = asignado;
    }
    

    @Override
    public String toString() {
        return "Medico{" + "nombre=" + nombre + ", apellido=" + apellido + ", especialidad=" + especialidad + ", asignado=" + asignado + '}';
    }

    
    public String toArchivo(){
        return nombre+","+apellido+","+especialidad+","+asignado;
    }
        
}
        
    
    

