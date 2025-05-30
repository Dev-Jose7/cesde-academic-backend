package org.cesde.academic.service.impl;

import org.cesde.academic.model.JwtBlacklist;
import org.cesde.academic.repository.JwtBlackListRepository;
import org.cesde.academic.service.IJwtBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JwtBlacklistServiceImpl implements IJwtBlacklistService {

    @Autowired
    private JwtBlackListRepository jwtBlackListRepository;

    @Override
    public void createBlacklistToken(String token, LocalDateTime expiracion) {
        jwtBlackListRepository.save(createEntity(token, expiracion));
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        return jwtBlackListRepository.existsByToken(token);
    }

    @Override
    public void deleteTokenBlacklist() {
        jwtBlackListRepository.deleteExpiredTokens();
    }

    private JwtBlacklist createEntity(String token, LocalDateTime expiracion){
        JwtBlacklist jwtBlacklist = new JwtBlacklist();
        jwtBlacklist.setToken(token);
        jwtBlacklist.setExpiracion(expiracion);
        return jwtBlacklist;
    }
}
