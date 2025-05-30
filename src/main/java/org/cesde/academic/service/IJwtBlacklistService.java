package org.cesde.academic.service;

import org.cesde.academic.model.Usuario;

import java.util.Date;

public interface IJwtBlacklistService {

    void blacklistTokens(String accessToken, Date accessExp, String refreshToken, Date refreshExp, Usuario usuario);

    boolean isAccessTokenBlacklisted(String accessToken);

    boolean isRefreshTokenBlacklisted(String refreshToken);

    void deleteExpiredTokens(); // Elimina access y refresh expirados
}
