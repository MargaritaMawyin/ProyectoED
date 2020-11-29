/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdas;

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
    private int sintoma; 

    public Paciente(String nombre, String apellido, int edad, String genero, int sintoma) {
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

    public int getSintoma() {
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

    public void setSintoma(int sintoma) {
        this.sintoma = sintoma;
    }

    @Override
    public String toString() {
        return "Paciente{" + "nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", genero=" + genero + ", sintoma=" + sintoma + '}';
    }
    public String toAchive(){
        return nombre+","+apellido+","+edad+","+genero+","+sintoma;
    }
    
    
     public static List<Paciente> verPacientes(String nombre){
        List<Paciente> procesos= new LinkedList<>();
        try(Scanner sc = new Scanner(new File (nombre))){
        
        while(sc.hasNextLine()){
            String line=sc.nextLine();
            String[] tokens =line.split(",");
            
            Paciente p = new Paciente(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3], Integer.parseInt(tokens[4].split("\\|")[1]));
            procesos.add(p);
        }
        
    }
        catch(Exception e){
            System.out.println("Archivo no  se pudo abrir");
        }
        return procesos;
                
    }
    
    
    
    
}
