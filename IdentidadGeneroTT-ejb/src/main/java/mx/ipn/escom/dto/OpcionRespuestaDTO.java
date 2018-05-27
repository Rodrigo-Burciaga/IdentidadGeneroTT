/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.dto;

import mx.ipn.escom.modelo.OpcionRespuesta;

/**
 *
 * @author andii-burciaga
 */
public class OpcionRespuestaDTO {
    
    
    private OpcionRespuesta entidad = new OpcionRespuesta();
    private Integer ratingM;
    private Integer ratingF;

    public OpcionRespuestaDTO() {
    }
    
    public OpcionRespuestaDTO(OpcionRespuesta entidad){
        this.entidad = entidad;
    }  

    public OpcionRespuesta getEntidad() {
        return entidad;
    }

    public void setEntidad(OpcionRespuesta entidad) {
        this.entidad = entidad;
    }

    public Integer getRatingM() {
        return ratingM;
    }

    public void setRatingM(Integer ratingM) {
        this.ratingM = ratingM;
    }

    public Integer getRatingF() {
        return ratingF;
    }

    public void setRatingF(Integer ratingF) {
        this.ratingF = ratingF;
    }
    
}
