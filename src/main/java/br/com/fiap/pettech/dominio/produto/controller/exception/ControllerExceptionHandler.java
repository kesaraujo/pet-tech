package br.com.fiap.pettech.dominio.produto.controller.exception;

import br.com.fiap.pettech.exception.ControllerNotFoundException;
import br.com.fiap.pettech.exception.DatabaseException;
import br.com.fiap.pettech.exception.DefaultError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    private DefaultError error = new DefaultError();

    @ExceptionHandler(ControllerNotFoundException.class)
    public ResponseEntity<DefaultError> entityNotFound(ControllerNotFoundException exception, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Entidade não encontrada.");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(this.error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<DefaultError> entityNotFound(DatabaseException exception, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("DataBase error.");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(this.error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultError> validation(MethodArgumentNotValidException exception, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Erro de Validação");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(this.error);
    }
}
