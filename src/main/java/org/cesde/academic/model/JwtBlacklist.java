package org.cesde.academic.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "jwt_blacklist", indexes = {
        @Index(name = "idx_token", columnList = "token")
        // Crea un índice en la columna token en la base de datos, esto mejora el rendimiento de consultas (filtros)
})
public class JwtBlacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 512, nullable = false, unique = true)
    private String token;

    @Column(name = "expiracion", nullable = false)
    private Date expiracion;

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

    public Date getExpiracion() {
        return expiracion;
    }

    public void setExpiracion(Date expiracion) {
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

