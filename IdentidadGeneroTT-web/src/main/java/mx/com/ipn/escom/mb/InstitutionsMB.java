/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.mb;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import mx.com.ipn.escom.identidadGenero.util.NavigationConstants;
import mx.com.ipn.escom.war.mb.GenericMB;
import mx.ipn.escom.dao.GenericDAO;
import mx.ipn.escom.dto.InstitutionDTO;
import mx.ipn.escom.ejb.InstitucionesEJB;
import mx.ipn.escom.modelo.Administrador;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "institutionsMB")
@SessionScoped
public class InstitutionsMB extends GenericMB implements Serializable {

    @EJB
    InstitucionesEJB institucionesEJB;
    @EJB
    GenericDAO genericDAO;
    private InstitutionDTO institutionDTO = new InstitutionDTO();

    public InstitutionsMB() {
    }

    @PostConstruct
    public void init() {
    }

    @Override
    public String prepareAdd() {
        institutionDTO = new InstitutionDTO();
        return NavigationConstants.ADDINSTITUTION;
    }

    @Override
    public String add() {
        //TODO: no olvidar quitar el codigo hardcodeado
        Respuesta<Administrador> admin = genericDAO.findByID(Administrador.class, 1L);
        institutionDTO.getEntidad().setIdadministrador(admin.getResultado());
        Respuesta<InstitutionDTO> respuestaDTO = institucionesEJB.save(institutionDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Institución Agregada Correctamente"));
        } else{
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", respuestaDTO.getMensaje()));
        }
        return NavigationConstants.VIEWINSTITUTIONS;
    }

    @Override
    public String prepareUpdate() {
        return NavigationConstants.EDITINSTITUTION;
    }

    @Override
    public String update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String prepareDelete() {
        return NavigationConstants.DELETEINSTITUTION;
    }

    @Override
    public String delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String viewDetail() {
        Respuesta<InstitutionDTO> respuestaDTO =
                institucionesEJB.findById(institutionDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            institutionDTO.setEntidad(respuestaDTO.getResultado().getEntidad());
            System.out.println(institutionDTO.getEntidad().getNombre());
        }
        return NavigationConstants.DETAILINSTITUCION;
    }

    @Override
    public String clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String back() {
        return NavigationConstants.VIEWINSTITUTIONS;
    }

    @Override
    public String find() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public InstitutionDTO getInstitutionDTO() {
        return institutionDTO;
    }

    public void setInstitutionDTO(InstitutionDTO institutionDTO) {
        this.institutionDTO = institutionDTO;
    }

}
