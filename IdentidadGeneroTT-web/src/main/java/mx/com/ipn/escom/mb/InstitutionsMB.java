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
import javax.inject.Inject;
import mx.com.ipn.escom.identidadGenero.util.GenericMB;
import mx.com.ipn.escom.identidadGenero.util.NavigationConstants;
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
    private InstitucionesEJB institucionesEJB;
    @Inject
    private LoginMB loginMB;
    @Inject
    CatalogMB catalogMB;

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
        //TODO: falta verificar que el acrónimo y el nombre de la institución no exista
        Administrador admin = loginMB.getLoginDTO().getEntidad();
        if (admin != null) {
            institutionDTO.getEntidad().setIdadministrador(admin);
            Respuesta<InstitutionDTO> respuestaDTO = institucionesEJB.
                    save(institutionDTO);
            if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
                catalogMB.updateInstitutions();
                addMessage("global.success", "globalMSG",
                        FacesMessage.SEVERITY_INFO, "addInstitution.success");
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", respuestaDTO.getMensaje()));
            }
        }
        return NavigationConstants.VIEWINSTITUTIONSWR;
    }

    @Override
    public String prepareUpdate() {
        Respuesta<InstitutionDTO> respuestaDTO
                = institucionesEJB.findById(institutionDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            institutionDTO.setEntidad(respuestaDTO.getResultado().getEntidad());
        }
        return NavigationConstants.EDITINSTITUTION;
    }

    @Override
    public String update() {
        //TODO: falta verificar que no se repitan acronimos ni nombres y/o direcciones
        Respuesta<InstitutionDTO> respuestaDTO = institucionesEJB.
                update(institutionDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            catalogMB.updateInstitutions();
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "editInstitition.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "editInstitution.error");
        }
        return NavigationConstants.DETAILINSTITUCIONWR;
    }

    @Override
    public String prepareDelete() {
        Respuesta<InstitutionDTO> respuestaDTO
                = institucionesEJB.findById(institutionDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            institutionDTO.setEntidad(respuestaDTO.getResultado().getEntidad());
        }
        return NavigationConstants.DELETEINSTITUTION;
    }

    @Override
    public String delete() {
        System.out.println("Entrando delete");
        Respuesta<InstitutionDTO> respuestaDTO
                = institucionesEJB.delete(institutionDTO);
        System.out.println(respuestaDTO.getCodigo());
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteInstitution.success");
            catalogMB.updateInstitutions();
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteInstitution.error");
            System.out.println(respuestaDTO.getMensaje());
        }
        return NavigationConstants.VIEWINSTITUTIONSWR;
    }

    @Override
    public String viewDetail() {
        Respuesta<InstitutionDTO> respuestaDTO
                = institucionesEJB.findById(institutionDTO);
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
