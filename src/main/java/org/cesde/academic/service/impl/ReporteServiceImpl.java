package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.ReporteRequestDTO;
import org.cesde.academic.dto.response.ClaseResponseInfoDTO;
import org.cesde.academic.dto.response.ReporteResponseDTO;
import org.cesde.academic.enums.EstadoReporte;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Actividad;
import org.cesde.academic.model.Clase;
import org.cesde.academic.model.Reporte;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.ClaseRepository;
import org.cesde.academic.repository.ReporteRepository;
import org.cesde.academic.repository.UsuarioRepository;
import org.cesde.academic.service.IReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReporteServiceImpl implements IReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ReporteResponseDTO createReporte(ReporteRequestDTO request) {
        Reporte reporte = createEntity(request);
        return createResponse(reporteRepository.save(reporte));
    }

    @Override
    public List<ReporteResponseDTO> getReportes() {
        return createResponseList(reporteRepository.findAll());
    }

    @Override
    public ReporteResponseDTO getReporteById(Integer id) {
        return createResponse(getReporteByIdOrException(id));
    }

    @Override
    public List<ReporteResponseDTO> getReportesByClaseId(Integer claseId) {
        return createResponseList(reporteRepository.findAllByClaseId(claseId));
    }

    @Override
    public List<ReporteResponseDTO> getReportesByUsuarioId(Integer usuarioId) {
        return createResponseList(reporteRepository.findAllByUsuarioId(usuarioId));
    }

    @Override
    public List<ReporteResponseDTO> getReportesByTitulo(String titulo) {
        return createResponseList(reporteRepository.findAllByTitulo(titulo));
    }

    @Override
    public List<ReporteResponseDTO> getReportesByFecha(LocalDate fecha) {
        return createResponseList(reporteRepository.findAllByFecha(fecha));
    }

    @Override
    public List<ReporteResponseDTO> getReportesByEstado(EstadoReporte estado) {
        return createResponseList(reporteRepository.findAllByEstado(estado));
    }

    @Override
    public ReporteResponseDTO updateReporte(Integer id, ReporteRequestDTO request) {
        Reporte original = getReporteByIdOrException(id);
        Reporte updated = createEntity(request);
        updated.setId(original.getId());
        updated.setCreado(original.getCreado());
        return createResponse(reporteRepository.save(updated));
    }

    @Override
    public void deleteReporte(Integer id) {
        Reporte reporte = getReporteByIdOrException(id);
        reporteRepository.delete(reporte);
    }

    // Métodos auxiliares

    private Reporte getReporteByIdOrException(Integer id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Reporte no encontrado"));
    }

    private Clase getClaseByIdOrException(Integer id) {
        return claseRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no encontrada"));
    }

    private Usuario getUsuarioByIdOrException(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));
    }

    private Reporte createEntity(ReporteRequestDTO request) {
        Clase clase = getClaseByIdOrException(request.getClaseId());
        Usuario usuario = getUsuarioByIdOrException(request.getUsuarioId());

        Reporte reporte = new Reporte();
        reporte.setClase(clase);
        reporte.setUsuario(usuario);
        reporte.setTitulo(request.getTitulo());
        reporte.setDescripcion(request.getDescripcion());
        reporte.setFecha(request.getFecha());
        reporte.setEstado(request.getEstado() == null ? EstadoReporte.PENDIENTE : request.getEstado());

        return reporte;
    }

    private ReporteResponseDTO createResponse(Reporte reporte) {
        return new ReporteResponseDTO(
                reporte.getId(),
                createClaseInfo(reporte),
                reporte.getUsuario().getNombre(),
                reporte.getTitulo(),
                reporte.getDescripcion(),
                reporte.getFecha(),
                reporte.getEstado(),
                reporte.getCreado(),
                reporte.getActualizado()
        );
    }

    private List<ReporteResponseDTO> createResponseList(List<Reporte> reportes) {
        List<ReporteResponseDTO> list = new ArrayList<>();
        for (Reporte reporte : reportes) {
            list.add(createResponse(reporte));
        }
        return list;
    }

    private ClaseResponseInfoDTO createClaseInfo(Reporte reporte){
        return new ClaseResponseInfoDTO(
                reporte.getClase().getGrupo().getCodigo(),
                reporte.getClase().getDocente().getNombre(),
                reporte.getClase().getModulo().getNombre()
        );
    }
}
