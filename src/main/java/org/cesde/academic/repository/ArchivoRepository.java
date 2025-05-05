package org.cesde.academic.repository;

import org.cesde.academic.model.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Integer> {
    List<Archivo> findByClase_Id(Integer claseId);
    List<Archivo> findByUsuario_Id(Integer usuarioId);
}
