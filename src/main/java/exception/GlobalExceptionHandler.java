package exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.todo.todo_api.model.Tarea;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.apache.coyote.BadRequestException;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<?> manejarResourceNotFoundException(BadRequestException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manejarValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
public ResponseEntity<?> manejarMensajeNoLegible(HttpMessageNotReadableException ex) {
    Map<String, String> error = new HashMap<>();

    Throwable causa = ex.getCause();
    if (causa instanceof InvalidFormatException) {
        InvalidFormatException ife = (InvalidFormatException) causa;
        if (ife.getTargetType().equals(LocalDate.class)) {
            error.put("fecha", "Fecha no válida. Usa el formato yyyy-MM-dd.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } else if (ife.getTargetType().equals(Tarea.Estatus.class)) {
            error.put("estatus", "Estatus no válido. Valores permitidos: pendiente, progreso, completado.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    error.put("error", "Solicitud malformada. Verifica los datos enviados.");
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
}

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> manejarBadRequestException(BadRequestException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manejarExcepcionGenerica(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Error interno: " + ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
