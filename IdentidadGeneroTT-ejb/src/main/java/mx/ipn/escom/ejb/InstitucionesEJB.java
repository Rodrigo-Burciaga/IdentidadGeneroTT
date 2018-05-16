/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import mx.ipn.escom.br.InstutionsBR;
import mx.ipn.escom.dao.GenericDAO;
import mx.ipn.escom.dao.InstitutionsDAO;
import mx.ipn.escom.dto.InstitutionDTO;
import mx.ipn.escom.modelo.Institucion;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.CodigoRespuestaBR;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateless
public class InstitucionesEJB {

    @EJB
    InstitutionsDAO institutionsDAO;
    @EJB
    GenericDAO genericDAO;
    @EJB
    InstutionsBR instutionsBR;

    public Respuesta<InstitutionDTO> findAll() {
        Respuesta<Institucion> respuesta = institutionsDAO.findAll();
        Respuesta<InstitutionDTO> respuestaDTO = new Respuesta<>();
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            List<InstitutionDTO> institutions = new ArrayList();
            for (Institucion institution : respuesta.getResultados()) {
                institutions.add(new InstitutionDTO(institution));
            }
            respuestaDTO.setResultados(institutions);
        } else {
            respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
        }
        return respuestaDTO;
    }

    public Respuesta<InstitutionDTO> save(InstitutionDTO institutionDTO) {
        Respuesta<InstitutionDTO> respuestaDTO = new Respuesta<>();
        if (instutionsBR.isValidBR001(institutionDTO) == CodigoRespuestaBR.OK) {
            Respuesta respuesta = genericDAO.save(institutionDTO.getEntidad());
            if (respuesta.getCodigo() == CodigoRespuesta.ERROR) {
                respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
                respuestaDTO.setMensaje("global.errorInsert");
            }
        } else {
            respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
            respuestaDTO.setMensaje("global.BR001");
        }
        return respuestaDTO;
    }

    public Respuesta<InstitutionDTO> findById(InstitutionDTO institutionDTO) {
        Respuesta<InstitutionDTO> respuestaDTO = new Respuesta<>();
        Respuesta<Institucion> respuesta = genericDAO
                .findByID(institutionDTO.getEntidad().getClass(),
                        institutionDTO.getEntidad().getId());
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            respuestaDTO.setResultado(new InstitutionDTO(respuesta.getResultado()));
        } else {
            respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
        }
        return respuestaDTO;
    }

    public Respuesta<InstitutionDTO> update(InstitutionDTO institutionDTO) {
        Respuesta<InstitutionDTO> respuestaDTO = new Respuesta<>();
        if (instutionsBR.isValidBR001(institutionDTO) == CodigoRespuestaBR.OK) {
            Respuesta respuesta = genericDAO.update(institutionDTO.getEntidad());
            if (respuesta.getCodigo() == CodigoRespuesta.ERROR) {
                respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
                respuestaDTO.setMensaje("global.errorInsert");
            }
        } else {
            respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
            respuestaDTO.setMensaje("global.BR001");
        }
        return respuestaDTO;
    }

    public Respuesta<InstitutionDTO> delete(InstitutionDTO institutionDTO) {
        Respuesta<InstitutionDTO> respuesta
                = genericDAO.delete(institutionDTO.getEntidad());
        return respuesta;
    }
}
