package org.cesde.academic.repository;

import org.cesde.academic.enums.TipoModulo;
import org.cesde.academic.model.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Integer> {
    boolean existsByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Integer id);
    boolean existsByNombreIgnoreCaseAndTipoNot(String nombre, TipoModulo tipo);
    boolean existsByNombreIgnoreCaseAndTipoNotAndIdNot(String nombre, TipoModulo tipo, Integer id);
    List<Modulo> findAllByNombreContainingIgnoreCase(String nombre);
}

// Válida si hay un registro que, en la columna nombre tenga como valor el dato proporcionado al
// métodó, ignorándo mayúsculas y minúsculas que existan en el valor (existsByNombreIgnoreCase)
// siempre y cuando el valor de la columna tipo sea diferente al dato proporcionado al métodó (existsByNombreIgnoreCaseAndTipoNot)
// siempre y cuando el valor de la columna id sea diferente al dato proporcionado al métodó (existsByNombreIgnoreCaseAndIdNot - existsByNombreIgnoreCaseAndTipoNotAndIdNot)




