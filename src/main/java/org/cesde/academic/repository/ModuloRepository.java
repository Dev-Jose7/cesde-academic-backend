package org.cesde.academic.repository;

import org.cesde.academic.model.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Integer> {
    boolean existsByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Integer id);
    List<Modulo> findAllByNombreContainingIgnoreCase(String nombre);
    List<Modulo> findByPrograma_Id(Integer programaId);
}
