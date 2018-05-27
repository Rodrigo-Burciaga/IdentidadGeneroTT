/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import mx.ipn.escom.dao.GenericDAO;
import mx.ipn.escom.dto.InstitutionDTO;
import mx.ipn.escom.dto.RepresentanteInstitucionDTO;
import mx.ipn.escom.modelo.RepresentanteInstitucion;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@LocalBean
public class RepresentanteInstitucionEJB {

    @EJB
    GenericDAO genericDAO;

    public Respuesta<RepresentanteInstitucionDTO> findByID() {
        Respuesta<RepresentanteInstitucionDTO> respuestaDTO = new Respuesta<>();
        Respuesta<RepresentanteInstitucion> respuesta = genericDAO
                .findByID(RepresentanteInstitucion.class,
                        1L);
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            respuestaDTO.setResultado(new RepresentanteInstitucionDTO(
                    respuesta.getResultado()));
        } else {
            respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
        }
        return respuestaDTO;

    }

}
