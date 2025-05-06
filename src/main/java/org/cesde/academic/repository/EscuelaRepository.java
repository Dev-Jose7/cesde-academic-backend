package org.cesde.academic.repository;

import org.cesde.academic.model.Escuela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EscuelaRepository extends JpaRepository<Escuela, Integer> {
    Optional<Escuela> findByNombre(String nombre);
}
