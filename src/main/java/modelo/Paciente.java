/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Kevin Chevez pc
 */
public class Paciente extends Usuario{
    private int edad;
    private String genero;
    private String sintoma;
    private int prioridad;
    private String turno;
    private Puesto puesto;

    public Paciente(int edad, String genero, String sintoma, int prioridad, String turno, Puesto puesto, String nombre, String apellido) {
        super(nombre, apellido);
        this.edad = edad;
        this.genero = genero;
        this.sintoma = sintoma;
        this.prioridad = prioridad;
        this.turno = turno;
        this.puesto = puesto;
    }

    public Paciente(Puesto puesto, String nombre, String apellido) {
        super(nombre, apellido);
        this.puesto = puesto;
        this.edad = 18;
        this.genero = "Masculino";
        this.sintoma = "fiebre";
        this.prioridad = 3;
        this.turno = "A2";
    }

    public Paciente(int edad, String genero, String sintoma, int prioridad, String nombre, String apellido) {
        super(nombre, apellido);
        this.edad = edad;
        this.genero = genero;
        this.sintoma = sintoma;
        this.prioridad = prioridad;
    }

    

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Paciente{"+"nombre="+nombre + "edad=" + edad + ", genero=" + genero + ", sintoma=" + sintoma + ", prioridad=" + prioridad + '}';
    }
    
    
    public String toAchive(){
        return nombre+","+edad+","+genero+","+sintoma+","+prioridad;
    }
    
    
     public static List<tdas.Paciente> verPacientes(String nombre) {
        List<tdas.Paciente> procesos = new LinkedList<>();
        try (Scanner sc = new Scanner(new File(nombre))) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] tokens = line.split(",");

                tdas.Paciente p = new tdas.Paciente(tokens[0], tokens[1], Integer.parseInt(tokens[2]), tokens[3], Integer.parseInt(tokens[4]));
                procesos.add(p);
            }

        } catch (Exception e) {
            System.out.println("Archivo no  se pudo abrir");
        }
        return procesos;

    }
}
