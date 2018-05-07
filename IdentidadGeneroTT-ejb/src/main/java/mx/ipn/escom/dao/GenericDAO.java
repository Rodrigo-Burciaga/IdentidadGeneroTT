/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.dao;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateless
public class GenericDAO implements Serializable {

    @PersistenceContext(unitName = "identidadPU")
    private EntityManager em;

    public Respuesta save(Object entidad) {
        Respuesta respuesta = new Respuesta();
        try {
            em.persist(entidad);
            em.flush();
        } catch (Exception e) {
            respuesta.setCodigo(CodigoRespuesta.ERROR);
            respuesta.setMensaje(e.toString());
        }
        return respuesta;
    }

    public <T> Respuesta findByID(Class<T> clase, Object id) {
        Respuesta<Object> respuesta = new Respuesta<>();
        Object entidad = em.find(clase, id);
        if (entidad == null) {
            respuesta.setCodigo(CodigoRespuesta.ERROR);
        } else {
            respuesta.setResultado(entidad);
        }
        return respuesta;
    }
}
