package org.cesde.academic.service;

import org.cesde.academic.dto.request.ActividadRequestDTO;
import org.cesde.academic.dto.response.ActividadResponseDTO;
import org.cesde.academic.enums.TipoActividad;

import java.util.List;

public interface IActividadService {
    ActividadResponseDTO createActividad(ActividadRequestDTO request);
    List<ActividadResponseDTO> getActividades();
    ActividadResponseDTO getActividadById(Integer id);
    List<ActividadResponseDTO> getActividadesByClase(Integer claseId);
    List<ActividadResponseDTO> getActividadesByTitulo(String titulo);
    List<ActividadResponseDTO> getActividadesByTipo(TipoActividad tipo);
    ActividadResponseDTO updateActividad(Integer id, ActividadRequestDTO request);
    void deleteActividad(Integer id);
}
