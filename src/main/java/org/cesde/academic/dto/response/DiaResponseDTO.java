package org.cesde.academic.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.cesde.academic.enums.NombreDia;

@AllArgsConstructor
@Getter
@ToString
public class DiaResponseDTO {

    private final Integer id;
    private final NombreDia nombre;
}
