package org.cesde.academic.service;

import org.cesde.academic.enums.TipoToken;
import org.cesde.academic.model.Usuario;

import java.util.Date;

public interface IJwtBlacklistService {

    void blacklistToken(String token, TipoToken tipo, Date expiration, Usuario usuario);

    boolean isTokenBlacklisted(String token, TipoToken tipo);

    void deleteExpiredTokens();
}
