package org.cesde.academic.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.cesde.academic.enums.NivelPensum;

@Getter
@Setter
@ToString
public class PensumRequestDTO {
    private Integer programaId;
    private Integer moduloId;
    private NivelPensum nivel;
}
