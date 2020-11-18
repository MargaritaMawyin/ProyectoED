/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author margo
 */
public class Paciente {
    private String nombre; 
    private String apellido; 
    private int edad;
    private String genero; 
    private String sintoma; 

    public Paciente(String nombre, String apellido, int edad, String genero, String sintoma) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.sintoma = sintoma;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public String getSintoma() {
        return sintoma;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    @Override
    public String toString() {
        return "Paciente{" + "nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", genero=" + genero + ", sintoma=" + sintoma + '}';
    }
     public static List<Turno> leerProcesos(String nombre){
        List<Turno> procesos= new LinkedList<>();
        try(Scanner sc = new Scanner(new File (nombre))){
        
        while(sc.hasNextLine()){
            String line=sc.nextLine();
            String[] tokens =line.split("\\|");
            
            
        }
        
    }
        catch(Exception e){
            System.out.println("Archivo no  se pudo abrir");
        }
        return procesos;
                
    }
    
    
}
