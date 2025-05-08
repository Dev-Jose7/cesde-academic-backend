package org.cesde.academic.service.impl;

import ch.qos.logback.core.model.ModelUtil;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Clase;
import org.cesde.academic.model.Grupo;
import org.cesde.academic.model.Modulo;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.*;
import org.cesde.academic.service.IClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaseServiceImpl implements IClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModuloRepository moduloRepository;

    @Override
    public Clase createClase(Clase clase) {
        Grupo grupo = grupoRepository.findById(clase.getGrupo().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Grupo no existente"));

        Usuario usuario = usuarioRepository.findById(clase.getDocente().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no existente"));

        Modulo modulo = moduloRepository.findById(clase.getModulo().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Modulo no existente"));

        clase.setGrupo(grupo);
        clase.setDocente(usuario);
        clase.setModulo(modulo);
        return claseRepository.save(clase);
    }

    @Override
    public List<Clase> getClases() {
        return claseRepository.findAll();
    }

    @Override
    public Optional<Clase> getClaseById(Integer id) {
        return claseRepository.findById(id);
    }

    @Override
    public List<Clase> getClasesByGrupoId(Integer grupoId) {
        return claseRepository.findByGrupo_Id(grupoId);
    }

    @Override
    public List<Clase> getClasesByDocenteId(Integer docenteId) {
        return claseRepository.findByDocente_Id(docenteId);
    }

    @Override
    public List<Clase> getClasesByModuloId(Integer moduloId) {
        return claseRepository.findByModulo_Id(moduloId);
    }

    @Override
    public Clase updateClase(Clase clase, Clase claseUpdated) {
        Grupo grupo = grupoRepository.findById(claseUpdated.getGrupo().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Grupo no existente"));

        Usuario usuario = usuarioRepository.findById(claseUpdated.getDocente().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no existente"));

        Modulo modulo = moduloRepository.findById(claseUpdated.getDocente().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Modulo no existente"));

        claseUpdated.setId(clase.getId());
        claseUpdated.setGrupo(grupo);
        claseUpdated.setDocente(usuario);
        claseUpdated.setModulo(modulo);
        return claseRepository.save(claseUpdated);
    }

    @Override
    public void deleteClase(Clase clase) {
        claseRepository.delete(clase);
    }
}
