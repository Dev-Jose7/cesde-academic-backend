package org.cesde.academic.service.impl;

import org.cesde.academic.model.Permission;
import org.cesde.academic.model.Role;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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
}