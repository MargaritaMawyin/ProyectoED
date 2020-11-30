/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.mycompany.mavenproject1.App;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Kevin Chevez pc
 */
public class Puesto implements Serializable {
    private static final long serialVersionUID = 777L;
    
    private Medico medico;
    private Paciente paciente;
    private int numPuesto;

    public Puesto(Medico medico, Paciente paciente, int numPuesto) {
        this.medico = medico;
        this.paciente = paciente;
        this.numPuesto = numPuesto;
    }
    
    public Puesto(){
        this.medico = new Medico("General", "Julio", "Tumbaco");
        this.paciente = new Paciente(this, "Carlos", "Román");
    }
    
    public Puesto(int numPuesto){
        this.numPuesto = numPuesto;
    }
    
    /**
     * Este método elimina el médico que atiende el puesto
     */
    public void quitarMedico(){
        this.medico = null;
    }
    
    /**
     * Este método elimina el paciente que se encuentra en el puesto
     */
    public void quitarPaciente(){
        this.paciente = null;
    }
    
    /**
     * Este método verifica si el Puesto hay o no un Medico, es decir, si el puesto está atendiendo o no.
     * @return Devuelve true si el puesto está siendo atendido por un Medico y false si no lo está.
     */
    public boolean hayMedico(){
        return (medico != null);
    }
    
    /**
     * Este método verifica si el Puesto hay o no un paciente, es decir, si el puesto está o no libre.
     * @return Devuelve true si el puesto está libre y false si está siendo ocupado por una Paciente
     */
    public boolean estaLibre(){
        return (paciente != null);
    }
    
    /**
     * Este método establece un médico pasado como parámetro.
     * @param medico
     * @return Devuelve true si se pudo establecer médico y false si ya existe un médico atendiendo el puesto.
     */
    public boolean establecerMedico(Medico medico){
        if(hayMedico()) return false;
        else{
            this.medico = medico;
            return true;
        }        
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public int getNumPuesto() {
        return numPuesto;
    }

    public void setNumPuesto(int numPuesto) {
        this.numPuesto = numPuesto;
    }

    @Override
    public String toString() {
        return "Puesto{" + "medico=" + medico.nombre + ", paciente=" + paciente.nombre + ", numPuesto=" + numPuesto + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.medico);
        hash = 13 * hash + Objects.hashCode(this.paciente);
        hash = 13 * hash + this.numPuesto;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Puesto other = (Puesto) obj;
        if (this.numPuesto != other.numPuesto) {
            return false;
        }
        if (!Objects.equals(this.medico, other.medico)) {
            return false;
        }
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        return true;
    }
    
    
    public String toArchivo(){
        return medico.nombre + ";"+ paciente.nombre + ";" + numPuesto;
    }
    
    
    
    public static List<modelo.Puesto> verPuestos(String path) {
        List<modelo.Puesto> puestos = new LinkedList<>();
        try(ObjectInputStream file = new ObjectInputStream(new FileInputStream(App.pathArchivos+"puestosv2.dat"))){
            while(true){
                Puesto puest = (Puesto)file.readObject();
                puestos.add(puest);
            }
        }catch(FileNotFoundException fe){
            System.out.println("Afiche no encontrado: "+fe.getMessage());
        }catch(EOFException eo){
            System.out.println("Fin de fichero: "+eo.getMessage());
        }catch(ClassNotFoundException ce){
            System.out.println("Clase no encontrada: "+ce.getMessage());
        }catch(IOException io){
            System.out.println("Error IO: "+io.getMessage());
        }
        return puestos;
    }
}