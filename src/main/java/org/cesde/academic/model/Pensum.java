package org.cesde.academic.model;

import jakarta.persistence.*;
import org.cesde.academic.enums.NivelPensum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "pensum")
public class Pensum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(targetEntity = Programa.class)
    @JoinColumn(name = "programa_id", nullable = false)
    private Programa programa;

    @ManyToOne(targetEntity = Modulo.class)
    @JoinColumn(name = "modulo_id", nullable = false)
    private Modulo modulo;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false)
    private NivelPensum nivel;

    @CreationTimestamp
    @Column(name = "creado", nullable = false, updatable = false)
    private LocalDateTime creado;

    @UpdateTimestamp
    @Column(name = "actualizado", nullable = false)
    private LocalDateTime actualizado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public NivelPensum getNivel() {
        return nivel;
    }

    public void setNivel(NivelPensum nivel) {
        this.nivel = nivel;
    }

    public LocalDateTime getCreado() {
        return creado;
    }

    public void setCreado(LocalDateTime creado) {
        this.creado = creado;
    }

    public LocalDateTime getActualizado() {
        return actualizado;
    }

    public void setActualizado(LocalDateTime actualizado) {
        this.actualizado = actualizado;
    }

    @Override
    public String toString() {
        return "Pensum{" +
                "id=" + id +
                ", programa=" + programa +
                ", modulo=" + modulo +
                ", nivel=" + nivel +
                ", creado=" + creado +
                ", actualizado=" + actualizado +
                '}';
    }
}
