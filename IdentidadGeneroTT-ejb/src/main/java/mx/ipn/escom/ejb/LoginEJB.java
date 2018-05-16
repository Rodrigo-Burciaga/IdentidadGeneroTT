/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import mx.ipn.escom.br.LoginBR;
import mx.ipn.escom.dao.LoginDAO;
import mx.ipn.escom.dto.LoginDTO;
import mx.ipn.escom.modelo.Administrador;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.CodigoRespuestaBR;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateful
public class LoginEJB {

    @EJB
    LoginBR loginBR;
    @EJB
    LoginDAO loginDAO;

    public Respuesta<LoginDTO> validateAccess(LoginDTO loginDTO) {
        Respuesta<LoginDTO> respuestaDTO = new Respuesta<>();
        if (loginBR.isValidBR001(loginDTO) == CodigoRespuestaBR.OK) {
            Respuesta<Administrador> respuesta = loginDAO.validateAccess(loginDTO);
            if (respuesta.getCodigo() == CodigoRespuesta.OK) {
                respuestaDTO.setResultado(new LoginDTO(respuesta.getResultado()));
            } else {
                respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
            }
        } else {
            respuestaDTO.setMensaje("Error Regla de Negocio "+ LoginEJB.class);
        }
        return respuestaDTO;
    }
    
   
}
