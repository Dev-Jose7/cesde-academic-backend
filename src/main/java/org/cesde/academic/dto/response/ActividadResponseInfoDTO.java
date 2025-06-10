package org.cesde.academic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.cesde.academic.enums.TipoActividad;

@AllArgsConstructor
@Getter
@ToString
public class ActividadResponseInfoDTO {
    private Integer id;
    private String titulo;
    private TipoActividad tipo;
}
