package org.cesde.academic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.cesde.academic.enums.NivelPensum;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class PensumResponseDTO {
    private Integer id;
    private String programa;
    private String modulo;
    private NivelPensum nivel;
    private LocalDateTime creado;
    private LocalDateTime actualizado;
}
