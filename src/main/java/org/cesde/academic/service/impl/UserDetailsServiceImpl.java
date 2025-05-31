package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.AuthRequestDTO;
import org.cesde.academic.dto.response.AuthResponseDTO;
import org.cesde.academic.dto.response.UsuarioResponseDTO;
import org.cesde.academic.model.Permission;
import org.cesde.academic.model.Role;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.UsuarioRepository;
import org.cesde.academic.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String cedula) throws UsernameNotFoundException {
        // Buscar al usuario en la base de datos por cedula
        Usuario usuario = loadUsuarioByCedula(cedula);

        // Convertir los roles y permisos del usuario en una lista de autoridades
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        // Agregar los roles como autoridades
        for (Role role : usuario.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getNombre())); // Asumiendo que el role tiene un nombre
        }

        System.out.println("Roles:" + authorities);

        // Agregar los permisos de cada rol como autoridades
        for (Role role : usuario.getRoles()) {
            for (Permission permission : role.getPermisos()) {
                authorities.add(new SimpleGrantedAuthority(permission.getNombre()));
            }
        }

        System.out.println("Permisos:" + authorities);

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

    public Usuario loadUsuarioByCedula(String cedula){
        return usuarioRepository.findByCedula(cedula)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario con cedula " + cedula + " no encontrado"));
    }

    public AuthResponseDTO loginUser(AuthRequestDTO request){

        Authentication authentication = this.authenticateToAccessToken(request.getCedula(), request.getContrasena());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken =  jwtUtils.createAccessToken(authentication); // Se crean access token con una autenticación de tipo token (cédula, contraseña y autoridades)
        String refreshToken = jwtUtils.createRefreshToken(request.getCedula()); // Se crea refresh token solo con la cédula (sin contraseña ni autoridades: roles y permisos)

        UsuarioResponseDTO usuario = usuarioService.getUsuarioByCedula(request.getCedula()).getFirst(); // Se obtiene datos del usuario para cargarlos en la respuesta

        return new AuthResponseDTO(usuario, "Usuario logueado correctamente", accessToken, refreshToken, true);
    }

    public Authentication authenticateToAccessToken(String cedula, String contrasena){
        UserDetails userDetails = this.loadUserByUsername(cedula);

        if (userDetails == null){
            throw new BadCredentialsException("Credenciales invalidas");
        }

        if (!passwordEncoder.matches(contrasena, userDetails.getPassword())){
            throw new BadCredentialsException("Credenciales invalidas");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()
        );
    }

    public Authentication authenticateToRefreshToken(String cedula) {
        UserDetails userDetails = this.loadUserByUsername(cedula);
        if (userDetails == null) {
            throw new BadCredentialsException("Usuario no encontrado");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), null, userDetails.getAuthorities()
        );
    }
}