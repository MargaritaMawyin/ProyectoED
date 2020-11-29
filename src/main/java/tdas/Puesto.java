/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdas;

/**
 *
 * @author margo
 */
public class Puesto {
    private String medico;
    private int id;

    public Puesto(int id) {
        this.id = id;
        medico = "NA";
        
    }

    public Puesto(String medico, int id) {
        this.medico = medico;
        this.id = id;
    }
    

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    @Override
    public String toString() {
        return "Puesto{" + "id=" + id  + ", medico="+medico+ '}';
    }
    
    public String toArchivo(){
        return id + ";"+ medico;
    }
    
    
}
