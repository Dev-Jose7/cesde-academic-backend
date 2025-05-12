package org.cesde.academic.dto.response;

import lombok.*;

import java.time.LocalDateTime;

// Se usa constructor con argumentos ya que por buenas prácticas no se usan setters en DTOs de respuestas
@AllArgsConstructor
@Getter
@ToString
public class EscuelaResponseDTO {// DTO de salida: inmutable y control sobre el acceso a los datos (encapsulado).
    private final Integer id;
    private final String nombre;
    private final LocalDateTime creado;
    private final LocalDateTime actualizado;
}

//Tipo de DTO	Estado	Validaciones	Getters	Setters	Constructor
//RequestDTO	Mutable	       ✅ Sí	✅ Sí	✅ Sí     Implicado
//ResponseDTO	Inmutable	Opcional	✅ Sí	❌ No	✅ Completo
