package org.cesde.academic.repository;

import org.cesde.academic.model.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {
    List<Anuncio> findAllByClaseId(Integer claseId);
    List<Anuncio> findAllByTituloContainingIgnoreCase(String titulo);
    List<Anuncio> findAllByFechaBetween(LocalDateTime desde, LocalDateTime hasta);
}
