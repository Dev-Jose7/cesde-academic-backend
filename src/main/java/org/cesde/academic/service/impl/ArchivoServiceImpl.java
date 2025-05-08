package org.cesde.academic.service.impl;

import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Archivo;
import org.cesde.academic.model.Clase;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.ArchivoRepository;
import org.cesde.academic.repository.ClaseRepository;
import org.cesde.academic.repository.UsuarioRepository;
import org.cesde.academic.service.IArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArchivoServiceImpl implements IArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Archivo createArchivo(Archivo archivo) {
        Clase clase = claseRepository.findById(archivo.getClase().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no existente"));

        Usuario usuario = usuarioRepository.findById(archivo.getUsuario().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no existente"));

        archivo.setClase(clase);
        archivo.setUsuario(usuario);
        return archivoRepository.save(archivo);
    }

    @Override
    public List<Archivo> getArchivos() {
        return archivoRepository.findAll();
    }

    @Override
    public Optional<Archivo> getArchivoById(Integer id) {
        return archivoRepository.findById(id);
    }

    @Override
    public List<Archivo> getArchivosByClaseId(Integer claseId) {
        return archivoRepository.findByClase_Id(claseId);
    }

    @Override
    public List<Archivo> getArchivosByUsuarioId(Integer usuarioId) {
        return archivoRepository.findByUsuario_Id(usuarioId);
    }

    @Override
    public Archivo updateArchivo(Archivo archivo, Archivo archivoUpdated) {
        Clase clase = claseRepository.findById(archivoUpdated.getClase().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no existente"));

        Usuario usuario = usuarioRepository.findById(archivoUpdated.getUsuario().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no existente"));

        archivoUpdated.setId(archivo.getId()); // Asignamos el id del archivo original al actualizado
        archivoUpdated.setClase(clase);
        archivoUpdated.setUsuario(usuario);
        return archivoRepository.save(archivoUpdated); // Este save realiza un update en lugar de un insert
    }

    @Override
    public void deleteArchivo(Archivo archivo) {
        archivoRepository.delete(archivo); // Eliminamos el archivo
    }
}
