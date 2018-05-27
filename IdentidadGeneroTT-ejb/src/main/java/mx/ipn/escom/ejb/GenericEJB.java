/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@LocalBean
public class GenericEJB {

    public <T> void setRespuestaWrong(Respuesta<T> respuestaDTO,
            String mensaje) {
        respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
        respuestaDTO.setMensaje(mensaje);
    }
}
