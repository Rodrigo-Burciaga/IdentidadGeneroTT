/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.dto;

import java.text.SimpleDateFormat;
import mx.ipn.escom.modelo.Administrador;

/**
 *
 * @author andii-burciaga
 */
public class LoginDTO {

    private Administrador entidad = new Administrador();
    private Boolean logged = false;
    private String fechaInicio;
    private String passRepeated;
    SimpleDateFormat ft = new SimpleDateFormat("EEEE dd 'de' MMMM 'de' YYYY "
            + "'a las' hh:mm:ss a");

    public LoginDTO() {
    }

    public LoginDTO(Administrador entidad) {
        this.entidad = entidad;
    }

    public Administrador getEntidad() {
        return entidad;
    }

    public void setEntidad(Administrador entidad) {
        this.entidad = entidad;
    }

    public Boolean getLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public SimpleDateFormat getFt() {
        return ft;
    }

    public void setFt(SimpleDateFormat ft) {
        this.ft = ft;
    }

    public String getPassRepeated() {
        return passRepeated;
    }

    public void setPassRepeated(String passRepeated) {
        this.passRepeated = passRepeated;
    }
    

}
