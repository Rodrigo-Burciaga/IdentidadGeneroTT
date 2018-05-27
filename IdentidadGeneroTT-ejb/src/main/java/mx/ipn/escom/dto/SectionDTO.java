/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.dto;

import mx.ipn.escom.modelo.Seccion;

/**
 *
 * @author andii-burciaga
 */
public class SectionDTO {

    private Seccion entidad = new Seccion();

    public SectionDTO() {
    }

    public SectionDTO(Seccion entidad) {
        this.entidad = entidad;
    }

    public Seccion getEntidad() {
        return entidad;
    }

    public void setEntidad(Seccion entidad) {
        this.entidad = entidad;
    }

}
