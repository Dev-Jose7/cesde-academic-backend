package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.ClaseHorarioRequestDTO;
import org.cesde.academic.dto.response.ClaseHorarioResponseDTO;
import org.cesde.academic.exception.RecursoExistenteException;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.ClaseHorario;
import org.cesde.academic.model.ClaseHorarioId;
import org.cesde.academic.model.Clase;
import org.cesde.academic.model.Horario;
import org.cesde.academic.repository.ClaseHorarioRepository;
import org.cesde.academic.repository.ClaseRepository;
import org.cesde.academic.repository.HorarioRepository;
import org.cesde.academic.service.IClaseHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClaseHorarioServiceImpl implements IClaseHorarioService {

    @Autowired
    private ClaseHorarioRepository claseHorarioRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Override
    public ClaseHorarioResponseDTO createClaseHorario(ClaseHorarioRequestDTO request) {
        ClaseHorario claseHorario = createEntity(request);
        return createResponse(claseHorarioRepository.save(claseHorario));
    }

    @Override
    public List<ClaseHorarioResponseDTO> getClaseHorarios() {
        List<ClaseHorario> claseHorarios = claseHorarioRepository.findAll();
        return createResponseList(claseHorarios);
    }

    @Override
    public ClaseHorarioResponseDTO getClaseHorarioById(ClaseHorarioId id) {
        ClaseHorario claseHorario = getClaseHorarioByIdOrException(id);
        return createResponse(claseHorario);
    }

    @Override
    public List<ClaseHorarioResponseDTO> getClaseHorariosByClaseId(Integer id) {
        List<ClaseHorario> claseHorarios = claseHorarioRepository.findByClase_Id(id);
        return createResponseList(claseHorarios);
    }

    @Override
    public List<ClaseHorarioResponseDTO> getClaseHorariosByHorarioId(Integer id) {
        List<ClaseHorario> claseHorarios = claseHorarioRepository.findByHorario_Id(id);
        return createResponseList(claseHorarios);
    }

    @Override
    public ClaseHorarioResponseDTO updateClaseHorario(ClaseHorarioId id, ClaseHorarioRequestDTO request) {
        // Verifica que no exista la relación en la base de datos
        ClaseHorario updateClaseHorario = createEntity(request);
        ClaseHorario oldClaseHorario = getClaseHorarioByIdOrException(id);

        claseHorarioRepository.delete(oldClaseHorario);
        return createResponse(claseHorarioRepository.save(updateClaseHorario));
    }

    @Override
    public void deleteClaseHorario(ClaseHorarioId id) {
        ClaseHorario claseHorario = getClaseHorarioByIdOrException(id);
        claseHorarioRepository.delete(claseHorario);
    }

    // Métodos auxiliares
    private ClaseHorario getClaseHorarioByIdOrException(ClaseHorarioId id) {
        return claseHorarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("ClaseHorario no encontrado"));
    }

    private Clase getClaseByIdOrException(Integer id) {
        return claseRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no encontrada"));
    }

    private Horario getHorarioByIdOrException(Integer id) {
        return horarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Horario no encontrado"));
    }

    private void validateClaseHorario(Integer claseId, Integer horarioId) {
        if (claseHorarioRepository.existsByClaseIdAndHorarioId(claseId, horarioId)) {
            throw new RecursoExistenteException("La clase ya tiene asignado este horario.");
        }
    }

    private ClaseHorario createEntity(ClaseHorarioRequestDTO request) {
        validateClaseHorario(request.getClaseId(), request.getHorarioId());

        ClaseHorario claseHorario = new ClaseHorario();
        claseHorario.setClase(getClaseByIdOrException(request.getClaseId()));
        claseHorario.setHorario(getHorarioByIdOrException(request.getHorarioId()));
        return claseHorario;
    }

    private ClaseHorarioResponseDTO createResponse(ClaseHorario claseHorario) {
        return new ClaseHorarioResponseDTO(
                claseHorario.getClase().getModulo().getNombre(),
                claseHorario.getHorario().getDia().getNombre(),
                claseHorario.getHorario().getFranjaHoraria().getHoraInicio(),
                claseHorario.getHorario().getFranjaHoraria().getHoraFin()
        );
    }

    private List<ClaseHorarioResponseDTO> createResponseList(List<ClaseHorario> claseHorarios) {
        List<ClaseHorarioResponseDTO> lista = new ArrayList<>();
        for (ClaseHorario claseHorario : claseHorarios) {
            lista.add(createResponse(claseHorario));
        }
        return lista;
    }
}
