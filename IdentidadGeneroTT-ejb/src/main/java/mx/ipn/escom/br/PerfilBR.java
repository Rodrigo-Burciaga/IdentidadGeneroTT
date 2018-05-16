/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.br;

import javax.ejb.Local;
import javax.ejb.Stateless;
import mx.ipn.escom.dto.LoginDTO;
import mx.ipn.escom.util.CodigoRespuestaBR;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@Local
public class PerfilBR {

    public int isValidBR001(LoginDTO loginDTO) {
        return (loginDTO.getEntidad().getUsuario() != null
                && loginDTO.getEntidad().getNombre() != null
                && loginDTO.getEntidad().getApellido1() != null
                && loginDTO.getEntidad().getContraseña() != null
                && loginDTO.getEntidad().getNumeroempleado() != null
                && loginDTO.getEntidad().getTelefono() != null
                && loginDTO.getEntidad().getCorreoelectronico() != null)
                ? CodigoRespuestaBR.OK : CodigoRespuestaBR.ERROR;
    }

    public int isValidBR002(LoginDTO loginDTO) {
        return (loginDTO.getPassRepeated().equals(loginDTO.getEntidad().
                getContraseña())) ? CodigoRespuestaBR.OK : CodigoRespuestaBR.ERROR;
    }
}
