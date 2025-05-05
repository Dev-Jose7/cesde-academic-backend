package org.cesde.academic.repository;

import org.cesde.academic.model.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Integer> {
    List<Modulo> findByPrograma_Id(Integer programaId);
}
