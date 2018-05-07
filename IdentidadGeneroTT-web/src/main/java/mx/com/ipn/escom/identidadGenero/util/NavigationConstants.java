/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.identidadGenero.util;

/**
 *
 * @author andii-burciaga
 */
public class NavigationConstants {

    /**
     * IDS For menu LeftBar for the navigation
     */
    public final static String M1 = "M1";
    public final static String M2 = "M2";
    public final static String M3 = "M3";
    public final static String M4 = "M4";
    public final static String INDEX = "index";
    /**
     * Navigation for institutions
     */
    public final static String ADDINSTITUTION = "addInstitution";
    public final static String VIEWINSTITUTIONS = "viewInstitutions";
    public final static String EDITINSTITUTION = "editInstitution";
    public final static String DELETEINSTITUTION = "deleteInstitution";
    public final static String DETAILINSTITUCION = "detailInstitution";
    public final static String VIEWACADEMICOS = "viewAcademicos";
    public final static String VIEWCUESTIONARIOS = "viewCuestionarios";
    public final static String EDITACADEMICO = "editAcademico";
    public final static String DELETEACADEMICO = "deleteAcademico";
    public final static String ADDACADEMICO = "addAcademico";
    public final static String ADDCUESTIONARIO = "addCuestionario";
    public final static String EDITCUESTIONARIO = "editCuestionario";
    public final static String DELETECUESTIONARIO = "deleteCuestionario";
    public final static String VIEWSTATISTICS = "viewStatistics";

    public enum MenuNav {
        M1(NavigationConstants.VIEWINSTITUTIONS),
        M2(NavigationConstants.VIEWCUESTIONARIOS),
        M3(NavigationConstants.VIEWACADEMICOS),
        M4(NavigationConstants.VIEWSTATISTICS);

        private final String navigation;

        MenuNav(String navigation) {
            this.navigation = navigation;
        }

        public String getNavRule() {
            return navigation;
        }
    }

    public static String navigate(String caseTo) {
        switch (caseTo) {
            case M1:
                return MenuNav.M1.getNavRule();
            case M2:
                return MenuNav.M2.getNavRule();
            case M3:
                return MenuNav.M3.getNavRule();
            case M4:
                return MenuNav.M4.getNavRule();
            default:
                return INDEX;
        }
    }

}
