package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.AuthRequestDTO;
import org.cesde.academic.dto.response.AuthResponseDTO;
import org.cesde.academic.model.Permission;
import org.cesde.academic.model.Role;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.cesde.academic.util.JwtUtils;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String cedula) throws UsernameNotFoundException {
        // Buscar al usuario en la base de datos por cedula
        Usuario usuario = usuarioRepository.findByCedula(cedula)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario con cedula " + cedula + " no encontrado"));

        // Convertir los roles y permisos del usuario en una lista de autoridades
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        // Agregar los roles como autoridades
        for (Role role : usuario.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getNombre())); // Asumiendo que el role tiene un nombre
        }

        // Agregar los permisos de cada rol como autoridades
        for (Role role : usuario.getRoles()) {
            for (Permission permission : role.getPermisos()) {
                authorities.add(new SimpleGrantedAuthority(permission.getNombre()));
            }
        }

        // Crear el objeto UserDetails con los datos del usuario
        return new org.springframework.security.core.userdetails.User(
                usuario.getCedula(),
                usuario.getContrasena(),
                usuario.getIsEnabled(),
                usuario.getAccountNoExpired(),
                usuario.getCredentialNoExpired(),
                usuario.getAccountNoLocked(),
                authorities
        );
    }

    public AuthResponseDTO loginUser(AuthRequestDTO request){

        Authentication authentication = this.authenticate(request.getCedula(), request.getContrasena());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken =  jwtUtils.createToken(authentication);
        String refreshToken = jwtUtils.createRefreshToken(request.getCedula());

        return new AuthResponseDTO(request.getCedula(), "Usuario logueado correctamente", accessToken, refreshToken, true);
    }

    public Authentication authenticate(String cedula, String contrasena){
        UserDetails userDetails = this.loadUserByUsername(cedula);

        if (userDetails == null){
            throw new BadCredentialsException("Credenciales invalidas");
        }

        if (!passwordEncoder.matches(contrasena, userDetails.getPassword())){
            throw new BadCredentialsException("Credenciales invalidas");
        }

        return new UsernamePasswordAuthenticationToken(cedula, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public Authentication authenticateRefreshToken(String cedula) {
        UserDetails userDetails = this.loadUserByUsername(cedula);
        if (userDetails == null) {
            throw new BadCredentialsException("Usuario no encontrado");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), null, userDetails.getAuthorities()
        );
    }
}