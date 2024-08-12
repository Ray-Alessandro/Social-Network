package dev.raydelcarmen.Technical_Test_Api.shared.exception;

public class NoPostsFoundException extends RuntimeException {
    public NoPostsFoundException() {
        super("No hay posts publicados por los usuarios que sigue este usuario.");
    }
}