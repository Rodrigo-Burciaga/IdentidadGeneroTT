/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.mb;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import mx.com.ipn.escom.identidadGenero.util.GenericMB;
import mx.com.ipn.escom.identidadGenero.util.NavigationConstants;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "academicosMB")
@SessionScoped
public class AcademicosMB extends GenericMB implements Serializable {

    /**
     * Creates a new instance of AcademicosMB
     */
    public AcademicosMB() {
    }

    @Override
    public String prepareAdd() {
       return NavigationConstants.ADDACADEMICO;
    }

    @Override
    public String add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String prepareUpdate() {
        return NavigationConstants.EDITACADEMICO;
    }

    @Override
    public String update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String prepareDelete() {
        return NavigationConstants.DELETEACADEMICO;
    }

    @Override
    public String delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String viewDetail() {
        return NavigationConstants.VIEWACADEMICOS;
    }

    @Override
    public String clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String back() {
        return NavigationConstants.VIEWACADEMICOS;
    }

    @Override
    public String find() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
