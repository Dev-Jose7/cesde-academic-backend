package org.cesde.academic.repository;

import org.cesde.academic.model.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Integer> {
    boolean existsByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Integer id);
    List<Programa> findAllByNombreContainingIgnoreCase(String nombre);
    List<Programa> findByEscuela_Id(Integer escuelaId);

    // findByEscuela_id
    // Va a consultar la propiedad escuela de la entidad Programa, y al ser una instancia de la clase Escuela,
    // accede al id para filtrar los programas mediante el ID de la escuela asociada.
}