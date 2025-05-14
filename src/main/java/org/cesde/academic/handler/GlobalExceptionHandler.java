package org.cesde.academic.handler;

import org.cesde.academic.exception.RecursoExistenteException;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.exception.TipoIncorrectoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice es como un subcontrolador especializado en manejar excepciones
// que son lanzadas en el contexto de un endpoint (es decir, durante una petición HTTP).
// Su propósito es ejecutar el métodō correspondiente anotado con @ExceptionHandler,
// y lo que este métodō retorne será la respuesta final enviada al cliente.

// ¿Por qué se llama ControllerAdvice?
// Porque aconseja (advice) o intercepta el comportamiento de los controladores (@Controller - @RestController). En particular:
// No se aplica a otras capas como @Service o @Repository.
// Solo entra en acción cuando la excepción es lanzada durante el procesamiento de una petición web (HTTP), que está siendo manejada por un controlador.
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> handleRecursoNoEncontrado(RecursoNoEncontradoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TipoIncorrectoException.class)
    public ResponseEntity<String> handlerTipoIncorrecto(TipoIncorrectoException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecursoExistenteException.class)
    public ResponseEntity<String> handlerRecuersoExistente(RecursoExistenteException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}

//FLUJO DETALLADO DEL MANEJO DE EXCEPCIONES
//1. El cliente hace una petición HTTP → se invoca un métodō del @RestController.
//
//2. El controlador llama a un métodō del servicio.
//
//3. El servicio lanza una excepción como RecursoNoEncontradoException.
//
//4. Como no hay try-catch local, la excepción sube hasta el framework.
//
//5. Spring busca una clase anotada con @ControllerAdvice que tenga un @ExceptionHandler para esa excepción.
//
//6. Spring ejecuta ese @ExceptionHandler y lo trata como si fuera la respuesta del controlador.
//
//7. Lo que retorna el @ExceptionHandler (por ejemplo, un ResponseEntity) se convierte en la respuesta final al cliente.


//FLUJO TÉCNICO REAL:
//Cliente hace una petición → Spring despacha al métodō del controlador.
//
//El métodō del controlador llama al servicio.
//
//El servicio lanza una excepción (por ejemplo RecursoNoEncontradoException).
//
//Spring detecta que la excepción fue lanzada en el contexto de una petición HTTP.
//
//Spring omite volver al controlador, y en cambio...
//
//Busca si hay una clase @ControllerAdvice con @ExceptionHandler para esa excepción.
//
//Si la encuentra, la ejecuta directamente, y el resultado (como un ResponseEntity) es lo que el cliente recibe.

// ANALOGÍA
// Piensa en el controlador como un pasajero en un tren.
//Si algo explota en el vagón del servicio (una excepción), el tren entero se detiene y el controlador nunca llega a destino.
//
//Entonces entra en acción el equipo de emergencia del tren (el @ControllerAdvice) que maneja la situación y da una respuesta final al pasajero (el cliente).