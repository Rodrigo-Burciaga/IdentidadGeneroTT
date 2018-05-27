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
import mx.ipn.escom.dao.TipoDAO;
import mx.ipn.escom.dto.TipoDTO;
import mx.ipn.escom.modelo.Tipo;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@LocalBean
public class TipoEJB {
    
    @EJB
    TipoDAO tipoDAO;
    
    public Respuesta<TipoDTO> findAll() {
        Respuesta<TipoDTO> respuestaDTO = new Respuesta<>();
        Respuesta<Tipo> respuesta = tipoDAO.findAll();
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            List<TipoDTO> tipos = new ArrayList<>();
            for (Tipo tipo : respuesta.getResultados()) {
                tipos.add(new TipoDTO(tipo));
            }
            respuestaDTO.setResultados(tipos);
        } else {
            respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
        }
        return respuestaDTO;
        
    }
}
