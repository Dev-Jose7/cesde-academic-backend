package org.cesde.academic.repository;

import org.cesde.academic.enums.TipoUsuario;
import org.cesde.academic.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Para validación de unicidad
    boolean existsByCorreoIgnoreCase(String correo);
    boolean existsByCorreoIgnoreCaseAndIdNot(String correo, Integer id);

    boolean existsByCedula(String cedula);
    boolean existsByCedulaAndIdNot(String cedula, Integer id);

    // Para obtener registros por columna que contenga un valor igual o similar al proporcionado
    List<Usuario> findAllByNombreContainingIgnoreCase(String nombre);
    List<Usuario> findAllByCedulaContainingIgnoreCase(String cedula);
    List<Usuario> findAllByCorreoContainingIgnoreCase(String correo);
    List<Usuario> findAllByTipo(TipoUsuario tipo);
}
