/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.br;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import mx.ipn.escom.dto.InstitutionDTO;
import mx.ipn.escom.util.CodigoRespuestaBR;

/**
 *
 * @author andii-burciaga
 */
@Stateless
@LocalBean
public class InstutionsBR {

    public int isValidBR001(InstitutionDTO institutionDTO) {
        return (institutionDTO.getEntidad().getNombre() != null
                && institutionDTO.getEntidad().getAcronimo() != null
                && institutionDTO.getEntidad().getDirector() != null
                && institutionDTO.getEntidad().getCorreoelectronico() != null
                && institutionDTO.getEntidad().getTelefono() != null
                && institutionDTO.getEntidad().getCalle() != null
                && institutionDTO.getEntidad().getColonia() != null
                && institutionDTO.getEntidad().getDelegacion() != null
                && institutionDTO.getEntidad().getCp() != null
                && institutionDTO.getEntidad().getEstado() != null)
                ? CodigoRespuestaBR.OK : CodigoRespuestaBR.ERROR;
    }

}
