package org.cesde.academic.repository;

import org.cesde.academic.model.Escuela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EscuelaRepository extends JpaRepository<Escuela, Integer> {
    boolean existsByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Integer id);
    List<Escuela> findAllByNombreContainingIgnoreCase(String nombre);

    // existsByNombreIgnoreCase
    // Busca una escuela bajo un nombre específico ignorando mayúsculas y minúsculas

    // existsByNombreIgnoreCaseAndIdNot
    // Busca una escuela bajo un nombre específico ignorando mayúsculas y minúsculas, y verifica que el ID de la
    // escuela encontrada no sea el mismo que el ID proporcionado (lo que significa que no afectará a la escuela
    // si está editando). Es útil cuando estás actualizando una escuela y quieres asegurarte de que no haya otra
    // escuela con el mismo nombre, pero permitiendo que la escuela que estás editando siga teniendo su propio
    // nombre. El ID es necesario aquí para excluir el propio registro en actualizaciones

    // findAllByNombreContainingIgnoreCase
    // Realiza una consulta sobre la columna "nombre" de la entidad, devolviendo todos los registros
    // que contengan parcialmente el valor del parámetro, sin distinguir entre mayúsculas y minúsculas.
}

