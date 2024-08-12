package dev.raydelcarmen.Technical_Test_Api.shared.exception;

public class NoUsersFollowedException extends RuntimeException {
    public NoUsersFollowedException(String username) {
        super(username + " no sigue a ningún usuario.");
    }
}