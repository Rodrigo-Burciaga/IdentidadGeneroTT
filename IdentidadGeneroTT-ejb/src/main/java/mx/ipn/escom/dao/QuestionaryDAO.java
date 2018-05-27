/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.ipn.escom.dto.QuestionaryDTO;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@LocalBean
public class QuestionaryDAO {

    @PersistenceContext(unitName = "identidadPU")
    private EntityManager em;

    private final String questionaryByName = "Select c from Cuestionario c where c.nombre ="
            + ":name";

    public Respuesta findQuestionaryByName(QuestionaryDTO questionaryDTO) {
        Respuesta respuesta = new Respuesta();
        try {
            Query query = em.createQuery(questionaryByName);
            query.setParameter("name", questionaryDTO.getNombreCuestinario());
            Object questionary = query.getSingleResult();
            respuesta.setResultado(questionary);

        } catch (Exception e) {
            respuesta.setMensaje(e.getMessage());
            respuesta.setCodigo(CodigoRespuesta.ERROR);
        }
        em.flush();
        return respuesta;
    }

}
