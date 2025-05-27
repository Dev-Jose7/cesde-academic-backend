package org.cesde.academic.config.init;

import jakarta.annotation.PostConstruct;
import org.cesde.academic.enums.NombreRole;
import org.cesde.academic.model.Permission;
import org.cesde.academic.model.Role;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.PermissionRepository;
import org.cesde.academic.repository.RoleRepository;
import org.cesde.academic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RolePermissionAndUserAssignmentInit {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void init() {
        initPermisosYRoles();
        asignarRolesAUsuarios();
    }

    private void initPermisosYRoles() {
        // Crear permisos base
        List<String> permisoNombres = List.of("CREATE", "READ", "UPDATE", "DELETE");

        for (String nombre : permisoNombres) {
            permissionRepository.findByNombre(nombre)
                    .orElseGet(() -> permissionRepository.save(createPermission(nombre)));
        }

        // Asignar permisos por rol
        Map<NombreRole, List<String>> permisosPorRol = new HashMap<>();
        permisosPorRol.put(NombreRole.INVITADO, List.of("READ"));
        permisosPorRol.put(NombreRole.ESTUDIANTE, List.of("CREATE", "READ", "UPDATE"));
        permisosPorRol.put(NombreRole.DOCENTE, List.of("CREATE", "READ", "UPDATE"));
        permisosPorRol.put(NombreRole.ADMINISTRATIVO, List.of("CREATE", "READ", "UPDATE"));
        permisosPorRol.put(NombreRole.DIRECTIVO, List.of("CREATE", "READ", "UPDATE"));
        permisosPorRol.put(NombreRole.DEV, List.of("CREATE", "READ", "UPDATE", "DELETE"));

        for (var entry : permisosPorRol.entrySet()) {
            NombreRole roleEnum = entry.getKey();
            List<String> permisos = entry.getValue();

            Role role = roleRepository.findByNombre(roleEnum)
                    .orElseGet(() -> {
                        Role nuevo = new Role();
                        nuevo.setNombre(roleEnum);
                        return nuevo;
                    });

            Set<Permission> permisosAsignados = new HashSet<>();
            for (String nombrePermiso : permisos) {
                permissionRepository.findByNombre(nombrePermiso)
                        .ifPresent(permisosAsignados::add);
            }

            role.setPermisos(permisosAsignados);
            roleRepository.save(role);
        }

        System.out.println("✅ Permisos y roles inicializados.");
    }

    private void asignarRolesAUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        for (Usuario usuario : usuarios) {
            // Asignar rol por tipo de usuario (si aplica)
            if (usuario.getTipo() != null) {
                try {
                    NombreRole rolNombre = NombreRole.valueOf(usuario.getTipo().name());
                    roleRepository.findByNombre(rolNombre)
                            .ifPresent(role -> usuario.getRoles().add(role));
                } catch (IllegalArgumentException e) {
                    System.err.println("❌ Tipo de usuario no coincide con NombreRole enum: " + usuario.getTipo());
                }
            }

            // Asignar rol DEV si coincide el nombre exacto
            if ("José Fernando Navarro Rivera".equalsIgnoreCase(usuario.getNombre())) {
                roleRepository.findByNombre(NombreRole.DEV)
                        .ifPresent(dev -> usuario.getRoles().add(dev));
            }

            usuarioRepository.save(usuario);
        }

        System.out.println("✅ Roles asignados a usuarios.");
    }

    private Permission createPermission(String nombre) {
        Permission p = new Permission();
        p.setNombre(nombre);
        return p;
    }
}
