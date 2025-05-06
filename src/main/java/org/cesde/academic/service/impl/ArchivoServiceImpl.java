package org.cesde.academic.service.impl;

import org.cesde.academic.model.Archivo;
import org.cesde.academic.repository.ArchivoRepository;
import org.cesde.academic.service.IArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArchivoServiceImpl implements IArchivoService {

    @Autowired
    private ArchivoRepository archivoRepository;

    @Override
    public Archivo createArchivo(Archivo archivo) {
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
        archivoUpdated.setId(archivo.getId()); // Asignamos el id del archivo original al actualizado
        return archivoRepository.save(archivoUpdated); // Este save realiza un update en lugar de un insert
    }

    @Override
    public void deleteArchivo(Archivo archivo) {
        archivoRepository.delete(archivo); // Eliminamos el archivo
    }
}
