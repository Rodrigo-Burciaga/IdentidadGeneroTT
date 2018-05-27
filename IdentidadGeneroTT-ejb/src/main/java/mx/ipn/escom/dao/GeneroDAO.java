/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@LocalBean
public class GeneroDAO {

    @PersistenceContext(unitName = "identidadPU")
    private EntityManager em;

    public Respuesta findAll() {
        Respuesta<Object> respuesta = new Respuesta();
        Query query = em.createNamedQuery("Genero.findAll");
        List generos = query.getResultList();
        if (generos == null) {
            respuesta.setCodigo(CodigoRespuesta.ERROR);
        } else {
            respuesta.setResultados(generos);
        }
        return respuesta;
    }

}

