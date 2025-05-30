package org.cesde.academic.service.impl;

import org.cesde.academic.model.JwtBlacklist;
import org.cesde.academic.repository.JwtBlacklistRepository;
import org.cesde.academic.service.IJwtBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class JwtBlacklistServiceImpl implements IJwtBlacklistService {

    @Autowired
    private JwtBlacklistRepository jwtblacklistrepository;

    @Override
    public void createBlacklistToken(String token, Date expiracion) {
        jwtblacklistrepository.save(createEntity(token, expiracion));
    }

    @Override
    public boolean isTokenBlacklisted(String token) {
        return jwtblacklistrepository.existsByToken(token);
    }

    @Override
    public void deleteTokenBlacklist() {
        jwtblacklistrepository.deleteExpiredTokens();
    }

    private JwtBlacklist createEntity(String token, Date expiracion){
        JwtBlacklist jwtBlacklist = new JwtBlacklist();
        jwtBlacklist.setToken(token);
        jwtBlacklist.setExpiracion(expiracion);
        return jwtBlacklist;
    }
}
