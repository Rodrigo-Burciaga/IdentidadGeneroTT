/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.mb;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import mx.com.ipn.escom.war.mb.GenericMB;
import mx.ipn.escom.dto.InstitutionDTO;
import mx.ipn.escom.ejb.InstitucionesEJB;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "catalogMB")
@ApplicationScoped
public class CatalogMB extends GenericMB{

    @EJB
    InstitucionesEJB institucionesEJB;
    private List<InstitutionDTO> instituciones;

    public CatalogMB() {

    }

    @PostConstruct
    public void init() {
        System.out.println("iniciando InstitutionsMB");
        Respuesta<InstitutionDTO> respuestaDTO
                = institucionesEJB.findAll();
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            setInstituciones(respuestaDTO.getResultados());
        }
    }

    @Override
    public String prepareAdd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String prepareUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String prepareDelete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String viewDetail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String back() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String find() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<InstitutionDTO> getInstituciones() {
        return instituciones;
    }

    public void setInstituciones(List<InstitutionDTO> instituciones) {
        this.instituciones = instituciones;
    }
    
    
    
    

}
