package com.ceiba.biblioteca.excepciones;

import com.ceiba.biblioteca.utilidades.MensajesConstantes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                     NativeWebRequest request) {

        return new ResponseEntity<>(
                new MensajeError(
                        MensajesConstantes.DATOS_ENTRADA_INVALIDOS
                ),
                HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MensajeError> handleIllegalArgumentException(final NativeWebRequest req,
                                                                       final IllegalArgumentException ex) {
        return new ResponseEntity<>(
                new MensajeError(
                        MensajesConstantes.DATOS_ENTRADA_INVALIDOS
                ),
                HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MensajeError> handleResourceNotFoundException(ResourceNotFoundException ex) {
        MensajeError message = new MensajeError(
                ex.getMessage()
        );

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<MensajeError> handleBadRequestException(
            BadRequestException ex) {

        MensajeError message = new MensajeError(ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeError> globalExceptionHandler(Exception ex) {
        MensajeError message = new MensajeError(
                ex.getMessage()
        );

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        MensajeError message = new MensajeError(MensajesConstantes.JSON_MALFORMADO_MENSAJE);

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> details = new ArrayList<>();
        details.add(String.format(MensajesConstantes.METODO_URL_NO_ENCONTRADO_MENSAJE, ex.getHttpMethod(), ex.getRequestURL()));

        MensajeError message = new MensajeError(
                MensajesConstantes.VIOLACION_CONSTRAINT_MENSAJE + " " + details
        );
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
