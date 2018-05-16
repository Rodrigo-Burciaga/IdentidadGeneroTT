/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import mx.ipn.escom.br.PerfilBR;
import mx.ipn.escom.dao.GenericDAO;
import mx.ipn.escom.dto.LoginDTO;
import mx.ipn.escom.modelo.Administrador;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.CodigoRespuestaBR;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@LocalBean
public class PerfilEJB {

    @EJB
    PerfilBR perfilBR;
    @EJB
    GenericDAO genericDAO;

    public PerfilEJB() {
    }

    public Respuesta<LoginDTO> validateUpdate(LoginDTO loginDTO) {
        Respuesta<LoginDTO> respuestaDTO = new Respuesta<>();
        if (perfilBR.isValidBR001(loginDTO) == CodigoRespuestaBR.OK) {
            if (perfilBR.isValidBR002(loginDTO) == CodigoRespuestaBR.OK) {
                Respuesta respuesta
                        = genericDAO.update(loginDTO.getEntidad());
                if(respuesta.getCodigo() == CodigoRespuesta.ERROR){
                    respuestaDTO.setMensaje("global.errorModificacion");
                    respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
                }
            } else {
                respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
                respuestaDTO.setMensaje("editPerfil.BR002");
            }
        } else {
            respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
            respuestaDTO.setMensaje("global.BR001");
        }
        return respuestaDTO;
    }

    public Respuesta<LoginDTO> findByID(LoginDTO loginDTO) {
        Respuesta<LoginDTO> respuestaDTO = new Respuesta<>();
        if (loginDTO.getEntidad().getId() != null) {
            Respuesta<Administrador> admin = genericDAO.
                    findByID(Administrador.class, loginDTO.getEntidad().getId());
            if (admin.getCodigo() == CodigoRespuesta.OK) {
                respuestaDTO.setResultado(new LoginDTO(admin.getResultado()));
            } else {
                respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
            }
        } else {
            respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
        }
        return respuestaDTO;
    }
}
