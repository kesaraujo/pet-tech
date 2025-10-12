package br.com.fiap.pettech.exception;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message) {
        super(message);
    }
}
