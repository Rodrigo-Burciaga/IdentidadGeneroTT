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
import mx.ipn.escom.dao.CategoriasDAO;
import mx.ipn.escom.dto.CategoriaDTO;
import mx.ipn.escom.modelo.Categoria;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@LocalBean
public class CategoriasEJB {

    @EJB
    CategoriasDAO categoriasDAO;

    public Respuesta<CategoriaDTO> findAll() {
        Respuesta<Categoria> respuesta = categoriasDAO.findAll();
        Respuesta<CategoriaDTO> respuestaDTO = new Respuesta<>();
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            List<CategoriaDTO> categorias = new ArrayList<>();
            for (Categoria categoria : respuesta.getResultados()) {
                categorias.add(new CategoriaDTO(categoria));
            }
            respuestaDTO.setResultados(categorias);
        } else {
            respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
        }
        return respuestaDTO;
    }
}
