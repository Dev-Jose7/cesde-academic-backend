package org.cesde.academic.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "jwt_blacklist", indexes = {
        @Index(name = "idx_token", columnList = "token")
        // Crea un índice en la columna token en la base de datos, esto mejora el rendimiento de consultas (filtros)
})
public class JwtBlacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 1000, nullable = false, unique = true)
    private String token;

    @Column(name = "expiracion", nullable = false)
    private LocalDateTime expiracion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiracion() {
        return expiracion;
    }

    public void setExpiracion(LocalDateTime expiracion) {
        this.expiracion = expiracion;
    }

    @Override
    public String toString() {
        return "JwtBlacklist{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", expiracion=" + expiracion +
                '}';
    }
}

