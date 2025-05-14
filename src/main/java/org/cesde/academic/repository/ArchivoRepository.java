package org.cesde.academic.repository;

import org.cesde.academic.model.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Integer> {
    List<Archivo> findByUsuarioId(Integer id);
    List<Archivo> findAllByNombreArchivoContainingIgnoreCase(String nombreArchivo);
    List<Archivo> findAllByRutaArchivoContainingIgnoreCase(String rutaArchivo);
}
