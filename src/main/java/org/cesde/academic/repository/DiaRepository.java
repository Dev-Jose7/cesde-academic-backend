package org.cesde.academic.repository;

import org.cesde.academic.enums.NombreDia;
import org.cesde.academic.model.Dia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Integer> {
    Optional<Dia> findByNombre(NombreDia nombre);
    boolean existsByNombre(NombreDia nombre);
}
