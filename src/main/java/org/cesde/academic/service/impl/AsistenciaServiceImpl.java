package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.AsistenciaRequestDTO;
import org.cesde.academic.dto.response.AsistenciaResponseDTO;
import org.cesde.academic.dto.response.ClaseResponseInfoDTO;
import org.cesde.academic.enums.EstadoAsistencia;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Actividad;
import org.cesde.academic.model.Asistencia;
import org.cesde.academic.model.Clase;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.AsistenciaRepository;
import org.cesde.academic.repository.ClaseRepository;
import org.cesde.academic.repository.UsuarioRepository;
import org.cesde.academic.service.IAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class AsistenciaServiceImpl implements IAsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public AsistenciaResponseDTO createAsistencia(AsistenciaRequestDTO request) {
        Asistencia asistencia = createEntity(request);
        return createResponse(asistenciaRepository.save(asistencia));
    }

    @Override
    public List<AsistenciaResponseDTO> getAsistencias() {
        return createResponseList(asistenciaRepository.findAll());
    }

    @Override
    public AsistenciaResponseDTO getAsistenciaById(Integer id) {
        return createResponse(getAsistenciaByIdOrException(id));
    }

    @Override
    public List<AsistenciaResponseDTO> getAsistenciasByClaseId(Integer claseId) {
        return createResponseList(asistenciaRepository.findAllByClaseId(claseId));
    }

    @Override
    public List<AsistenciaResponseDTO> getAsistenciasByEstudianteId(Integer estudianteId) {
        return createResponseList(asistenciaRepository.findAllByEstudianteId(estudianteId));
    }

    @Override
    public List<AsistenciaResponseDTO> getAsistenciasByFechaRange(LocalDate desde, LocalDate hasta) {
        return createResponseList(asistenciaRepository.findAllByFechaBetween(desde, hasta));
    }

    @Override
    public List<AsistenciaResponseDTO> getAsistenciasByEstado(EstadoAsistencia estado) {
        return createResponseList(asistenciaRepository.findAllByEstado(estado));
    }

    @Override
    public AsistenciaResponseDTO updateAsistencia(Integer id, AsistenciaRequestDTO request) {
        Asistencia original = getAsistenciaByIdOrException(id);
        Asistencia updated = createEntity(request);
        updated.setId(original.getId());
        updated.setCreado(original.getCreado());
        return createResponse(asistenciaRepository.save(updated));
    }

    @Override
    public void deleteAsistencia(Integer id) {
        Asistencia asistencia = getAsistenciaByIdOrException(id);
        asistenciaRepository.delete(asistencia);
    }

    // Auxiliares

    private Asistencia getAsistenciaByIdOrException(Integer id) {
        return asistenciaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Asistencia no encontrada"));
    }

    private Clase getClaseByIdOrException(Integer id){
         return claseRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no encontrada"));
    }

    private Usuario getEstudianteByIdOrException(Integer id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Estudiante no encontrado"));
    }

    private Asistencia createEntity(AsistenciaRequestDTO request) {
        Clase clase = getClaseByIdOrException(request.getClaseId());
        Usuario estudiante = getEstudianteByIdOrException(request.getEstudianteId());

        Asistencia asistencia = new Asistencia();
        asistencia.setClase(clase);
        asistencia.setEstudiante(estudiante);
        asistencia.setFecha(request.getFecha());
        asistencia.setEstado(request.getEstado());
        return asistencia;
    }

    private AsistenciaResponseDTO createResponse(Asistencia asistencia) {
        return new AsistenciaResponseDTO(
                asistencia.getId(),
                createClaseInfo(asistencia),
                asistencia.getEstudiante().getNombre(),
                asistencia.getFecha(),
                asistencia.getEstado(),
                asistencia.getCreado(),
                asistencia.getActualizado()
        );
    }

    private List<AsistenciaResponseDTO> createResponseList(List<Asistencia> asistencias) {
        List<AsistenciaResponseDTO> list = new ArrayList<>();
        for (Asistencia asistencia : asistencias) {
            list.add(createResponse(asistencia));
        }
        return list;
    }

    private ClaseResponseInfoDTO createClaseInfo(Asistencia asistencia){
        return new ClaseResponseInfoDTO(
                asistencia.getClase().getGrupo().getCodigo(),
                asistencia.getClase().getDocente().getNombre(),
                asistencia.getClase().getModulo().getNombre()
        );
    }
}
