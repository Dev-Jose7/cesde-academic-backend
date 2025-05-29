package org.cesde.academic.repository;

import org.cesde.academic.enums.NivelPensum;
import org.cesde.academic.model.Pensum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PensumRepository extends JpaRepository<Pensum, Integer> {
    List<Pensum> findAllByProgramaId(Integer id);
    List<Pensum> findAllByModuloId(Integer id);
    List<Pensum> findAllByNivel(NivelPensum nivel);

    Optional<Pensum> findByProgramaId(Integer id);
    Optional<Pensum> findByModuloId(Integer id);
    Optional<Pensum> findByNivel(NivelPensum id);

    // Para validar unicidad
    boolean existsByProgramaIdAndModuloIdAndNivel(Integer programaId, Integer moduloId, NivelPensum nivel);
    boolean existsByProgramaIdAndModuloIdAndNivelAndIdNot(Integer programaId, Integer moduloId, NivelPensum nivel, Integer id);
}
