package dev.raydelcarmen.Technical_Test_Api.shared.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("\"No se encontró ningún usuario @" + username + "\"");
    }
}