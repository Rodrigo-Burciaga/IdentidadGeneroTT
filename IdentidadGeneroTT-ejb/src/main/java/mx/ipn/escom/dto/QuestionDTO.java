/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.dto;

import java.util.ArrayList;
import java.util.List;
import mx.ipn.escom.modelo.Pregunta;

/**
 *
 * @author andii-burciaga
 */
public class QuestionDTO {

    private Pregunta entidad = new Pregunta();
    private List<OpcionRespuestaDTO> opcionRespuestaDTO = new ArrayList<>();
    private Integer id;
    private Integer idOpcion;
    private Long idTipo;
    private String categoriaSelected;
    private String seccionSelected;

    public QuestionDTO() {
        idOpcion = 1;
    }

    public Pregunta getEntidad() {
        return entidad;
    }

    public void incrementOpcion() {
        idOpcion++;
    }

    public void setEntidad(Pregunta entidad) {
        this.entidad = entidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public Long getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }

    public List<OpcionRespuestaDTO> getOpcionRespuestaDTO() {
        return opcionRespuestaDTO;
    }

    public void setOpcionRespuestaDTO(List<OpcionRespuestaDTO> opcionRespuestaDTO) {
        this.opcionRespuestaDTO = opcionRespuestaDTO;
    }

    public String getCategoriaSelected() {
        return categoriaSelected;
    }

    public void setCategoriaSelected(String categoriaSelected) {
        this.categoriaSelected = categoriaSelected;
    }

    public String getSeccionSelected() {
        return seccionSelected;
    }

    public void setSeccionSelected(String seccionSelected) {
        this.seccionSelected = seccionSelected;
    }
    
    
    
}
