/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.br;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import mx.com.ipn.escom.war.mb.CodigoRespuestaValidaciones;
import mx.com.ipn.escom.war.mb.ValidatorField;
import mx.com.ipn.escom.war.mb.ValidatorPage;
import mx.ipn.escom.dao.QuestionaryDAO;
import mx.ipn.escom.dto.CategoriaDTO;
import mx.ipn.escom.dto.OpcionRespuestaDTO;
import mx.ipn.escom.dto.QuestionDTO;
import mx.ipn.escom.dto.QuestionaryDTO;
import mx.ipn.escom.dto.SectionDTO;
import mx.ipn.escom.modelo.Cuestionario;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.CodigoRespuestaBR;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@LocalBean
public class QuestionariesBR {

    @EJB
    QuestionaryDAO questionaryDAO;

    public int isValidFieldNombre(QuestionaryDTO questionaryDTO) {
        if (questionaryDTO.getNombreCuestinario() != null
                && (ValidatorField.ValidateField(ValidatorPage.Fields.ADDQUESTIONARY_NAME, questionaryDTO
                        .getNombreCuestinario()))
                == CodigoRespuestaValidaciones.OK) {
            return CodigoRespuestaBR.OK;
        }
        return CodigoRespuestaBR.ERROR;
    }

    public int isValidFieldCategory(QuestionaryDTO questionaryDTO) {
        return (ValidatorField.
                ValidateField(ValidatorPage.Fields.ADDQUESTIONARY_CATEGORY,
                        questionaryDTO.getNombreCategoria())
                == CodigoRespuestaValidaciones.OK) ? CodigoRespuestaBR.OK
                        : CodigoRespuestaBR.ERROR;

    }

    public int isValidFieldSection(QuestionaryDTO questionaryDTO) {
        return (ValidatorField.
                ValidateField(ValidatorPage.Fields.ADDQUESTIONARY_SECTION,
                        questionaryDTO.getNombreSeccion())
                == CodigoRespuestaValidaciones.OK) ? CodigoRespuestaBR.OK
                        : CodigoRespuestaBR.ERROR;

    }

    public int noRepeatedCategory(QuestionaryDTO questionaryDTO) {
        for (CategoriaDTO categoria : questionaryDTO.getCategorias()) {
            if (categoria.getEntidad().getNombre().
                    equals(questionaryDTO.getNombreCategoria())) {
                return CodigoRespuestaBR.ERROR;
            }
        }
        return CodigoRespuestaBR.OK;
    }

    public int noRepeatedSection(QuestionaryDTO questionaryDTO) {
        for (SectionDTO seccion : questionaryDTO.getSecciones()) {
            if (seccion.getEntidad().getNombre().
                    equals(questionaryDTO.getNombreSeccion())) {
                return CodigoRespuestaBR.ERROR;
            }
        }
        return CodigoRespuestaBR.OK;
    }

    public int isSelectedCategory(QuestionaryDTO questionaryDTO) {
        int codeResponse = CodigoRespuestaBR.OK;
        for (QuestionDTO questionDTO : questionaryDTO.getQuestionsDTO()) {
            if (questionDTO.getCategoriaSelected() == null) {
                codeResponse = CodigoRespuesta.ERROR;
                break;
            }
        }
        return codeResponse;
    }

    public int isSelectedSection(QuestionaryDTO questionaryDTO) {
        int codeResponse = CodigoRespuestaBR.OK;
        for (QuestionDTO questionDTO : questionaryDTO.getQuestionsDTO()) {
            if (questionDTO.getSeccionSelected() == null) {
                codeResponse = CodigoRespuesta.ERROR;
                break;
            }
        }
        return codeResponse;
    }

    public int notEmptyQuestions(QuestionaryDTO questionaryDTO) {
        return (!questionaryDTO.getQuestionsDTO().isEmpty())
                ? CodigoRespuestaBR.OK : CodigoRespuestaBR.ERROR;
    }

    public int isValidNameQuestion(QuestionaryDTO questionaryDTO) {
        int codeResponse = CodigoRespuestaBR.OK;
        for (QuestionDTO questionDTO : questionaryDTO.getQuestionsDTO()) {
            if (ValidatorField.
                    ValidateField(ValidatorPage.Fields.ADDQUESTIONARY_NAMEQUESTION, questionDTO
                            .getEntidad().getPregunta())
                    == CodigoRespuestaValidaciones.ERROR) {
                codeResponse = CodigoRespuestaBR.ERROR;
                break;
            }
        }
        return codeResponse;
    }

    public int notEmptyTypeQuestion(QuestionaryDTO questionaryDTO) {
        int codeResponse = CodigoRespuestaBR.OK;
        for (QuestionDTO questionDTO : questionaryDTO.getQuestionsDTO()) {
            if (questionDTO.getIdTipo() == null) {
                codeResponse = CodigoRespuestaBR.ERROR;
            }
        }
        return codeResponse;
    }

    public int notEmptyOptions(QuestionaryDTO questionaryDTO) {
        int codeResponse = CodigoRespuestaBR.OK;
        for (QuestionDTO questionDTO : questionaryDTO.getQuestionsDTO()) {
            if (questionDTO.getOpcionRespuestaDTO().isEmpty()) {
                codeResponse = CodigoRespuestaBR.ERROR;
                break;
            }
        }
        return codeResponse;
    }

    public int isValidOption(QuestionaryDTO questionaryDTO) {
        int codeResponse = CodigoRespuestaBR.OK;
        for (QuestionDTO questionDTO : questionaryDTO.getQuestionsDTO()) {
            for (OpcionRespuestaDTO op : questionDTO.getOpcionRespuestaDTO()) {
                if (ValidatorField
                        .ValidateField(ValidatorPage.Fields.ADDQUESTIONARY_OPTION,
                                op.getEntidad().getOpcion())
                        == CodigoRespuestaValidaciones.ERROR) {
                    codeResponse = CodigoRespuestaBR.ERROR;
                    break;
                }
            }
        }
        return codeResponse;
    }

    public int isValidPM(QuestionaryDTO questionaryDTO) {
        int codeResponse = CodigoRespuestaBR.OK;
        for (QuestionDTO questionDTO : questionaryDTO.getQuestionsDTO()) {
            for (OpcionRespuestaDTO op : questionDTO.getOpcionRespuestaDTO()) {
                if (op.getRatingM() == null) {
                    codeResponse = CodigoRespuestaBR.ERROR;
                    break;
                }
            }
        }
        return codeResponse;
    }

    public int isValidPF(QuestionaryDTO questionaryDTO) {
        int codeResponse = CodigoRespuestaBR.OK;
        for (QuestionDTO questionDTO : questionaryDTO.getQuestionsDTO()) {
            for (OpcionRespuestaDTO op : questionDTO.getOpcionRespuestaDTO()) {
                if (op.getRatingF() == null) {
                    codeResponse = CodigoRespuestaBR.ERROR;
                    break;
                }
            }
        }
        return codeResponse;
    }

    public int noRepeatedQuestionary(QuestionaryDTO questionaryDTO) {
        int codeResponse = CodigoRespuestaBR.OK;
        Respuesta<Cuestionario> respuesta = questionaryDAO
                .findQuestionaryByName(questionaryDTO);
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            codeResponse = CodigoRespuestaBR.ERROR;
        }
        return codeResponse;
    }

}
