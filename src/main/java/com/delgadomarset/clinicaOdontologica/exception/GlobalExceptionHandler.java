package com.delgadomarset.clinicaOdontologica.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> procesarNotFoundException(ResourceNotFoundException exception){
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("message", "Recurso no encontrado: " + exception.getMessage());
        return mensaje;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarNotValidException(MethodArgumentNotValidException exception){
        Map<String, String> mensaje = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String campo = ((FieldError) error).getField();
            String mensajeError = error.getDefaultMessage();
            mensaje.put(campo, mensajeError);
        });
        return mensaje;
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarBadRquesttException(BadRequestException exception) {
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("message", "Error de sintaxis: " + exception.getMessage());
        return mensaje;
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> procesarDataIntegrityViolationException(DataIntegrityViolationException exception) {
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("message", "¡Atención! Violación de restricción de integridad referencial; "
                .concat("en otras palabras, estás realizando transacciones con registros que no han sido creados ")
                .concat("o que están siendo utilizados por otros registros."));
        return mensaje;
    }




}
