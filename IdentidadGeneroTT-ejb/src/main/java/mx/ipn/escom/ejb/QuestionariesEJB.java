/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import mx.ipn.escom.br.QuestionariesBR;
import mx.ipn.escom.dao.GenericDAO;
import mx.ipn.escom.dao.QuestionaryDAO;
import mx.ipn.escom.dto.CategoriaDTO;
import mx.ipn.escom.dto.GeneroDTO;
import mx.ipn.escom.dto.OpcionRespuestaDTO;
import mx.ipn.escom.dto.QuestionDTO;
import mx.ipn.escom.dto.QuestionaryDTO;
import mx.ipn.escom.dto.RepresentanteInstitucionDTO;
import mx.ipn.escom.dto.SectionDTO;
import mx.ipn.escom.dto.TipoDTO;
import mx.ipn.escom.modelo.Categoria;
import mx.ipn.escom.modelo.Genero;
import mx.ipn.escom.modelo.OpcionRespuesta;
import mx.ipn.escom.modelo.Pregunta;
import mx.ipn.escom.modelo.Puntuacion;
import mx.ipn.escom.modelo.Seccion;
import mx.ipn.escom.modelo.Tipo;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.CodigoRespuestaBR;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@LocalBean
public class QuestionariesEJB extends GenericEJB {

    @EJB
    QuestionariesBR questionariesBR;
    @EJB
    RepresentanteInstitucionEJB representanteInstitucionEJB;
    @EJB
    GenericDAO genericDAO;
    @EJB
    QuestionaryDAO questionaryDAO;
    @EJB
    TipoEJB tipoEJB;
    @EJB
    PuntuacionEJB puntuacionEJB;
    @EJB
    GeneroEJB generoEJB;

    public Respuesta<QuestionaryDTO> addCategory(QuestionaryDTO questionaryDTO) {
        Respuesta<QuestionaryDTO> respuestaDTO = new Respuesta<>();
        if (questionariesBR.isValidFieldCategory(questionaryDTO) == CodigoRespuestaBR.OK) {
            if (questionaryDTO.getCategorias().isEmpty()) {
                Categoria categoria = new Categoria();
                categoria.setNombre(questionaryDTO.getNombreCategoria());
                questionaryDTO.getCategorias().add(new CategoriaDTO(categoria));
                return respuestaDTO;
            }
            if (questionariesBR.noRepeatedCategory(questionaryDTO)
                    == CodigoRespuestaBR.OK) {
                Categoria categoria = new Categoria();
                categoria.setNombre(questionaryDTO.getNombreCategoria());
                questionaryDTO.getCategorias().add(new CategoriaDTO(categoria));
                return respuestaDTO;
            }
        }
        respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
        return respuestaDTO;
    }

    public Respuesta<QuestionaryDTO> addSection(QuestionaryDTO questionaryDTO) {
        Respuesta<QuestionaryDTO> respuestaDTO = new Respuesta<>();
        if (questionariesBR.isValidFieldSection(questionaryDTO) == CodigoRespuestaBR.OK) {
            if (questionaryDTO.getSecciones().isEmpty()) {
                Seccion seccion = new Seccion();
                seccion.setNombre(questionaryDTO.getNombreSeccion());
                questionaryDTO.getSecciones().add(new SectionDTO(seccion));
                return respuestaDTO;
            }
            if (questionariesBR.noRepeatedSection(questionaryDTO)
                    == CodigoRespuesta.OK) {
                Seccion seccion = new Seccion();
                seccion.setNombre(questionaryDTO.getNombreSeccion());
                questionaryDTO.getSecciones().add(new SectionDTO(seccion));
                return respuestaDTO;
            }
        }
        respuestaDTO.setCodigo(CodigoRespuesta.ERROR);
        return respuestaDTO;
    }

    public Respuesta<QuestionaryDTO> addQuestion(QuestionaryDTO questionaryDTO,
            String pregunta, Integer noQuestions, String opcion) {
        Respuesta<QuestionaryDTO> respuestaDTO = new Respuesta<>();
        QuestionDTO question = new QuestionDTO();
        question.getEntidad().setPregunta(pregunta + " " + noQuestions);
        question.setId(noQuestions);
        List<OpcionRespuestaDTO> respuestas = new ArrayList<>();
        OpcionRespuesta option = createOption(question, opcion);
        respuestas.add(new OpcionRespuestaDTO(option));
        question.setOpcionRespuestaDTO(respuestas);
        questionaryDTO.getQuestionsDTO().add(question);
        return respuestaDTO;
    }

    public Respuesta<QuestionaryDTO> addOption(QuestionDTO question,
            String opcion) {
        Respuesta<QuestionaryDTO> respuestaDTO = new Respuesta<>();
        OpcionRespuesta option = createOption(question, opcion);
        question.getOpcionRespuestaDTO().add(new OpcionRespuestaDTO(option));
        return respuestaDTO;
    }

