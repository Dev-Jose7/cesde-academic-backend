package org.cesde.academic.repository;

import org.cesde.academic.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
    List<Horario> findByDia_Id(Integer diaId);
    List<Horario> findByfranjaHoraria_Id(Integer franjaId);

    //findBy: inicia una consulta basada en nombres de propiedades.
    //Franja: es el nombre del atributo en la entidad Horario que representa una relación @ManyToOne con FranjaHoraria.
    //_Id: accede a la propiedad id de esa entidad relacionada (FranjaHoraria).
}
