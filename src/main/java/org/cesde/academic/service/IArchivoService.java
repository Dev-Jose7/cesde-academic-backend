package org.cesde.academic.service;

import org.cesde.academic.model.Archivo;

import java.util.List;
import java.util.Optional;

public interface IArchivoService {

    Archivo createArchivo(Archivo archivo);
    List<Archivo> getArchivos();
    Optional<Archivo> getArchivoById(Integer id);
    List<Archivo> getArchivosByClaseId(Integer claseId);
    List<Archivo> getArchivosByUsuarioId(Integer usuarioId);
    Archivo updateArchivo(Archivo archivo, Archivo archivoUpdated);
    void deleteArchivo(Archivo archivo);
}
