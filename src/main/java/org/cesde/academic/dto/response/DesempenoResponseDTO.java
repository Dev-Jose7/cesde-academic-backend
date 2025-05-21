package org.cesde.academic.dto.response;

import lombok.*;
import org.cesde.academic.enums.EstadoDesempeno;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@ToString
public class DesempenoResponseDTO {
    private Integer id;
    private String estudiante;
    private String modulo;
    private BigDecimal calificacion;
    private EstadoDesempeno estado;
}
