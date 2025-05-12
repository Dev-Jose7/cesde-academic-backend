package org.cesde.academic.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
public class EscuelaRequestDTO { // DTO de entrada: mutable y validado.

    @NotNull
    @Size(min = 1, max = 255, message = "El nombre de la escuela debe tener entre 1 y 255 caracteres")
    private String nombre;
}

//Tipo de DTO	Estado	Validaciones	Getters	Setters	Constructor
//RequestDTO	Mutable	       ✅ Sí	✅ Sí	✅ Sí     Implicado
//ResponseDTO	Inmutable	Opcional	✅ Sí	❌ No	✅ Completo