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
import mx.ipn.escom.dao.PuntuacionDAO;
import mx.ipn.escom.dto.PuntuacionDTO;
import mx.ipn.escom.modelo.Puntuacion;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@LocalBean
public class PuntuacionEJB {

    @EJB
    PuntuacionDAO puntuacionDAO;

    public Respuesta<PuntuacionDTO> findAll() {
        Respuesta<Puntuacion> respuesta = puntuacionDAO.findAll();
        Respuesta<PuntuacionDTO> respuestaDTO = new Respuesta<>();
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            List<PuntuacionDTO> puntuaciones = new ArrayList();
            for (Puntuacion puntuacion : respuesta.getResultados()) {
                puntuaciones.add(new PuntuacionDTO(puntuacion));
            }
            respuestaDTO.setResultados(puntuaciones);
        } else {
            respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
        }
        return respuestaDTO;
    }
}
