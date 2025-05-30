package org.cesde.academic.service;

import java.time.LocalDateTime;

public interface IJwtBlacklistService {
    void createBlacklistToken(String token, LocalDateTime expiracion);
    boolean isTokenBlacklisted(String token);
    void deleteTokenBlacklist();
}
