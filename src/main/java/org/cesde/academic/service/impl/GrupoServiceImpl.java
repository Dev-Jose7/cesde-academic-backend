package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.GrupoRequestDTO;
import org.cesde.academic.dto.response.GrupoResponseDTO;
import org.cesde.academic.exception.RecursoExistenteException;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Grupo;
import org.cesde.academic.model.Programa;
import org.cesde.academic.model.Semestre;
import org.cesde.academic.repository.GrupoRepository;
import org.cesde.academic.repository.ProgramaRepository;
import org.cesde.academic.repository.SemestreRepository;
import org.cesde.academic.service.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GrupoServiceImpl implements IGrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private SemestreRepository semestreRepository;

    @Override
    public GrupoResponseDTO createGrupo(GrupoRequestDTO request) {
        Grupo grupo = createEntity(request);
        String codigoGenerado = generateCodigo(grupo);
        validateCodigoUnique(codigoGenerado, null);
        grupo.setCodigo(codigoGenerado);
        return createResponse(grupoRepository.save(grupo));
    }

    @Override
    public List<GrupoResponseDTO> getGrupos() {
        return createResponseList(grupoRepository.findAll());
    }

    @Override
    public GrupoResponseDTO getGrupoById(Integer id) {
        return createResponse(getGrupoByIdOrException(id));
    }

    @Override
    public List<GrupoResponseDTO> getGruposByCodigo(String codigo) {
        return createResponseList(grupoRepository.findByCodigoContainingIgnoreCase(codigo));
    }

    @Override
    public List<GrupoResponseDTO> getGruposByProgramaId(Integer programaId) {
        return createResponseList(grupoRepository.findAllByProgramaId(programaId));
    }

    @Override
    public List<GrupoResponseDTO> getGruposBySemestreId(Integer semestreId) {
        return createResponseList(grupoRepository.findAllBySemestreId(semestreId));
    }

    @Override
    public GrupoResponseDTO updateGrupo(Integer id, GrupoRequestDTO request) {
        Grupo grupoOld = getGrupoByIdOrException(id);
        Grupo updatedGrupo = createEntity(request);
        String codigoGenerado = generateCodigo(updatedGrupo);
        validateCodigoUnique(codigoGenerado, id);

        updatedGrupo.setId(grupoOld.getId());
        updatedGrupo.setCodigo(grupoOld.getCodigo());
        updatedGrupo.setCreado(grupoOld.getCreado());
        updatedGrupo.setCodigo(generateCodigo(updatedGrupo));

        return createResponse(grupoRepository.save(updatedGrupo));
    }

    @Override
    public void deleteGrupo(Integer id) {
        Grupo grupo = getGrupoByIdOrException(id);
        grupoRepository.delete(grupo);
    }

    // Métodos auxiliares

    private Grupo getGrupoByIdOrException(Integer id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Grupo no encontrado"));
    }

    private Programa getProgramaOrException(Integer id) {
        return programaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Programa no encontrado"));
    }

    private Semestre getSemestreOrException(Integer id) {
        return semestreRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Semestre no encontrado"));
    }

    private void validateCodigoUnique(String codigo, Integer id){
        boolean existe = id == null
                ? grupoRepository.existsByCodigoIgnoreCase(codigo)
                : grupoRepository.existsByCodigoIgnoreCaseAndIdNot(codigo, id);

        if (existe){
            throw new RecursoExistenteException("El grupo ya está registrado con el código: ");
        }
    }

    private Grupo createEntity(GrupoRequestDTO request) {
        Grupo grupo = new Grupo();
        grupo.setPrograma(getProgramaOrException(request.getProgramaId()));
        grupo.setSemestre(getSemestreOrException(request.getSemestreId()));
        grupo.setEstado(request.getEstado());
        return grupo;
    }

    private GrupoResponseDTO createResponse(Grupo grupo) {
        return new GrupoResponseDTO(
                grupo.getId(),
                grupo.getCodigo(),
                grupo.getPrograma().getId(),
                grupo.getSemestre().getId(),
                grupo.getEstado(),
                grupo.getCreado(),
                grupo.getActualizado()
        );
    }

    private List<GrupoResponseDTO> createResponseList(List<Grupo> grupos) {
        List<GrupoResponseDTO> responseList = new ArrayList<>();
        for (Grupo grupo : grupos) {
            responseList.add(createResponse(grupo));
        }
        return responseList;
    }

    private String generateCodigo(Grupo grupo) {
        String programaId = "P" + grupo.getPrograma().getId();
        String semestreId = "S" + grupo.getSemestre().getNombre();
        int grupoCount = grupoRepository.findAllByProgramaIdAndSemestreId(grupo.getPrograma().getId(), grupo.getSemestre().getId()).size() + 1;
        return programaId + "-" + semestreId + "-" + grupoCount;
    }
}
