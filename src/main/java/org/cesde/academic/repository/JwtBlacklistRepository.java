package org.cesde.academic.repository;

import org.cesde.academic.enums.TipoToken;
import org.cesde.academic.model.JwtBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JwtBlacklistRepository extends JpaRepository<JwtBlacklist, Integer> {

    // Verifica si un token (access o refresh) está en la blacklist según su tipo
    boolean existsByTokenAndTipo(String token, TipoToken tipo);

    // Consulta los registros por nombre de usuario
    List<JwtBlacklist> findByUsuarioCedula(String cedula);

    // Elimina access tokens expirados
    @Modifying
    @Transactional
    @Query("DELETE FROM JwtBlacklist jb WHERE jb.expiracion <= CURRENT_TIMESTAMP")
    void deleteExpiredTokens();
}
