package org.cesde.academic.repository;

import org.cesde.academic.enums.NombreRole;
import org.cesde.academic.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByNombre(NombreRole nombre);
}
