package org.cesde.academic.repository;

import org.cesde.academic.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
    // Consulta para validación
    boolean existsByDiaIdAndFranjaHorariaId(Integer diaId, Integer franjaHorariaId); // Para creación
    boolean existsByDiaIdAndFranjaHorariaIdAndIdNot(Integer diaId, Integer franjaHorariaId, Integer id); // Para actualización
    // IdNot permite excluir de la verificación el registro encontrado con el diaId y franjaHorariaId proporcionado
    // siempre y cuando el ID entregado como parametro sea igual al ID del registro encontrado, permitiendo así
    // actualizar un registro cuando no se desea editar el dia y franja de este.

    // Consultar registros a través de campos foráneos
    List<Horario> findAllByDiaId(Integer diaId);
    List<Horario> findAllByFranjaHorariaId(Integer franjaId);

    //findBy: inicia una consulta basada en nombres de propiedades.
    //FranjaHorariaId: es el nombre del atributo en la entidad Horario que representa una relación @ManyToOne con FranjaHoraria.
    //Id: accede a la propiedad id de esa entidad relacionada (FranjaHoraria).
}
