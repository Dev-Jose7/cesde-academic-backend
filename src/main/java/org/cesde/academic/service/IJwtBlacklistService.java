package org.cesde.academic.service;

import java.util.Date;

public interface IJwtBlacklistService {
    void createBlacklistToken(String token, Date expiracion);
    boolean isTokenBlacklisted(String token);
    void deleteTokenBlacklist();
}
