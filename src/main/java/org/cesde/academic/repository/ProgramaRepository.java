package org.cesde.academic.repository;

import org.cesde.academic.model.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Integer> {
    List<Programa> findByEscuela_Id(Integer escuelaId);
}