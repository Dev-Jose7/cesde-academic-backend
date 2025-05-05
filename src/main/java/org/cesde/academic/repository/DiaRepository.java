package org.cesde.academic.repository;

import org.cesde.academic.model.Dia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Integer> {
}