    public Respuesta<QuestionaryDTO> removeOption(OpcionRespuestaDTO opcionRespuestaDTO,
            QuestionDTO questionDTO) {
        Respuesta<QuestionaryDTO> respuestaDTO = new Respuesta<>();
        int i = 0;
        for (OpcionRespuestaDTO op : questionDTO.getOpcionRespuestaDTO()) {
            if (op.getEntidad().getOpcion().equals(opcionRespuestaDTO
                    .getEntidad().getOpcion())) {
                questionDTO.getOpcionRespuestaDTO().remove(i);
                break;
            }
            i++;
        }
        return respuestaDTO;
    }

    public Respuesta<QuestionaryDTO> removeQuestion(QuestionDTO questionDTO,
            QuestionaryDTO questionaryDTO) {
        Respuesta<QuestionaryDTO> respuestaDTO = new Respuesta<>();
        questionaryDTO.getQuestionsDTO().remove(questionDTO);
        return respuestaDTO;
    }

    public Respuesta<QuestionaryDTO> addQuestionary(QuestionaryDTO questionaryDTO) {
        Respuesta<QuestionaryDTO> respuestaDTO = validateAdd(questionaryDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            if (questionariesBR.noRepeatedQuestionary(questionaryDTO)
                    == CodigoRespuestaBR.OK) {
                //TODO: codigo Hardcodeado !!!!!!!!!!!!!!!!!
                Respuesta<RepresentanteInstitucionDTO> respuestaRepDTO
                        = representanteInstitucionEJB.findByID();
                questionaryDTO.getEntidad().setNombre(questionaryDTO
                        .getNombreCuestinario());
                questionaryDTO.getEntidad().setIdrepresentanteinst(
                        respuestaRepDTO.getResultado().getEntidad());

                List<Categoria> categorias = new ArrayList<>();
                List<Seccion> secciones = new ArrayList<>();
                Respuesta<TipoDTO> tiposDTO = tipoEJB.findAll();
                List<Tipo> tipos = new ArrayList<>();
                for (TipoDTO tipoDTO : tiposDTO.getResultados()) {
                    tipos.add(tipoDTO.getEntidad());
                }

                Respuesta<GeneroDTO> generos = generoEJB.findAll();
                Genero masculino = generos.getResultados().get(0).getEntidad();
                Genero femenino = generos.getResultados().get(1).getEntidad();

                for (QuestionDTO questionDTO : questionaryDTO
                        .getQuestionsDTO()) {

                    boolean addcategory = true;

                    if (!categorias.isEmpty()) {
                        for (Categoria categoria1 : categorias) {
                            if (categoria1.getNombre().equals(
                                    questionDTO.getCategoriaSelected())) {
                                addcategory = false;
                                break;
                            }
                        }
                    }

                    if (addcategory) {
                        Categoria categoria = new Categoria();
                        categoria.setNombre(questionDTO.getCategoriaSelected());
                        categoria.setIdcuestionario(questionaryDTO.getEntidad());
                        categorias.add(categoria);
                        categoria.setSeccionList(new ArrayList<Seccion>());
                    }

                    boolean addSection = true;

                    if (!secciones.isEmpty()) {
                        for (Seccion seccion1 : secciones) {
                            if (seccion1.getNombre().equals(
                                    questionDTO.getSeccionSelected())
                                    && seccion1.
                                            getIdcategoria().
                                            getNombre().equals(questionDTO.
                                                    getCategoriaSelected())) {
                                addSection = false;
                                break;
                            }
                        }
                    }

                    if (addSection) {
                        Seccion seccion = new Seccion();
                        seccion.setPreguntaList(new ArrayList<Pregunta>());
                        seccion.setNombre(questionDTO.getSeccionSelected());
                        for (Categoria categoria1 : categorias) {
                            if (categoria1.getNombre().equals(
                                    questionDTO.getCategoriaSelected())) {
                                seccion.setIdcategoria(categoria1);
                                categoria1.getSeccionList().add(seccion);
                            }
                        }
                        secciones.add(seccion);
                    }

                    for (Seccion seccion1 : secciones) {
                        if (seccion1.getNombre().
                                equals(questionDTO.getSeccionSelected())
                                && seccion1.getIdcategoria().
                                        getNombre().equals(
                                                questionDTO.getCategoriaSelected())) {
                            questionDTO.getEntidad().setIdseccion(seccion1);
                            seccion1.getPreguntaList().
                                    add(questionDTO.getEntidad());
                        }
                    }
                    for (Tipo tipo : tipos) {
                        if (tipo.getId().equals(questionDTO.getIdTipo())) {
                            questionDTO.getEntidad().setIdtipo(tipo);
                            break;
                        }
                    }

                    List<OpcionRespuesta> opcionesRespuestas = new ArrayList<>();

                    for (OpcionRespuestaDTO opcion
                            : questionDTO.getOpcionRespuestaDTO()) {
                        opcion.getEntidad().setIdpregunta(questionDTO.
                                getEntidad());
                        List<Puntuacion> puntuaciones = new ArrayList<>();
                        Puntuacion pM = new Puntuacion();
                        Puntuacion pF = new Puntuacion();
                        pM.setPuntuacion(opcion.getRatingM());
                        pF.setPuntuacion(opcion.getRatingF());
                        pM.setIdgenero(masculino);
                        pF.setIdgenero(femenino);
                        pM.setIdopcionrespuesta(opcion.getEntidad());
                        pF.setIdopcionrespuesta(opcion.getEntidad());
                        puntuaciones.add(pM);
                        puntuaciones.add(pF);
                        opcion.getEntidad().setPuntuacionList(puntuaciones);
                        opcionesRespuestas.add(opcion.getEntidad());
                    }
                    questionDTO.getEntidad().
                            setOpcionRespuestaList(opcionesRespuestas);
                }

                questionaryDTO.getEntidad().setCategoriaList(categorias);

                respuestaDTO = genericDAO.save(questionaryDTO.getEntidad());
                if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
                    System.out.println("si se pudo");
                } else {
                    System.out.println("no se pudo");
                    System.out.println(respuestaDTO.getMensaje());
                }

            } else {
                setRespuestaWrong(respuestaDTO,
                        "addQuestionary,repeatedNameQuestion");
            }
        }
        return respuestaDTO;
    }

    public Respuesta<QuestionaryDTO> validateAdd(QuestionaryDTO questionaryDTO) {
        Respuesta<QuestionaryDTO> respuestaDTO = new Respuesta<>();
        if (questionariesBR.isValidFieldNombre(questionaryDTO)
                == CodigoRespuestaBR.OK) {
            if (questionariesBR.isSelectedCategory(questionaryDTO)
                    == CodigoRespuestaBR.OK) {
                if (questionariesBR.isSelectedSection(questionaryDTO)
                        == CodigoRespuestaBR.OK) {
                    if (questionariesBR.notEmptyQuestions(questionaryDTO)
                            == CodigoRespuestaBR.OK) {
                        if (questionariesBR.isValidNameQuestion(questionaryDTO)
                                == CodigoRespuestaBR.OK) {
                            if (questionariesBR.notEmptyTypeQuestion(questionaryDTO)
                                    == CodigoRespuestaBR.OK) {
                                if (questionariesBR.notEmptyOptions(questionaryDTO)
                                        == CodigoRespuestaBR.OK) {
                                    if (questionariesBR.isValidOption(questionaryDTO)
                                            == CodigoRespuestaBR.OK) {
                                        if (questionariesBR.isValidPM(questionaryDTO)
                                                == CodigoRespuestaBR.OK) {
                                            if (questionariesBR.isValidPF(questionaryDTO)
                                                    == CodigoRespuestaBR.OK) {

                                            } else {
                                                setRespuestaWrong(respuestaDTO,
                                                        "addQuestionary.failRateF");
                                            }

                                        } else {
                                            setRespuestaWrong(respuestaDTO,
                                                    "addQuestionary.failRateM");
                                        }

                                    } else {
                                        setRespuestaWrong(respuestaDTO,
                                                "addQuestionary.failNameOption");
                                    }

                                } else {
                                    setRespuestaWrong(respuestaDTO,
                                            "addQuestionary.failEmptyOptions");
                                }

                            } else {
                                setRespuestaWrong(respuestaDTO,
                                        "addQuestionary.failEmptyType");
                            }
                        } else {
                            setRespuestaWrong(respuestaDTO,
                                    "addQuestionary.failNameQuestion");
                        }

                    } else {
                        setRespuestaWrong(respuestaDTO,
                                "addQuestionary.failemptyQuestions");
                    }
                } else {
                    setRespuestaWrong(respuestaDTO, "addQuestionary.failSelectedSect");
                }
            } else {
                setRespuestaWrong(respuestaDTO, "addQuestionary.failSelectedCat");
            }
        } else {
            setRespuestaWrong(respuestaDTO, "addQuestionary.failNombre");
        }
        return respuestaDTO;

    }

    public OpcionRespuesta createOption(QuestionDTO question, String opcion) {
        OpcionRespuesta option = new OpcionRespuesta();
        option.setOpcion(opcion + " " + question.getIdOpcion());
        question.incrementOpcion();
        return option;
    }

}
