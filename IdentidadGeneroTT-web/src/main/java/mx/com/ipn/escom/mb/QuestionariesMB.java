/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.mb;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import mx.com.ipn.escom.identidadGenero.util.GenericMB;
import mx.com.ipn.escom.identidadGenero.util.NavigationConstants;
import mx.ipn.escom.dto.CategoriaDTO;
import mx.ipn.escom.dto.OpcionRespuestaDTO;
import mx.ipn.escom.dto.QuestionDTO;
import mx.ipn.escom.dto.QuestionaryDTO;
import mx.ipn.escom.dto.SectionDTO;
import mx.ipn.escom.ejb.QuestionariesEJB;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "questionariesMB")
@ViewScoped
public class QuestionariesMB extends GenericMB implements Serializable {

    @Inject
    CatalogMB catalogMB;
    @EJB
    QuestionariesEJB questionariesEJB;

    private QuestionaryDTO questionaryDTO;
    private Integer noQuestions;

    public QuestionariesMB() {

    }

    @PostConstruct
    public void init() {
        System.out.println("------------------------------------------------------------------------");
        questionaryDTO = new QuestionaryDTO();
        noQuestions = 1;
        List<CategoriaDTO> categorias = catalogMB.getCategorias();
        if (categorias != null) {
            questionaryDTO.setCategorias(categorias);
        }
        List<SectionDTO> secciones = catalogMB.getSecciones();
        if (secciones != null) {
            questionaryDTO.setSecciones(secciones);
            for (SectionDTO section : secciones) {
                section.getEntidad().setNombre(section.getEntidad().getNombre());
            }
        }
    }

    public void addCategory() {
        Respuesta<QuestionaryDTO> respuestaDTO = questionariesEJB.
                addCategory(questionaryDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG", FacesMessage.SEVERITY_INFO,
                    "addCategory.success");
        } else {
            addMessage("global.error", "globalMSG", FacesMessage.SEVERITY_ERROR,
                    "addCategory.fail");
        }
        questionaryDTO.setNombreCategoria(null);
    }

    public void addSection() {
        Respuesta<QuestionaryDTO> respuestaDTO = questionariesEJB.
                addSection(questionaryDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG", FacesMessage.SEVERITY_INFO,
                    "addSection.success");
        } else {
            addMessage("global.error", "globalMSG", FacesMessage.SEVERITY_ERROR,
                    "addSection.fail");
        }
        questionaryDTO.setNombreSeccion(null);

    }

    public void addQuestion() {
        Respuesta<QuestionaryDTO> respuestaDTO
                = questionariesEJB.addQuestion(questionaryDTO,
                        getMessage("addQuestionary.questionName"), noQuestions,
                        getMessage("addQuestionary.option"));
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG", FacesMessage.SEVERITY_INFO,
                    "addQuestion.success");
            this.noQuestions++;
        } else {
            addMessage("global.error", "globalMSG", FacesMessage.SEVERITY_INFO,
                    "addQuestion.fail");
        }

    }

    public void addOption(QuestionDTO questionDTO) {
        Respuesta<QuestionaryDTO> respuestaDTO
                = questionariesEJB.addOption(questionDTO,
                        getMessage("addQuestionary.option"));
    }

    public void removeOption(OpcionRespuestaDTO opcionRespuestaDTO,
            QuestionDTO questionDTO) {
        Respuesta<QuestionaryDTO> respuestaDTO
                = questionariesEJB.removeOption(opcionRespuestaDTO, questionDTO);
    }

    public void removeQuestion(QuestionDTO questionDTO) {
        Respuesta<QuestionaryDTO> respuestaDTO
                = questionariesEJB.removeQuestion(questionDTO, questionaryDTO);
    }

    @Override
    public String prepareAdd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String add() {
        System.out.println("entrando Add");
        System.out.println("imprimiendo datos: " + questionaryDTO.getNombreCuestinario()
                + "\n");
        for (QuestionDTO questionDTO : questionaryDTO.getQuestionsDTO()) {
            System.out.println("PRegunta");
            System.out.println(questionDTO.getEntidad().getPregunta() + "\n"
                    + questionDTO.getIdTipo() + "\n" + questionDTO.getCategoriaSelected()
                    + "\n" + questionDTO.getSeccionSelected());
            for (OpcionRespuestaDTO opcionRespuesta : questionDTO
                    .getOpcionRespuestaDTO()) {
                System.out.println("Opcion Respuesta");
                System.out.println(opcionRespuesta.getEntidad().getOpcion());
                System.out.println(opcionRespuesta.getRatingM());
                System.out.println(opcionRespuesta.getRatingF());

            }
        }
        Respuesta<QuestionaryDTO> respuesta
                = questionariesEJB.addQuestionary(questionaryDTO);
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            questionaryDTO = new QuestionaryDTO();
            addMessage("global.success", "globalMSG", FacesMessage.SEVERITY_INFO,
                    "addQuestionary.success");
            catalogMB.updateQuestionaries();
            catalogMB.updateSections();
            catalogMB.updateCategories();
            return NavigationConstants.VIEWCUESTIONARIOSWR;
        } else {
            
            if (questionaryDTO.getNumFail() != null) {
                addMessage("global.error", "globalMSG", FacesMessage.SEVERITY_ERROR,
                        respuesta.getMensaje(), questionaryDTO.getNumFail());
            } else {
                addMessage("global.error", "globalMSG", FacesMessage.SEVERITY_ERROR,
                        respuesta.getMensaje());
            }
            return null;
        }

    }

    @Override
    public String prepareUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getNoQuestions() {
        return noQuestions;
    }

    public void setNoQuestions(Integer noQuestions) {
        this.noQuestions = noQuestions;
    }

    public QuestionaryDTO getQuestionaryDTO() {
        return questionaryDTO;
    }

    public void setQuestionaryDTO(QuestionaryDTO questionaryDTO) {
        this.questionaryDTO = questionaryDTO;
    }
}
