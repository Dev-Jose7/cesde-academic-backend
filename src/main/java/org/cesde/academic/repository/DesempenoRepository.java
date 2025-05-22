package org.cesde.academic.repository;

import org.cesde.academic.enums.EstadoDesempeno;
import org.cesde.academic.model.Desempeno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DesempenoRepository extends JpaRepository<Desempeno, Integer> {
    List<Desempeno> findAllByEstudianteId(Integer id);
    List<Desempeno> findAllByModuloId(Integer id);
    List<Desempeno> findAllByEstado(EstadoDesempeno estado);
}