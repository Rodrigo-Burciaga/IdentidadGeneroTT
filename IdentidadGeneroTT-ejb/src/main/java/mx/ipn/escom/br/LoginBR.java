/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.br;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import mx.ipn.escom.dto.LoginDTO;
import mx.ipn.escom.util.CodigoRespuestaBR;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@LocalBean
public class LoginBR {

    public int isValidBR001(LoginDTO loginDTO) {
        return (loginDTO.getEntidad().getUsuario() != null
                && loginDTO.getEntidad().getContraseña() != null) ? CodigoRespuestaBR.OK
                : CodigoRespuestaBR.ERROR;
    }
}
