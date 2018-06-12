/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.mb;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import mx.com.ipn.escom.identidadGenero.util.GenericMB;
import mx.com.ipn.escom.identidadGenero.util.NavigationConstants;
import mx.ipn.escom.dto.CuestionariosDTO;
import mx.ipn.escom.ejb.QuestionariesEJB;
import mx.ipn.escom.modelo.Categoria;
import mx.ipn.escom.modelo.OpcionRespuesta;
import mx.ipn.escom.modelo.Pregunta;
import mx.ipn.escom.modelo.Puntuacion;
import mx.ipn.escom.modelo.Seccion;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "cuestionariosMB")
@SessionScoped
public class CuestionariosMB extends GenericMB implements Serializable {

    @EJB
    QuestionariesEJB questionariesEJB;
    @Inject
    CatalogMB catalogMB;
    private CuestionariosDTO cuestionarioDTO;

    public CuestionariosMB() {
        cuestionarioDTO = new CuestionariosDTO();
    }

    @PostConstruct
    public void init() {

    }

    public String probarGenero(Object t) {
        if (t.equals(1L)) {
            return getMessage("detailQuestionary.pM");
        } else {
            return getMessage("detailQuestionary.pF");
        }

    }

    public void addCategory() {
        Respuesta<CuestionariosDTO> respuestaDTO
                = questionariesEJB.addCategory(cuestionarioDTO,
                        getMessage("addQuestionary.newCategory"),
                        getMessage("addQuestionary.newSection"),
                        getMessage("editQuestion.newQuestion"),
                        getMessage("editAnswer.newOption"));
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addCategory.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addCategory.error");
        }

    }

    public void removeCategory(Long idCategory, String nombreCategoria) {
        Respuesta<CuestionariosDTO> respuesta
                = questionariesEJB.removeCategory(cuestionarioDTO, idCategory,
                        nombreCategoria);
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteCategory.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_ERROR, "deleteCategory.fail");
        }
    }

    public void removeSection(Long idCategoria, Long idSection,
            String nameSection, String nameCategory) {
        Respuesta<CuestionariosDTO> respuesta
                = questionariesEJB.removeSection(cuestionarioDTO, idCategoria,
                        idSection, nameSection, nameCategory);
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteSection.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteSection.fail");
        }
    }

    public void removeQuestion(Long idCategory, Long idSection, Long idQuestion,
            String nameSection, String nameCategory, String nameQuestion) {
        Respuesta<CuestionariosDTO> respuesta
                = questionariesEJB.removeQuestion(cuestionarioDTO, idCategory,
                        idSection, idQuestion, nameSection,
                        nameCategory, nameQuestion);
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteQuestion.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_ERROR, "deleteQuestion.fail");
        }

    }

    public Respuesta<CuestionariosDTO> removeOption(Long idCategory, Long idSection, Long idQuestion,
            Long idOption, String nameSection, String nameCategory,
            String nameQuestion, String nameOption) {
        Respuesta<CuestionariosDTO> respuestaDTO
                = questionariesEJB.removeOption(cuestionarioDTO, idCategory,
                        idSection, idQuestion, idOption, nameSection,
                        nameCategory, nameQuestion, nameOption);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteOption.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteOption.fail");
        }
        return respuestaDTO;
    }

    public void addSection(Long idCategoy, String nameCategory) {
        Respuesta<CuestionariosDTO> respuestaDTO
                = questionariesEJB.addSection(cuestionarioDTO,
                        idCategoy, getMessage("addQuestionary.newSection"),
                        nameCategory);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addSection.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addSection.fail1");
        }
    }

    public void addQuestion(Long idCategory, Long idSection, String nameCategory,
            String nameSection) {
        Respuesta<CuestionariosDTO> respuestaDTO
                = questionariesEJB.addQuestion(cuestionarioDTO,
                        idCategory, idSection, nameCategory, nameSection,
                        getMessage("editQuestion.newQuestion"));
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addQuestion.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addQuestion.fail");
        }
    }

    public void addOption(Long idCategory, Long idSection, Long idQuestion,
            String nameCategory, String nameSection, String nameQuestion) {
        Respuesta<CuestionariosDTO> respuestaDTO
                = questionariesEJB.addOption(cuestionarioDTO,
                        idCategory, idSection, idQuestion, nameCategory,
                        nameSection, nameQuestion,
                        getMessage("editAnswer.newOption"));
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addOption.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addOption.fail");
        }
    }

    @Override
    public String prepareAdd() {
        return NavigationConstants.ADDCUESTIONARIO;
    }

    @Override
    public String add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String prepareUpdate() {
        return NavigationConstants.EDITCUESTIONARIO;
    }

    @Override
    public String update() {
        Respuesta<CuestionariosDTO> respuestaDTO
                = questionariesEJB.updateQuestionary(cuestionarioDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.ERROR) {
            if (cuestionarioDTO.getCaseFail() != null) {
                addMessage("global.error", "globalMSG",
                        FacesMessage.SEVERITY_ERROR,
                        respuestaDTO.getMensaje(),
                        cuestionarioDTO.getCaseFail());
            } else {
                addMessage("global.error", "globalMSG",
                        FacesMessage.SEVERITY_ERROR,
                        respuestaDTO.getMensaje());
            }
            return null;
        } else {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "editQuestionary.success");
            cuestionarioDTO = new CuestionariosDTO();
            catalogMB.updateQuestionaries();
            return NavigationConstants.VIEWCUESTIONARIOSWR;
        }
    }

    @Override
    public String prepareDelete() {
        return NavigationConstants.DELETECUESTIONARIO;
    }

    @Override
    public String delete() {
        System.out.println(cuestionarioDTO.getEntidad().getId());
        Respuesta<CuestionariosDTO> respuestaDTO = questionariesEJB
                .delete(cuestionarioDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteQuestionary.success");
            catalogMB.updateQuestionaries();
            catalogMB.updateSections();
            catalogMB.updateCategories();
            cuestionarioDTO = new CuestionariosDTO();
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteQuestionary.fail");
        }
        return NavigationConstants.VIEWCUESTIONARIOSWR;
    }

    @Override
    public String viewDetail() {
        return NavigationConstants.DETAILQUESTIONARY;
    }

    @Override
    public String clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String back() {
        return NavigationConstants.VIEWCUESTIONARIOS;
    }

    @Override
    public String find() {
        Respuesta<CuestionariosDTO> respuestaDTO = questionariesEJB
                .findById(cuestionarioDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            cuestionarioDTO = respuestaDTO.getResultado();
//            for (Categoria categoria : cuestionarioDTO.getEntidad().getCategoriaList()) {
//                System.out.println(categoria.getNombre());
//                for (Seccion seccion : categoria.getSeccionList()) {
//                    System.out.println(seccion.getNombre() + " " + seccion.getId());
//                    for (Pregunta pregunta : seccion.getPreguntaList()) {
//                        System.out.println(pregunta.getPregunta());
//                        for (OpcionRespuesta opcion : pregunta.getOpcionRespuestaList()) {
//                            System.out.println(opcion.getOpcion());
//                            for (Puntuacion puntuacion : opcion.getPuntuacionList()) {
//                                System.out.println("puntuacion tipo " + puntuacion.getIdgenero().getId());
//                            }
//                        }
//                    }
//                }
//            }
        }
        return null;
    }

    public CuestionariosDTO getCuestionarioDTO() {
        return cuestionarioDTO;
    }

    public void setCuestionarioDTO(CuestionariosDTO cuestionarioDTO) {
        this.cuestionarioDTO = cuestionarioDTO;
    }

}
