/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.mb;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.servlet.http.HttpSession;
import mx.com.ipn.escom.identidadGenero.util.GenericMB;
import mx.com.ipn.escom.identidadGenero.util.NavigationConstants;
import mx.com.ipn.escom.identidadGenero.util.SessionUtils;
import mx.ipn.escom.dto.LoginDTO;
import mx.ipn.escom.ejb.LoginEJB;
import mx.ipn.escom.ejb.PerfilEJB;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "loginMB")
@SessionScoped
public class LoginMB extends GenericMB implements Serializable {

    @EJB
    LoginEJB loginEJB;
    @EJB
    PerfilEJB perfilEJB;

    private LoginDTO loginDTO = new LoginDTO();

    public LoginMB() {
    }

    public String validateAccess() {
        Respuesta<LoginDTO> respuestaDTO = loginEJB.validateAccess(loginDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            loginDTO = respuestaDTO.getResultado();
            loginDTO.setLogged(true);
            HttpSession sesion = SessionUtils.getSession();
            sesion.setAttribute("admin", loginDTO.getEntidad());
            return NavigationConstants.INADMIN;
        }
        addMessage("global.error", "globalMSG",
                FacesMessage.SEVERITY_ERROR, "global.failLogin");
        return NavigationConstants.LOGINWR;
    }

    public void formatDate() {
        loginDTO.setFechaInicio(loginDTO.getFt().format(loginDTO.getEntidad()
                .getFechainicio()));
    }

    @Override
    public String prepareAdd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String prepareUpdate() {
        return NavigationConstants.EDITPERFIL;
    }

    @Override
    public String update() {
        
        //TODO: Falta verificar que no exista el nuevo usuario!!!!!!!!!!!!!!!!!!!
        Respuesta<LoginDTO> respuesta = perfilEJB.validateUpdate(loginDTO);
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG", FacesMessage.SEVERITY_INFO,
                    "global.successModify");
            return NavigationConstants.TOPERFILWR;
        }
        if (respuesta.getMensaje().equals("global.errorModificacion")) {
            respuesta = perfilEJB.findByID(loginDTO);
            if (respuesta.getCodigo() == CodigoRespuesta.OK) {
                loginDTO = respuesta.getResultado();
            }
            addMessage("global.error", "globalMSG", FacesMessage.SEVERITY_ERROR,
                    respuesta.getMensaje());
            return NavigationConstants.TOPERFILWR;
        }
        addMessage("global.error", "globalMSG", FacesMessage.SEVERITY_ERROR,
                respuesta.getMensaje());
        return NavigationConstants.EDITPERFILWR;
    }

    @Override
    public String prepareDelete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String viewDetail() {
        return NavigationConstants.EDITPERFIL;
    }

    @Override
    public String clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String back() {
        return NavigationConstants.TOPERFIL;
    }

    @Override
    public String find() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LoginDTO getLoginDTO() {
        return loginDTO;
    }

    public void setLoginDTO(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }
}
