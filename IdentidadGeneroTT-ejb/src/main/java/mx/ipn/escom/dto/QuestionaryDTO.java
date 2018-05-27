/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.dto;

import java.util.ArrayList;
import java.util.List;
import mx.ipn.escom.modelo.Cuestionario;

/**
 *
 * @author andii-burciaga
 */
public class QuestionaryDTO {

    private Cuestionario entidad = new Cuestionario();
    private List<CategoriaDTO> categorias = new ArrayList<>();
    private List<SectionDTO> secciones = new ArrayList<>();
    private List<QuestionDTO> questionsDTO = new  ArrayList<>();
    
    private String nombreCuestinario;
    private String nombreCategoria;
    private String nombreSeccion;

    public QuestionaryDTO() {
    }

    public Cuestionario getEntidad() {
        return entidad;
    }

    public void setEntidad(Cuestionario entidad) {
        this.entidad = entidad;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }

    public List<SectionDTO> getSecciones() {
        return secciones;
    }

    public void setSecciones(List<SectionDTO> secciones) {
        this.secciones = secciones;
    }

    public String getNombreSeccion() {
        return nombreSeccion;
    }

    public void setNombreSeccion(String nombreseccion) {
        this.nombreSeccion = nombreseccion;
    }

    public List<QuestionDTO> getQuestionsDTO() {
        return questionsDTO;
    }

    public void setQuestionsDTO(List<QuestionDTO> questionsDTO) {
        this.questionsDTO = questionsDTO;
    }

    public String getNombreCuestinario() {
        return nombreCuestinario;
    }

    public void setNombreCuestinario(String nombreCuestinario) {
        this.nombreCuestinario = nombreCuestinario;
    }
    
   
    
}
