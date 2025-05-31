package org.cesde.academic.model;

import jakarta.persistence.*;
import org.cesde.academic.enums.TipoToken;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "jwt_blacklist", indexes = {
        @Index(name = "idx_token", columnList = "token"),
        @Index(name = "idx_tipo_token", columnList = "tipo")
})
public class JwtBlacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "token", length = 512, nullable = false, unique = true)
    private String token;

    @Column(name = "expiracion", nullable = false)
    private Date expiracion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoToken tipo;

    // Getters y setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public TipoToken getTipo() {
        return tipo;
    }

    public void setTipo(TipoToken tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "JwtBlacklist{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", token='" + token + '\'' +
                ", expiracion=" + expiracion +
                ", tipo=" + tipo +
                '}';
    }
}

