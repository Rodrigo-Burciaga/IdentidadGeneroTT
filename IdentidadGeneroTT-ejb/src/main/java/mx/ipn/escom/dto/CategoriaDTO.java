/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.dto;

import mx.ipn.escom.modelo.Categoria;

/**
 *
 * @author andii-burciaga
 */
public class CategoriaDTO {

    private Categoria entidad = new Categoria();

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria entidad) {
        this.entidad = entidad;
    }

    public Categoria getEntidad() {
        return entidad;
    }

    public void setEntidad(Categoria entidad) {
        this.entidad = entidad;
    }

}
