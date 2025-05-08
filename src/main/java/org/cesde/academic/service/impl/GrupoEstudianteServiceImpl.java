package org.cesde.academic.service.impl;

import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.exception.TipoIncorrectoException;
import org.cesde.academic.model.Grupo;
import org.cesde.academic.model.GrupoEstudiante;
import org.cesde.academic.model.GrupoEstudianteId;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.GrupoEstudianteRepository;
import org.cesde.academic.repository.GrupoRepository;
import org.cesde.academic.repository.UsuarioRepository;
import org.cesde.academic.service.IGrupoEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoEstudianteServiceImpl implements IGrupoEstudianteService {

    @Autowired
    private GrupoEstudianteRepository grupoEstudianteRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public GrupoEstudiante createGrupoEstudiante(GrupoEstudiante grupoEstudiante) {
        Grupo grupo = grupoRepository.findById(grupoEstudiante.getGrupo().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Grupo no existente"));

        Usuario usuario = usuarioRepository.findById(grupoEstudiante.getEstudiante().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no existente"));

        //Confirmas que tanto Grupo como Usuario existen antes de guardar la relación.
        //Previene errores de integridad referencial.

        if(!usuario.getTipo().equals(Usuario.Tipo.ESTUDIANTE)){
            throw new TipoIncorrectoException("El usuario debe ser un estudiante");
        }

        GrupoEstudianteId id = new GrupoEstudianteId(grupo.getId(), usuario.getId());
        if (grupoEstudianteRepository.existsById(id)) {
            throw new IllegalStateException("Esta relación ya existe");
        }

        grupoEstudiante.setGrupo(grupo);
        grupoEstudiante.setEstudiante(usuario);
        return grupoEstudianteRepository.save(grupoEstudiante);
    }

    @Override
    public List<GrupoEstudiante> getGrupoEstudiantes() {
        return grupoEstudianteRepository.findAll();
    }

    @Override
    public Optional<GrupoEstudiante> getGrupoEstudianteById(GrupoEstudianteId grupoEstudianteId) {
        return grupoEstudianteRepository.findById(grupoEstudianteId); // Directamente usamos el ID compuesto
    }

    @Override
    public List<GrupoEstudiante> getGrupoEstudiantesByGrupoId(Integer grupoId) {
        return grupoEstudianteRepository.findByGrupo_Id(grupoId);
    }

    @Override
    public List<GrupoEstudiante> getGrupoEstudiantesByEstudianteId(Integer estudianteId) {
        return grupoEstudianteRepository.findByEstudiante_Id(estudianteId);
    }

    @Override
    public GrupoEstudiante updateGrupoEstudiante(GrupoEstudiante oldGrupoEstudiante, GrupoEstudiante grupoEstudianteUpdated) {
        // Verificar existencia del nuevo grupo y estudiante
        Grupo grupo = grupoRepository.findById(grupoEstudianteUpdated.getGrupo().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Grupo no existente"));

        Usuario usuario = usuarioRepository.findById(grupoEstudianteUpdated.getEstudiante().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no existente"));

        if (!usuario.getTipo().equals(Usuario.Tipo.ESTUDIANTE)) {
            throw new TipoIncorrectoException("El usuario debe ser un estudiante");
        }

        grupoEstudianteRepository.delete(oldGrupoEstudiante);

        grupoEstudianteUpdated.setGrupo(grupo);
        grupoEstudianteUpdated.setEstudiante(usuario);
        return grupoEstudianteRepository.save(grupoEstudianteUpdated);
    }


    @Override
    public void deleteGrupoEstudiante(GrupoEstudiante grupoEstudiante) {
        grupoEstudianteRepository.delete(grupoEstudiante);
    }
}

