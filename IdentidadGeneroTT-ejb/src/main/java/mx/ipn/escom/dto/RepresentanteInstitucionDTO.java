/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.dto;

import mx.ipn.escom.modelo.RepresentanteInstitucion;
import mx.ipn.escom.modelo.RepresentanteInstitucion_;

/**
 *
 * @author andii-burciaga
 */
public class RepresentanteInstitucionDTO {
    
    private RepresentanteInstitucion entidad = new RepresentanteInstitucion();

    public RepresentanteInstitucionDTO() {
    }
    
    public RepresentanteInstitucionDTO(RepresentanteInstitucion entidad){
        this.entidad = entidad;
    }

    public RepresentanteInstitucion getEntidad() {
        return entidad;
    }

    public void setEntidad(RepresentanteInstitucion entidad) {
        this.entidad = entidad;
    }
    
}
