package br.com.fiap.pettech.dominio.produto.exception;

public class ControllerNotFoundException extends RuntimeException {

    public ControllerNotFoundException(String message) {
        super(message);
    }
}
