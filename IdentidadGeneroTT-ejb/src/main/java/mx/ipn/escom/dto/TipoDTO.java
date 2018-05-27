/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.dto;

import mx.ipn.escom.modelo.Tipo;

/**
 *
 * @author andii-burciaga
 */
public class TipoDTO {
    
    private Tipo entidad = new Tipo();

    public TipoDTO() {
    }
    
    public TipoDTO(Tipo entidad){
        this.entidad = entidad;
    }

    public Tipo getEntidad() {
        return entidad;
    }

    public void setEntidad(Tipo entidad) {
        this.entidad = entidad;
    }
}
