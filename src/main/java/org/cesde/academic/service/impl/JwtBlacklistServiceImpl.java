package org.cesde.academic.service.impl;

import org.cesde.academic.model.JwtBlacklist;
import org.cesde.academic.enums.TipoToken;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.JwtBlacklistRepository;
import org.cesde.academic.service.IJwtBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtBlacklistServiceImpl implements IJwtBlacklistService {

    @Autowired
    private JwtBlacklistRepository repository;

    @Override
    public void blacklistToken(String token, TipoToken tipo, Date expiration, Usuario usuario) {
        JwtBlacklist jwt = new JwtBlacklist();
        jwt.setToken(token);
        jwt.setExpiracion(expiration);
        jwt.setTipo(tipo);
        jwt.setUsuario(usuario);
        repository.save(jwt);
    }

    @Override
    public boolean isTokenBlacklisted(String token, TipoToken tipo) {
        return repository.existsByTokenAndTipo(token, tipo);
    }

    @Override
    public void deleteExpiredTokens() {
        repository.deleteExpiredTokens();
    }
}
