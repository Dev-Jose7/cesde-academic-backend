package org.cesde.academic.repository;

import org.cesde.academic.model.JwtBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JwtBlacklistRepository extends JpaRepository<JwtBlacklist, Integer> {

    // Verifica si un access token está en la blacklist
    boolean existsByAccessToken(String accessToken);

    // Verifica si un refresh token está en la blacklist
    boolean existsByRefreshToken(String refreshToken);

    // Consulta los registros por nombre de usuario
    List<JwtBlacklist> findByUsuarioCedula(String cedula);

    // Elimina access tokens expirados
    @Modifying
    @Transactional
    @Query("DELETE FROM JwtBlacklist jb WHERE jb.accessExpiracion <= CURRENT_TIMESTAMP")
    void deleteExpiredAccessTokens();

    // Elimina refresh tokens expirados
    @Modifying
    @Transactional
    @Query("DELETE FROM JwtBlacklist jb WHERE jb.refreshExpiracion <= CURRENT_TIMESTAMP")
    void deleteExpiredRefreshTokens();
}
