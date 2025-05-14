package org.cesde.academic.dto.response;

import lombok.*;

@AllArgsConstructor
@Getter
@ToString
public class ClaseResponseInfoDTO {
    private final String grupo;
    private final String docente;
    private final String modulo;
}
