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
import mx.ipn.escom.dao.GeneroDAO;
import mx.ipn.escom.dto.GeneroDTO;
import mx.ipn.escom.modelo.Genero;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@LocalBean
public class GeneroEJB {

    @EJB
    GeneroDAO generoDAO;

    public Respuesta<GeneroDTO> findAll() {
        Respuesta<Genero> respuesta = generoDAO.findAll();
        Respuesta<GeneroDTO> respuestaDTO = new Respuesta<>();
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            List<GeneroDTO> institutions = new ArrayList();
            for (Genero genero : respuesta.getResultados()) {
                institutions.add(new GeneroDTO(genero));
            }
            respuestaDTO.setResultados(institutions);
        } else {
            respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
        }
        return respuestaDTO;

    }
}
