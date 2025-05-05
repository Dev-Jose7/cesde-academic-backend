package org.cesde.academic.repository;

import org.cesde.academic.model.FranjaHoraria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranjaHorariaRepository extends JpaRepository<FranjaHoraria, Integer> {
}
