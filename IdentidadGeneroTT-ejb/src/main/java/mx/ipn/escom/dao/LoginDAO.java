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
import mx.ipn.escom.dto.LoginDTO;
import mx.ipn.escom.modelo.Administrador;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@LocalBean
public class LoginDAO {

    @PersistenceContext(unitName = "identidadPU")
    private EntityManager em;
    private String queryAccess = "SELECT a FROM Administrador a where a.usuario = "
            + ":user and a.contraseña = :pass";

    public Respuesta validateAccess(LoginDTO loginDTO) {

        Respuesta respuesta = new Respuesta();
        try {
            Query query = em.createQuery(queryAccess);
            query.setParameter("user", loginDTO.getEntidad().getUsuario());
            query.setParameter("pass", loginDTO.getEntidad().getContraseña());
            Object admin = query.getSingleResult();
            respuesta.setResultado(admin);

        } catch (Exception e) {
            respuesta.setMensaje(e.getMessage());
            respuesta.setCodigo(CodigoRespuesta.ERROR);
        }
        em.flush();
        return respuesta;
    }
}
