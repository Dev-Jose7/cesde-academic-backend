package org.cesde.academic.service.impl;

import org.cesde.academic.model.JwtBlacklist;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.JwtBlacklistRepository;
import org.cesde.academic.service.IJwtBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class JwtBlacklistServiceImpl implements IJwtBlacklistService {

    @Autowired
    private JwtBlacklistRepository jwtBlacklistRepository;

    @Override
    public void blacklistTokens(String accessToken, Date accessExp, String refreshToken, Date refreshExp, Usuario usuario) {
        JwtBlacklist jwtBlacklist = new JwtBlacklist();
        jwtBlacklist.setAccessToken(accessToken);
        jwtBlacklist.setAccessExpiracion(accessExp);
        jwtBlacklist.setRefreshToken(refreshToken);
        jwtBlacklist.setRefreshExpiracion(refreshExp);
        jwtBlacklist.setUsuario(usuario);
        jwtBlacklistRepository.save(jwtBlacklist);
    }

    @Override
    public boolean isAccessTokenBlacklisted(String accessToken) {
        return jwtBlacklistRepository.existsByAccessToken(accessToken);
    }

    @Override
    public boolean isRefreshTokenBlacklisted(String refreshToken) {
        return jwtBlacklistRepository.existsByRefreshToken(refreshToken);
    }

    @Override
    @Transactional
    public void deleteExpiredTokens() {
        jwtBlacklistRepository.deleteExpiredAccessTokens();
        jwtBlacklistRepository.deleteExpiredRefreshTokens();
    }
}
