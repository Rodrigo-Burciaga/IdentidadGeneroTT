/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import mx.ipn.escom.dao.SectionsDAO;
import mx.ipn.escom.dto.SectionDTO;
import mx.ipn.escom.modelo.Seccion;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@LocalBean
public class SectionsEJB {

    @EJB
    SectionsDAO sectionsDAO;

    public Respuesta<SectionDTO> findAll() {
        Respuesta<Seccion> respuesta = sectionsDAO.findAll();
        Respuesta<SectionDTO> respuestaDTO = new Respuesta<>();
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            List<SectionDTO> secciones = new ArrayList<>();
            for (Seccion seccion : respuesta.getResultados()) {
                secciones.add(new SectionDTO(seccion));
            }
            respuestaDTO.setResultados(secciones);
        } else {
            respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
        }
        return respuestaDTO;

    }

}
