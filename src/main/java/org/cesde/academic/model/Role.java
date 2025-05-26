package org.cesde.academic.model;

import jakarta.persistence.*;
import org.cesde.academic.enums.NombreRole;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    @Enumerated(EnumType.STRING)
    private NombreRole nombre;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permisos = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NombreRole getNombre() {
        return nombre;
    }

    public void setNombre(NombreRole nombre) {
        this.nombre = nombre;
    }

    public Set<Permission> getPermisos() {
        return permisos;
    }

    public void setPermisos(Set<Permission> permisos) {
        this.permisos = permisos;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", nombre=" + nombre +
                ", permisos=" + permisos +
                '}';
    }
}
