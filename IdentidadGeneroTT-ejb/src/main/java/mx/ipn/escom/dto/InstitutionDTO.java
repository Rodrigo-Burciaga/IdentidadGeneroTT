/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.dto;

import mx.ipn.escom.modelo.Institucion;

/**
 *
 * @author andii-burciaga
 */
public class InstitutionDTO {

    private Institucion entidad = new Institucion();
    private Integer id;

    public InstitutionDTO() {
    }

    public InstitutionDTO(Institucion entidad) {
        this.entidad = entidad;
    }

    public Institucion getEntidad() {
        return entidad;
    }

    public void setEntidad(Institucion entidad) {
        this.entidad = entidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

}
