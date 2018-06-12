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
    public final static String INSTITUTIONS = "INSTITUTIONS";
    public final static String ADMINISTRATORS = "ADMINISTRATORS";
    public final static String INDEX = "INDEX";
    public final static String LOGIN = "LOGIN";
    public final static String REDIRECT = "?faces-redirect=true";
    public final static String PERFIL = "PERFIL";

    /**
     * Navigation for institutions
     */
    public final static String ADDINSTITUTION = "addInstitution";
    public final static String VIEWADMINISTRATORS = "/administrator/viewAdministrators" + REDIRECT;
    public final static String VIEWINSTITUTIONS = "/administrator/viewInstitutions" + REDIRECT;
    public final static String VIEWINSTITUTIONSWR = "/administrator/viewInstitutions";
    public final static String EDITINSTITUTION = "/administrator/editInstitution" + REDIRECT;
    public final static String DELETEINSTITUTION = "deleteInstitution";
    public final static String DETAILINSTITUCION = "/administrator/detailInstitution" + REDIRECT;
    public final static String DETAILINSTITUCIONWR = "/administrator/detailInstitution";
    public final static String VIEWACADEMICOS = "viewAcademicos";
    public final static String VIEWCUESTIONARIOS = "/InstitutionRepresentative/viewQuestionaries" + REDIRECT;
    public final static String VIEWCUESTIONARIOSWR = "/InstitutionRepresentative/viewQuestionaries";
    public final static String EDITACADEMICO = "editAcademico";
    public final static String DELETEACADEMICO = "deleteAcademico";
    public final static String ADDACADEMICO = "addAcademico";
    public final static String ADDCUESTIONARIO = "/InstitutionRepresentative/addQuestionary" + REDIRECT;
    public final static String ADDCUESTIONARIOWR = "/InstitutionRepresentative/addQuestionary";
    public final static String EDITCUESTIONARIO = "/InstitutionRepresentative/editCuestionario" + REDIRECT;
    public final static String DELETECUESTIONARIO = "/InstitutionRepresentative/deleteCuestionario" + REDIRECT;
    public final static String VIEWSTATISTICS = "viewStatistics";
    public final static String TOLOGIN = "/login" + REDIRECT;
    public final static String LOGINWR = "/login";
    public final static String TOINDEX = "/index.xhtml" + REDIRECT;
    public final static String INADMIN = "/administrator/indexAdmin" + REDIRECT;
    public final static String TOPERFIL = "/administrator/detailPerfil" + REDIRECT;
    public final static String TOPERFILWR = "/administrator/detailPerfil";
    public final static String EDITPERFIL = "/administrator/editPerfil" + REDIRECT;
    public final static String EDITPERFILWR = "/administrator/editPerfil";
    public final static String DETAILQUESTIONARY = "/InstitutionRepresentative/detailQuestionary" + REDIRECT;

    public enum MenuNav {
        INSTITUTIONS(NavigationConstants.VIEWINSTITUTIONS),
        ADMINISTRATORS(NavigationConstants.VIEWADMINISTRATORS),
        M2(NavigationConstants.VIEWCUESTIONARIOS),
        M3(NavigationConstants.VIEWACADEMICOS),
        M4(NavigationConstants.VIEWSTATISTICS),
        LOGIN(NavigationConstants.TOLOGIN),
        INDEX(NavigationConstants.TOINDEX),
        PERFIL(NavigationConstants.TOPERFIL);

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
            case INSTITUTIONS:
                return MenuNav.INSTITUTIONS.getNavRule();
            case ADMINISTRATORS:
                return MenuNav.ADMINISTRATORS.getNavRule();
            case INDEX:
                return MenuNav.INDEX.getNavRule();
            case LOGIN:
                return MenuNav.LOGIN.getNavRule();
            case PERFIL:
                return MenuNav.PERFIL.getNavRule();
            default:
                return "/index.xhtml?faces-redirect=true";
        }
    }

}
