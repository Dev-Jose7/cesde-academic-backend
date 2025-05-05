package org.cesde.academic.repository;

import org.cesde.academic.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    List<Grupo> findByPrograma_Id(Integer programaId);
    List<Grupo> findBySemestre_Id(Integer semestreId);
}
