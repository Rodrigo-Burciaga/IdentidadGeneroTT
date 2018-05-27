/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.dto;

import mx.ipn.escom.modelo.Puntuacion;

/**
 *
 * @author andii-burciaga
 */
public class PuntuacionDTO {

    private Puntuacion entidad = new Puntuacion();
    
    public PuntuacionDTO() {
    }
    
    public PuntuacionDTO(Puntuacion entidad){
        this.entidad = entidad;
    }

    public Puntuacion getEntidad() {
        return entidad;
    }

    public void setEntidad(Puntuacion entidad) {
        this.entidad = entidad;
    }
    
    
    
    
}
