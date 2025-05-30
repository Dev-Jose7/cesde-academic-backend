package org.cesde.academic.repository;

import org.springframework.transaction.annotation.Transactional;
import org.cesde.academic.model.JwtBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtBlacklistRepository extends JpaRepository<JwtBlacklist, Integer> {
    boolean existsByToken(String token);

    @Modifying // Le dice a Spring que esta no es una consulta de selección (SELECT), sino de modificación (DELETE, UPDATE, etc.). Es necesario incluirla cuando se usa la anotación QUERY al modificar
    @Transactional // Asegura que la operación de borrado ocurra dentro de una transacción. Si algo sale mal, la transacción se revierte (rollback).
    @Query("DELETE FROM JwtBlacklist jb WHERE jb.expiracion <= CURRENT_TIMESTAMP") // Especifica una consulta JPQL (Java Persistence Query Language) personalizada.
    void deleteExpiredTokens();
}
