/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.dto;

import mx.ipn.escom.modelo.Genero;

/**
 *
 * @author andii-burciaga
 */
public class GeneroDTO {

    private Genero entidad = new Genero();

    public GeneroDTO() {
    }

    public GeneroDTO(Genero entidad) {
        this.entidad = entidad;
    }

    public Genero getEntidad() {
        return entidad;
    }

    public void setEntidad(Genero entidad) {
        this.entidad = entidad;
    }

}
