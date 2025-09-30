package br.com.fiap.pettech.dominio.produto.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message) {
        super(message);
    }
}
