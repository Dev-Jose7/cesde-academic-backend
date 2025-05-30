package org.cesde.academic.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "jwt_blacklist", indexes = {
        @Index(name = "idx_access_token", columnList = "access_token"),
        @Index(name = "idx_refresh_token", columnList = "refresh_token")
        // Crea un índice en la columna token en la base de datos, esto mejora el rendimiento de consultas (filtros)
})
public class JwtBlacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cedula", nullable = false)
    private String cedula;

    @Column(name = "access_token", length = 512, nullable = false, unique = true)
    private String accessToken;

    @Column(name = "access_expiracion", nullable = false)
    private Date accessExpiracion;

    @Column(name = "refresh_token", length = 512, nullable = false, unique = true)
    private String refreshToken;

    @Column(name = "refresh_expiracion", nullable = false)
    private Date refreshExpiracion;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getAccessExpiracion() {
        return accessExpiracion;
    }

    public void setAccessExpiracion(Date accessExpiracion) {
        this.accessExpiracion = accessExpiracion;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getRefreshExpiracion() {
        return refreshExpiracion;
    }

    public void setRefreshExpiracion(Date refreshExpiracion) {
        this.refreshExpiracion = refreshExpiracion;
    }

    @Override
    public String toString() {
        return "JwtBlacklist{" +
                "id=" + id +
                ", cedula='" + cedula + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", accessExpiracion=" + accessExpiracion +
                ", refreshToken='" + refreshToken + '\'' +
                ", refreshExpiracion=" + refreshExpiracion +
                '}';
    }
}

